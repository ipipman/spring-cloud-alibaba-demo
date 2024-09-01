package cn.ipman.coupon.template.serv.service;

import cn.ipman.coupon.template.api.beans.CouponTemplateInfo;
import cn.ipman.coupon.template.api.beans.PagedCouponTemplateInfo;
import cn.ipman.coupon.template.api.beans.TemplateSearchParams;
import cn.ipman.coupon.template.api.enums.CouponType;
import cn.ipman.coupon.template.serv.converter.CouponTemplateConverter;
import cn.ipman.coupon.template.serv.dao.CouponTemplateDao;
import cn.ipman.coupon.template.serv.dao.entity.CouponTemplate;
import cn.ipman.coupon.template.serv.service.intf.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 优惠券模板类相关操作
 * 主要负责处理与优惠券模板相关的业务逻辑，包括创建、克隆、查询和删除模板等操作
 *
 * @Author IpMan
 * @Date 2024/9/1 22:03
 */
@Slf4j
@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

    @Autowired
    private CouponTemplateDao templateDao; // 模板数据访问对象，用于数据库操作

    // 克隆优惠券模板
    @Override
    public CouponTemplateInfo cloneTemplate(Long templateId) {
        log.info("cloning template id {}", templateId);
        // 根据ID查找模板，如果不存在抛出异常
        CouponTemplate source = templateDao.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("invalid template ID"));

        // 复制属性到新模板对象
        CouponTemplate target = new CouponTemplate();
        BeanUtils.copyProperties(source, target);

        // 重置ID和可用状态
        target.setAvailable(true);
        target.setId(null);

        // 保存新的模板并转换为信息对象返回
        templateDao.save(target);
        return CouponTemplateConverter.convertToTemplateInfo(target);
    }

    /**
     * 创建优惠券模板
     * 单个门店最多可以创建100张优惠券模板
     * 如果超过限制，抛出异常
     */
    @Override
    public CouponTemplateInfo createTemplate(CouponTemplateInfo request) {
        // 检查门店是否超过模板创建限制
        if (request.getShopId() != null) {
            Integer count = templateDao.countByShopIdAndAvailable(request.getShopId(), true);
            if (count >= 100) {
                log.error("the totals of coupon template exceeds maximum number");
                throw new UnsupportedOperationException("exceeded the maximum of coupon templates that you can create");
            }
        }

        // 构建并保存新的优惠券模板
        CouponTemplate template = CouponTemplate.builder()
                .name(request.getName())
                .description(request.getDesc())
                .category(CouponType.convert(request.getType()))
                .available(true)
                .shopId(request.getShopId())
                .rule(request.getRule())
                .build();
        template = templateDao.save(template);

        // 将保存后的模板转换为信息对象返回
        return CouponTemplateConverter.convertToTemplateInfo(template);
    }

    /**
     * 搜索优惠券模板
     * 根据搜索参数查找并返回分页的优惠券模板信息
     */
    @Override
    public PagedCouponTemplateInfo search(TemplateSearchParams request) {
        // 构建搜索示例和分页请求
        CouponTemplate example = CouponTemplate.builder()
                .shopId(request.getShopId())
                .category(CouponType.convert(request.getType()))
                .available(request.getAvailable())
                .name(request.getName())
                .build();
        Pageable page = PageRequest.of(request.getPage(), request.getPageSize());

        // 执行搜索并转换结果
        Page<CouponTemplate> result = templateDao.findAll(Example.of(example), page);
        List<CouponTemplateInfo> couponTemplateInfos = result.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toList());

        // 构建并返回分页的优惠券模板信息
        PagedCouponTemplateInfo response = PagedCouponTemplateInfo.builder()
                .templates(couponTemplateInfos)
                .page(request.getPage())
                .total(result.getTotalElements())
                .build();

        return response;
    }

    /**
     * 通过ID查询优惠券模板
     * 根据指定的模板ID查找并返回优惠券模板信息
     */
    @Override
    public CouponTemplateInfo loadTemplateInfo(Long id) {
        // 根据ID查找模板，如果存在则转换为信息对象返回，否则返回null
        Optional<CouponTemplate> template = templateDao.findById(id);
        return template.map(CouponTemplateConverter::convertToTemplateInfo).orElse(null);
    }

    // 将优惠券模板设置为不可用状态
    @Override
    @Transactional
    public void deleteTemplate(Long id) {
        // 根据ID将模板的可用状态设置为false，如果影响行数为0，则抛出异常
        int rows = templateDao.makeCouponUnavailable(id);
        if (rows == 0) {
            throw new IllegalArgumentException("Template Not Found: " + id);
        }
    }

    /**
     * 批量读取模板信息
     * 根据提供的模板ID集合，批量查找并返回对应的模板信息映射
     */
    @Override
    public Map<Long, CouponTemplateInfo> getTemplateInfoMap(Collection<Long> ids) {
        // 根据ID集合查找模板并转换为信息对象，然后构建ID到信息对象的映射
        List<CouponTemplate> templates = templateDao.findAllById(ids);

        return templates.stream()
                .map(CouponTemplateConverter::convertToTemplateInfo)
                .collect(Collectors.toMap(CouponTemplateInfo::getId, Function.identity()));
    }
}
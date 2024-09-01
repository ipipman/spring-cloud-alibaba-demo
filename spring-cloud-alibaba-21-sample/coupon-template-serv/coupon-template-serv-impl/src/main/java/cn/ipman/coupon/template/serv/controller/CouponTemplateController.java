package cn.ipman.coupon.template.serv.controller;

import cn.ipman.coupon.template.api.beans.CouponTemplateInfo;
import cn.ipman.coupon.template.api.beans.PagedCouponTemplateInfo;
import cn.ipman.coupon.template.api.beans.TemplateSearchParams;
import cn.ipman.coupon.template.serv.service.intf.CouponTemplateService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/**
 * 优惠券模版控制器
 * 提供了对优惠券模版的各种操作，如创建、克隆、读取、批量获取、搜索和删除优惠券模版。
 *
 * @Author IpMan
 * @Date 2024/9/1 22:31
 */
@Slf4j
@RestController
@RequestMapping("/template")
public class CouponTemplateController {

    @Autowired
    private CouponTemplateService couponTemplateService;

    /**
     * 创建优惠券模版
     *
     * @param request 包含优惠券模版信息的请求体
     * @return 创建完成后的优惠券模版信息
     */
    @PostMapping("/addTemplate")
    public CouponTemplateInfo addTemplate(@Valid @RequestBody CouponTemplateInfo request) {
        log.info("Create coupon template: data={}", request);
        return couponTemplateService.createTemplate(request);
    }

    /**
     * 克隆优惠券模版
     *
     * @param templateId 需要克隆的优惠券模版ID
     * @return 克隆后的优惠券模版信息
     */
    @PostMapping("/cloneTemplate")
    public CouponTemplateInfo cloneTemplate(@RequestParam("id") Long templateId) {
        log.info("Clone coupon template: data={}", templateId);
        return couponTemplateService.cloneTemplate(templateId);
    }

    /**
     * 读取优惠券模版信息
     *
     * @param id 优惠券模版ID
     * @return 优惠券模版详细信息
     */
    @GetMapping("/getTemplate")
    public CouponTemplateInfo getTemplate(@RequestParam("id") Long id) {
        log.info("Load template, id={}", id);
        return couponTemplateService.loadTemplateInfo(id);
    }

    /**
     * 批量获取优惠券模版信息
     *
     * @param ids 优惠券模版ID集合
     * @return 包含指定ID优惠券模版信息的Map，键为模版ID，值为模版信息
     */
    @GetMapping("/getBatch")
    public Map<Long, CouponTemplateInfo> getTemplateInBatch(@RequestParam("ids") Collection<Long> ids) {
        log.info("getTemplateInBatch: {}", JSON.toJSONString(ids));
        return couponTemplateService.getTemplateInfoMap(ids);
    }

    /**
     * 搜索优惠券模版
     *
     * @param request 包含搜索条件的请求体
     * @return 分页的优惠券模版搜索结果
     */
    @PostMapping("/search")
    public PagedCouponTemplateInfo search(@Valid @RequestBody TemplateSearchParams request) {
        log.info("search templates, payload={}", request);
        return couponTemplateService.search(request);
    }

    /**
     * 删除优惠券模版
     *
     * @param id 需要删除的优惠券模版ID
     */
    @DeleteMapping("/deleteTemplate")
    public void deleteTemplate(@RequestParam("id") Long id) {
        log.info("Load template, id={}", id);
        couponTemplateService.deleteTemplate(id);
    }

}

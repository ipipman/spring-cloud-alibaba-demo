package cn.ipman.coupon.template.serv.impl.converter;

import cn.ipman.coupon.template.api.beans.CouponTemplateInfo;
import cn.ipman.coupon.template.serv.dao.entity.CouponTemplate;

/**
 * 优惠券模板转换器类，用于在CouponTemplate实体和CouponTemplateInfoDTO之间进行转换
 * 主要是为了将数据库中的模板数据转换为API响应对象，确保数据的一致性和可维护性
 *
 * @Author IpMan
 * @Date 2024/9/1 22:00
 */
public class CouponTemplateConverter {

    /**
     * 将CouponTemplate实体转换为CouponTemplateInfo对象
     * 此方法详细地映射了实体的每个字段到DTO中对应的属性，以用于API响应
     *
     * @param template CouponTemplate实体，从数据库查询所得
     * @return CouponTemplateInfo对象，用于API响应
     */
    public static CouponTemplateInfo convertToCouponTemplateInfo(CouponTemplate template) {
        return CouponTemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .desc(template.getDescription())
                .type(template.getCategory().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }
}

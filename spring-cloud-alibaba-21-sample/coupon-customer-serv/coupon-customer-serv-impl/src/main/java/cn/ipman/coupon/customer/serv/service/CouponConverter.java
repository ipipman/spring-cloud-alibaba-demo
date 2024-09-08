package cn.ipman.coupon.customer.serv.service;

import cn.ipman.coupon.customer.serv.dao.entity.Coupon;
import cn.ipman.coupon.template.api.beans.CouponInfo;

/**
 * 优惠券转换
 *
 * @Author IpMan
 * @Date 2024/9/8 22:38
 */
public class CouponConverter {

    public static CouponInfo convertToCoupon(Coupon coupon) {

        return CouponInfo.builder()
                .id(coupon.getId())
                .status(coupon.getStatus().getCode())
                .templateId(coupon.getTemplateId())
                .shopId(coupon.getShopId())
                .userId(coupon.getUserId())
                .template(coupon.getTemplateInfo())
                .build();
    }
}

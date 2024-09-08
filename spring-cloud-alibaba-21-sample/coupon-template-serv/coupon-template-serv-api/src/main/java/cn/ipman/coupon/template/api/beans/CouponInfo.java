package cn.ipman.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装优惠券信息
 * 该类用于描述优惠券的基本信息，包括模板ID、用户ID、店铺ID和状态等
 *
 * @Author IpMan
 * @Date 2024/9/1 20:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponInfo {

    // 优惠券ID
    private Long id;

    // 优惠券模板ID，关联具体的优惠券模板
    private Long templateId;

    // 用户ID，标识领取该优惠券的用户
    private Long userId;

    // 店铺ID，标识发行该优惠券的店铺
    private Long shopId;

    // 状态，用于标识优惠券的当前状态（例如未使用、已使用、已过期等）
    private Integer status;

    // 优惠券模板详细信息，对应具体优惠券模板的详细信息
    private CouponTemplateInfo template;

}

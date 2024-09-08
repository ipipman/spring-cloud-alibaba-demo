package cn.ipman.coupon.customer.serv.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 用户领取优惠券的请求类
 * 该类封装了用户领取优惠券时所需的信息，包括用户ID和优惠券模板ID
 *
 * @Author IpMan
 * @Date 2024/9/8 22:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCoupon {

    /**
     * 用户ID
     * 领取优惠券的用户标识，不能为空
     */
    @NotNull
    private Long userId;

    /**
     * 优惠券模板ID
     * 用户希望领取的优惠券模板的唯一标识，不能为空
     */
    @NotNull
    private Long couponTemplateId;
}

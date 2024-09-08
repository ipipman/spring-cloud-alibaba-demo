package cn.ipman.coupon.customer.serv.api.beans;

import javax.validation.constraints.NotNull;

/**
 * 用户查询优惠券的请求类
 * 用于封装用户查询优惠券时需要的参数
 *
 * @Author IpMan
 * @Date 2024/9/8 22:02
 */
public class SearchCoupon {

    /**
     * 用户ID
     * 用于指定查询优惠券的用户
     */
    @NotNull
    private Long userId;

    /**
     * 商店ID
     * 可选参数，用于筛选特定商店的优惠券
     */
    private Long shopId;

    /**
     * 优惠券状态
     * 可选参数，用于筛选特定状态的优惠券
     */
    private Integer couponStatus;
}

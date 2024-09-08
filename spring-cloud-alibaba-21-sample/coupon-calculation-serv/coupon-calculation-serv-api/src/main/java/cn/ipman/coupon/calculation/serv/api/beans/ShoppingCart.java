package cn.ipman.coupon.calculation.serv.api.beans;

import cn.ipman.coupon.template.api.beans.CouponInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 封装了订单信息
 * 包含商品列表、优惠券信息、总金额和用户ID等
 *
 * @Author IpMan
 * @Date 2024/9/8 13:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    /**
     * 商品列表
     * 必须至少有一个商品
     */
    @NotEmpty
    private List<Product> products;

    /**
     * 优惠券ID
     * 用于标识当前订单欲使用的优惠券
     */
    private Long couponId;

    /**
     * 订单总金额
     * 在计算优惠后的价格时使用
     */
    private long cost;

    // 目前只支持单张优惠券
    // 但是为了以后的扩展考虑, 你可以添加多个优惠券的计算逻辑
    private List<CouponInfo> couponInfos;

    /**
     * 用户ID
     * 用于关联订单属于哪个用户
     * 在进行优惠计算和验证优惠券归属时必不可少
     */
    @NotNull
    private Long userId;
}

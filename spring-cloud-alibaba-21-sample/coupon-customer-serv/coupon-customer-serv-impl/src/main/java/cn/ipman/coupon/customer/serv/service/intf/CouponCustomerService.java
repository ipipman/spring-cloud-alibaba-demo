package cn.ipman.coupon.customer.serv.service.intf;

import cn.ipman.coupon.calculation.serv.api.beans.ShoppingCart;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationOrder;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationResponse;
import cn.ipman.coupon.customer.serv.api.beans.RequestCoupon;
import cn.ipman.coupon.customer.serv.api.beans.SearchCoupon;
import cn.ipman.coupon.customer.serv.dao.entity.Coupon;
import cn.ipman.coupon.template.api.beans.CouponInfo;

import java.util.List;

/**
 * 用户对接服务
 */
public interface CouponCustomerService {

    /**
     * 领取优惠券
     *
     * @param request 领券请求，包含用户ID和优惠券模板ID
     * @return 领取结果，包括优惠券详细信息
     */
    Coupon requestCoupon(RequestCoupon request);

    /**
     * 提交订单，核销符合条件的优惠券
     *
     * @param info 购物车信息，包含商品列表和用户信息
     * @return 核销后的购物车信息，包括优惠后的价格和使用的优惠券信息
     */
    ShoppingCart placeOrder(ShoppingCart info);

    /**
     * 模拟订单价格，计算使用优惠券后的支付金额
     *
     * @param order 模拟订单信息，包含商品列表和用户信息
     * @return 模拟结果，包括优惠后价格和使用的优惠券信息
     */
    SimulationResponse simulateOrderPrice(SimulationOrder order);

    /**
     * 删除用户优惠券
     *
     * @param userId 用户ID
     * @param couponId 优惠券ID
     */
    void deleteCoupon(Long userId, Long couponId);

    /**
     * 查询用户拥有的优惠券列表
     *
     * @param request 查询请求，包含用户ID和查询条件（如优惠券状态）
     * @return 用户优惠券列表，包含每张优惠券的详细信息
     */
    List<CouponInfo> findCoupon(SearchCoupon request);
}

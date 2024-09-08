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

    // 领券接口
    Coupon requestCoupon(RequestCoupon request);

    // 核销优惠券
    ShoppingCart placeOrder(ShoppingCart info);

    // 优惠券金额试算
    SimulationResponse simulateOrderPrice(SimulationOrder order);

    void deleteCoupon(Long userId, Long couponId);

    // 查询用户优惠券
    List<CouponInfo> findCoupon(SearchCoupon request);
}

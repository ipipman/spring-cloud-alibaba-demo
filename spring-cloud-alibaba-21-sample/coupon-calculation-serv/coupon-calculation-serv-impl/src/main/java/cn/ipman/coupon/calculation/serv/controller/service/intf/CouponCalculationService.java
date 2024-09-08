package cn.ipman.coupon.calculation.serv.controller.service.intf;

import cn.ipman.coupon.calculation.serv.api.beans.ShoppingCart;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationOrder;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 优惠券计算服务接口
 * 该接口定义了用于计算订单价格和模拟订单价格的方法
 */
public interface CouponCalculationService {

    /**
     * 计算订单价格
     * 根据购物车信息计算订单的总价，并应用优惠券
     *
     * @param cart 购物车对象，包含待结算的商品信息
     * @return 计算后的购物车对象，包含更新后的商品价格和总价
     */
    ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart);

    /**
     * 模拟订单价格
     * 根据模拟订单信息，计算应用优惠券后的订单价格
     *
     * @param order 模拟订单对象，包含待结算的商品信息和优惠券信息
     * @return 模拟响应对象，包含应用优惠券后的商品价格和总价
     */
    SimulationResponse simulateOrder(@RequestBody SimulationOrder order);
}

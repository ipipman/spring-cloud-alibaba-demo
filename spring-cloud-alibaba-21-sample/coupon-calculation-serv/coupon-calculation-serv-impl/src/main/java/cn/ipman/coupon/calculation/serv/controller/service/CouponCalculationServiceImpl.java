package cn.ipman.coupon.calculation.serv.controller.service;

import cn.ipman.coupon.calculation.serv.api.beans.ShoppingCart;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationOrder;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationResponse;
import cn.ipman.coupon.calculation.serv.controller.service.intf.CouponCalculationService;
import cn.ipman.coupon.calculation.serv.template.CouponTemplateFactory;
import cn.ipman.coupon.calculation.serv.template.RuleTemplate;
import cn.ipman.coupon.template.api.beans.CouponInfo;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 优惠券计算服务实现类
 * 该类实现了优惠券的结算和模拟计算功能
 *
 * @Author IpMan
 * @Date 2024/9/8 13:55
 */
@Slf4j
@Service
public class CouponCalculationServiceImpl implements CouponCalculationService {

    @Autowired
    private CouponTemplateFactory couponProcessorFactory;

    /**
     * 计算订单价格
     * 通过Factory类决定使用哪个底层Rule，底层规则对上层透明
     *
     * @param cart 购物车信息，包含了商品和优惠券的信息
     * @return 计算后的购物车信息，包括了优惠后的订单价格
     */
    @Override
    public ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart cart) {
        log.info("calculate order price: {}", JSON.toJSONString(cart));
        RuleTemplate ruleTemplate = couponProcessorFactory.getTemplate(cart);
        return ruleTemplate.calculate(cart);
    }


    /**
     * 模拟订单计算
     * 对所有优惠券进行试算，看哪个最赚钱
     *
     * @param order 模拟订单信息，包含了商品和优惠券的信息
     * @return 模拟响应结果，包含了每个优惠券对应的订单价格和最优优惠券的ID
     */
    @Override
    public SimulationResponse simulateOrder(@RequestBody SimulationOrder order) {
        SimulationResponse response = new SimulationResponse();
        long minOrderPrice = Long.MAX_VALUE;

        // 计算每一个优惠券的订单价格
        for (CouponInfo coupon : order.getCouponInfos()) {
            ShoppingCart cart = new ShoppingCart();
            cart.setProducts(order.getProducts());
            cart.setCouponInfos(Lists.newArrayList(coupon));
            cart = couponProcessorFactory.getTemplate(cart).calculate(cart);

            Long couponId = coupon.getId();
            long orderPrice = cart.getCost();

            // 设置当前优惠券对应的订单价格
            response.getCouponToOrderPrice().put(couponId, orderPrice);

            // 比较订单价格，设置当前最优优惠券的ID
            if (minOrderPrice > orderPrice) {
                response.setBestCouponId(coupon.getId());
                minOrderPrice = orderPrice;
            }
        }
        return response;
    }
}

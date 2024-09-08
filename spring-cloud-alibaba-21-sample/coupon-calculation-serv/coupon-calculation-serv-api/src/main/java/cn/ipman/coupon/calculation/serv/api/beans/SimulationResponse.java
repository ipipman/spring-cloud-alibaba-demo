package cn.ipman.coupon.calculation.serv.api.beans;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 用于模拟请求响应的类，帮助计算给定购物车商品和优惠券集合下，最划算的优惠券
 *
 * @Author IpMan
 * @Date 2024/9/8 13:15
 */
@Data
@NoArgsConstructor
public class SimulationResponse {

    /**
     * 最省钱的优惠券ID，通过模拟计算所有可用优惠券，选出最优惠的一个
     */
    private Long bestCouponId;

    /**
     * 每个优惠券对应的订单价格映射，使用Map结构存储
     * Key为优惠券ID，Value为应用该优惠券后的订单价格
     */
    private Map<Long, Long> couponToOrderPrice = Maps.newHashMap();

}

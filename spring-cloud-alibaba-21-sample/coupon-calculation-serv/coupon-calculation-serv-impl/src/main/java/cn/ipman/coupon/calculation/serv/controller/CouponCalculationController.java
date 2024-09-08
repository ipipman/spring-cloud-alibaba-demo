package cn.ipman.coupon.calculation.serv.controller;

import cn.ipman.coupon.calculation.serv.api.beans.ShoppingCart;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationOrder;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationResponse;
import cn.ipman.coupon.calculation.serv.controller.service.intf.CouponCalculationService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 优惠券计算API服务
 *
 * @Author IpMan
 * @Date 2024/9/8 13:54
 */
@Slf4j
@RestController
@RequestMapping("/calculator")
public class CouponCalculationController {


    @Autowired
    private CouponCalculationService calculationService;

    // 优惠券结算
    @PostMapping("/checkout")
    @ResponseBody
    public ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart settlement) {
        log.info("do calculation: {}", JSON.toJSONString(settlement));
        return calculationService.calculateOrderPrice(settlement);
    }

    // 优惠券列表挨个试算
    // 给客户提示每个可用券的优惠额度，帮助挑选
    @PostMapping("/simulate")
    @ResponseBody
    public SimulationResponse simulate(@RequestBody SimulationOrder simulator) {
        log.info("do simulation: {}", JSON.toJSONString(simulator));
        return calculationService.simulateOrder(simulator);
    }

}

package cn.ipman.coupon.customer.serv.controller;

import cn.ipman.coupon.calculation.serv.api.beans.ShoppingCart;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationOrder;
import cn.ipman.coupon.calculation.serv.api.beans.SimulationResponse;
import cn.ipman.coupon.customer.serv.api.beans.RequestCoupon;
import cn.ipman.coupon.customer.serv.api.beans.SearchCoupon;
import cn.ipman.coupon.customer.serv.dao.entity.Coupon;
import cn.ipman.coupon.customer.serv.service.intf.CouponCustomerService;
import cn.ipman.coupon.template.api.beans.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 优惠券用户控制器类，处理用户相关的优惠券操作
 */
@Slf4j
@RestController
@RequestMapping("/coupon-customer")
public class CouponCustomerController {

    @Autowired
    private CouponCustomerService customerService;

    /**
     * 用户请求优惠券
     *
     * @param request 请求优惠券的信息
     * @return 请求结果，包含优惠券详细信息
     */
    @PostMapping("requestCoupon")
    public Coupon requestCoupon(@Valid @RequestBody RequestCoupon request) {
        return customerService.requestCoupon(request);
    }

    /**
     * 用户删除优惠券
     *
     * @param userId 用户ID
     * @param couponId 优惠券ID
     */
    @DeleteMapping("deleteCoupon")
    public void deleteCoupon(@RequestParam("userId") Long userId,
                                       @RequestParam("couponId") Long couponId) {
        customerService.deleteCoupon(userId, couponId);
    }

    /**
     * 用户模拟计算每个优惠券的优惠价格
     *
     * @param order 模拟订单信息
     * @return 模拟计算的响应，包含每个优惠券的优惠后价格
     */
    @PostMapping("simulateOrder")
    public SimulationResponse simulate(@Valid @RequestBody SimulationOrder order) {
        return customerService.simulateOrderPrice(order);
    }

    /**
     * 用户下单结算
     *
     * @param info 购物车信息
     * @return 结算后的购物车信息
     */
    @PostMapping("placeOrder")
    public ShoppingCart checkout(@Valid @RequestBody ShoppingCart info) {
        return customerService.placeOrder(info);
    }

    /**
     * 用户查找优惠券
     *
     * @param request 搜索优惠券的请求信息
     * @return 查找到的优惠券列表
     */
    @PostMapping("findCoupon")
    public List<CouponInfo> findCoupon(@Valid @RequestBody SearchCoupon request) {
        return customerService.findCoupon(request);
    }

}

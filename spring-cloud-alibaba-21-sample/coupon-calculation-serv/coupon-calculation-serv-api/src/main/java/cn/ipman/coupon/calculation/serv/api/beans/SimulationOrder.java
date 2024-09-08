package cn.ipman.coupon.calculation.serv.api.beans;

import cn.ipman.coupon.template.api.beans.CouponInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 试算最优的优惠券
 *
 * @Author IpMan
 * @Date 2024/9/8 13:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationOrder {

    /**
     * 用户购买的商品列表
     * 不能为空，因为没有商品就无法计算优惠
     */
    @NotEmpty
    private List<Product> products;

    /**
     * 用户可用的优惠券ID列表
     * 不能为空，因为如果没有指定优惠券，就无法进行优惠计算
     */
    @NotEmpty
    private List<Long> couponIDs;

    /**
     * 优惠券详细信息列表
     * 该信息用于详细描述每张优惠券的规则和条件
     */
    private List<CouponInfo> couponInfos;

    /**
     * 用户ID
     * 不能为空，确保操作对应正确的用户
     */
    @NotNull
    private Long userId;

}

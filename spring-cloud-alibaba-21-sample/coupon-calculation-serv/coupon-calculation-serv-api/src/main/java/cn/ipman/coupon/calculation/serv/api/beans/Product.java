package cn.ipman.coupon.calculation.serv.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品信息
 *
 * @Author IpMan
 * @Date 2024/9/8 13:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    // 你可以试着搭建一个商品中心, 用来存储商品信息, 逐步完善这个应用
    private Long productId;

    // 商品的价格
    private long price;

    // 商品在购物车的数量
    private Integer count;

    // 商品销售的店铺id
    private Long shopId;
}

package cn.ipman.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠折扣
 *
 * @Author IpMan
 * @Date 2024/9/1 20:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount {

    // 满减 - 减掉的钱数
    // 折扣 - 90 = 9折,  95=95折
    private Long quota;

    // 最低达到多少消费才能用
    private Long threshold;

}

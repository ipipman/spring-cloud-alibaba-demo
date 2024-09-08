package cn.ipman.coupon.template.api.beans;

import cn.ipman.coupon.template.api.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 创建优惠券模板
 * 该类用于定义优惠券模板的基本信息和规则，包括优惠券的名称、类型、适用范围等
 *
 * @Author IpMan
 * @Date 2024/9/1 20:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateInfo {

    // 优惠券模板ID
    private Long id;

    // 优惠券名称
    @NotNull
    private String name;

    // 优惠券描述
    @NotNull
    private String desc;

    // 优惠券类型
    @NotNull
    private String type;

    // 适用门店 - 若无则为全店通用券
    private Long shopId;

    /**
     * 优惠券规则
     * 包含优惠券的使用规则和条件，如满减规则、有效期等
     */
    @NotNull
    private TemplateRule rule;


    // 优惠券是否可用
    // 可用于控制优惠券的启用状态
    private Boolean available;
}

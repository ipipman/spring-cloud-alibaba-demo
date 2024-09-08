package cn.ipman.coupon.calculation.serv.template;

import cn.ipman.coupon.calculation.serv.api.beans.ShoppingCart;

public interface RuleTemplate {

    // 计算优惠券
    ShoppingCart calculate(ShoppingCart settlement);
}

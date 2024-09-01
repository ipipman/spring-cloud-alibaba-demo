package cn.ipman.coupon.template.serv.dao.converter;

import cn.ipman.coupon.template.api.beans.rules.TemplateRule;
import com.alibaba.fastjson.JSON;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 优惠券核算规则-转换类
 *
 * @Author IpMan
 * @Date 2024/9/1 21:31
 */
@Converter
public class RuleConverter implements AttributeConverter<TemplateRule, String> {

    @Override
    @SuppressWarnings("all")
    public String convertToDatabaseColumn(TemplateRule rule) {
        return JSON.toJSONString(rule);
    }

    @Override
    @SuppressWarnings("all")
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule, TemplateRule.class);
    }
}

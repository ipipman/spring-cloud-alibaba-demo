package cn.ipman.coupon.template.serv.dao.converter;

import cn.ipman.coupon.template.api.enums.CouponType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 优惠卷类型-转换类
 *
 * @Author IpMan
 * @Date 2024/9/1 21:34
 */
@Converter
public class CouponTypeConverter
        implements AttributeConverter<CouponType, String> {

    @Override
    public String convertToDatabaseColumn(CouponType couponCategory) {
        return couponCategory.getCode();
    }

    @Override
    public CouponType convertToEntityAttribute(String code) {
        return CouponType.convert(code);
    }
}

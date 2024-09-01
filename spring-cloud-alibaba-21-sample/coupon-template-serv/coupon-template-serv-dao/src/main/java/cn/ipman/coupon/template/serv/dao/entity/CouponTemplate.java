package cn.ipman.coupon.template.serv.dao.entity;

import cn.ipman.coupon.template.api.beans.rules.TemplateRule;
import cn.ipman.coupon.template.api.enums.CouponType;
import cn.ipman.coupon.template.serv.dao.converter.CouponTypeConverter;
import cn.ipman.coupon.template.serv.dao.converter.RuleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券模板PO, 即持久化对象
 *
 * CREATE TABLE IF NOT EXISTS `geekbang_coupon_db`.`coupon_template` (
 * ->   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 * ->   `available` boolean NOT NULL DEFAULT false COMMENT '优惠券可用状态',
 * ->   `name` varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券名称',
 * ->   `description` varchar(256) NOT NULL DEFAULT '' COMMENT '优惠券详细信息',
 * ->   `type` varchar(10) NOT NULL DEFAULT '' COMMENT '优惠券类型，比如满减、随机立减、晚间双倍等等',
 * ->   `shop_id` bigint(20) COMMENT '优惠券适用的门店，如果是空则代表全场适用',
 * ->   `created_time` datetime NOT NULL DEFAULT '2021-12-13 00:00:00' COMMENT '创建时间',
 * ->   `rule` varchar(2000) NOT NULL DEFAULT '' COMMENT '详细的使用规则',
 * ->   PRIMARY KEY (`id`),
 * ->   KEY `idx_shop_id` (`shop_id`)
 * -> ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='优惠券模板';
 *
 * @Author IpMan
 * @Date 2024/9/1 21:04
 */
@Data
@SuppressWarnings("all")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
public class CouponTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 状态是否可用
    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "name", nullable = false)
    private String name;

    // 适用门店-如果为空, 则为全店满减券
    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "description", nullable = false)
    private String desc;

    // 优惠券类型
    @Column(name = "type", nullable = false)
    @Convert(converter = CouponTypeConverter.class)
    private CouponType category;

    // 创建时间，通过@CreateDate注解自动填值（需要配合@JpaAuditing注解在启动类上生效）
    @CreatedDate
    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    // 优惠券核算规则，平铺成JSON字段
    @Column(name = "rule", nullable = false)
    @Convert(converter = RuleConverter.class)
    private TemplateRule rule;


}

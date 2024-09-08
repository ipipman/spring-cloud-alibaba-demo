package cn.ipman.coupon.customer.serv.dao;

import cn.ipman.coupon.customer.serv.dao.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 优惠券数据访问接口
 * 该接口继承自JpaRepository，提供了对优惠券实体的基本数据操作
 *
 * @Author IpMan
 * @Date 2024/9/8 22:14
 */
public interface CouponDao extends JpaRepository<Coupon, Long> {

    /**
     * 根据用户ID和模板ID统计优惠券数量
     *
     * @param userId 用户ID
     * @param templateId 模板ID
     * @return 符合条件的优惠券数量
     */
    long countByUserIdAndTemplateId(Long userId, Long templateId);
}

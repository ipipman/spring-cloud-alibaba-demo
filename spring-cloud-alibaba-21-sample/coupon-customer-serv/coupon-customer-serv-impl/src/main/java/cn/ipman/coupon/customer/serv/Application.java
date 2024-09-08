package cn.ipman.coupon.customer.serv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "cn.ipman")
@EnableTransactionManagement
// 用于扫描Dao @Repository
@EnableJpaRepositories(basePackages = "cn.ipman")
// 用于扫描JPA实体类 @Entity, 默认扫当下路径
@EntityScan(basePackages = "cn.ipman")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("coupon-customer-serv started");
    }

}

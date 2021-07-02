package cn.lngex.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"cn.lngex.utils","cn.lngex.user"})
@EnableEurekaClient
@MapperScan("cn.lngex.user.mapper")
@EnableFeignClients("cn.lngex.auth.feign")
public class UserServerApp {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApp.class);
    }
}

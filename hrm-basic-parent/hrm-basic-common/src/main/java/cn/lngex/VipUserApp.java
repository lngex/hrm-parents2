package cn.lngex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.lngex.common.domain.vipuser.mapper")
public class VipUserApp {
    public static void main(String[] args) {
        SpringApplication.run(VipUserApp.class);
    }
}

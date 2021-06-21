package cn.lngex.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "cn.lngex")
@EnableEurekaClient
public class OssApp {
    public static void main(String[] args) {
        SpringApplication.run(OssApp.class);
    }
}

package cn.lngex.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"cn.lngex.search","cn.lngex.utils"})
@EnableEurekaClient
public class SearchServerApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchServerApp.class);
    }
}

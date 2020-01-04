package com.youngzi.contentcenter;

import com.youngzi.contentcenter.configuration.UserCenterFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.youngzi")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients//(defaultConfiguration = UserCenterFeignConfiguration.class)
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

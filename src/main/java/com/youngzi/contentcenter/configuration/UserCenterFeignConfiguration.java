package com.youngzi.contentcenter.configuration;


import feign.Logger;
import org.springframework.context.annotation.Bean;

//别加注解
//@Configuration
public class UserCenterFeignConfiguration {

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}

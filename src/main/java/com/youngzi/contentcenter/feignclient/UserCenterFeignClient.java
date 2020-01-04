package com.youngzi.contentcenter.feignclient;

import com.youngzi.contentcenter.configuration.UserCenterFeignConfiguration;
import com.youngzi.contentcenter.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-center")
//@FeignClient(value = "user-center",configuration = UserCenterFeignConfiguration.class)
public interface UserCenterFeignClient {
    @RequestMapping("/users/{id}")
    UserDTO findBy1Id(@PathVariable Integer id);

}


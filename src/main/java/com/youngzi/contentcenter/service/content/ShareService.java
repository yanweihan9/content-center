package com.youngzi.contentcenter.service.content;

import com.youngzi.contentcenter.dao.content.ShareMapper;
import com.youngzi.contentcenter.domain.entity.content.Share;
import com.youngzi.contentcenter.dto.content.ShareDTO;
import com.youngzi.contentcenter.dto.user.UserDTO;
import com.youngzi.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final ShareMapper shareMapper;

    private  final  RestTemplate restTemplate;

    private final UserCenterFeignClient userCenterFeignClient;

    public ShareDTO findById(Integer id) throws Exception {
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId = share.getUserId();
//        UserDTO userDto = restTemplate.getForObject(
//                "http://user-center/users/{userId}", UserDTO.class, userId);
        UserDTO userDto = userCenterFeignClient.findBy1Id(userId);
        share.setAuthor(userDto.getWxNickname());
        ShareDTO shareDto = new ShareDTO();
        BeanUtils.copyProperties(share,shareDto);
        shareDto.setWxNickName(userDto.getWxNickname());
        return shareDto;

    }
}

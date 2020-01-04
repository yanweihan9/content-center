package com.youngzi.contentcenter;

import com.youngzi.contentcenter.dao.content.ShareMapper;
import com.youngzi.contentcenter.domain.entity.content.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private DiscoveryClient discoveryClient ;

    @GetMapping("/test")
    private List<Share> testInsert() {
        Share share = new Share();
        share.setAuthor("ywh");
        share.setBuyCount(1);
        share.setCover("a");
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("ti");
        share.setPrice(11);
        shareMapper.insertSelective(share);
        List<Share> shares = shareMapper.selectAll();
        return shares;
    }

    @GetMapping("/test2")
    private List<ServiceInstance> test2() {
        return discoveryClient.getInstances("user-center");
    }


}

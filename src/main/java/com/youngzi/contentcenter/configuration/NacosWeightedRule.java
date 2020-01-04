package com.youngzi.contentcenter.configuration;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        //一般都设为空
    }

    @Override
    public Server choose(Object o) {
        try {
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            log.info("ln={}", loadBalancer);
            // 想要请求的微服务的名称
            String name = loadBalancer.getName();
            //实现负载均衡的算法
            // 拿到服务发现的相关api
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            // nacos client 自动通过基于权重的负载均衡算法，给我们选择一个实例。
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("port={}，instance={}", instance.getPort(), instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}

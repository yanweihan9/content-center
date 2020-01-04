package com.youngzi.contentcenter.configuration;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.google.common.collect.Lists;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        //一般都设为空
    }

    @Override
    public Server choose(Object o) {
        try {
            // 拿到文件中的集群名称
            String clusterName = nacosDiscoveryProperties.getClusterName();

            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            //log.info("ln={}", loadBalancer);
            // 想要请求的微服务的名称
            String name = loadBalancer.getName();
            //实现负载均衡的算法
            // 拿到服务发现的相关api
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();

            // 1. 找到指定服务的所有实例 A
            List<Instance> instances = namingService.selectInstances(name, true);
            // 2. 找到相同集群下的所有实例 B
            List<Instance> sameClusterInstances = instances.stream()
                    .filter(instance -> Objects.equals(instance.getClusterName(), clusterName))
                    .collect(Collectors.toList());
            // 3. 如果B为空，就用A
            List<Instance> instanceList = Lists.newArrayList();
            if (CollectionUtils.isEmpty(sameClusterInstances)) {
                instanceList = instances;
                log.warn("发生了跨集群的调用，name={},clusterName={},instance={}",
                        name, clusterName, instances);
            } else {
                instanceList = sameClusterInstances;
            }
            // 4. 基于权重的负载均衡算法，返回一个实例
            Instance instance = ExtendsBalancer.getHostByRandomWeight2(instanceList);
            log.info("port={},instance={}",instance.getPort(),instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("发生异常了", e);
        }
        return null;
    }

    static class ExtendsBalancer extends Balancer {
        public static Instance getHostByRandomWeight2(List<Instance> hosts) {
            return getHostByRandomWeight(hosts);
        }
    }

}

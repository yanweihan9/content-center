package com.youngzi.contentcenter.service.typeuse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class AppleService implements IFruit {

    @Override
    public TypeEnum type() {
        return TypeEnum.APPLE;
    }

    @Override
    public String hanle() {
        String price = "5人民币";
        log.info("------>   苹果一斤定价5元   <--------");
        return price;
    }

    @Override
    @Resource(type = AppleService.class)
    public <T extends IFruit> void init(T service) {
        FruitFactory.add(type(), service);
    }
}

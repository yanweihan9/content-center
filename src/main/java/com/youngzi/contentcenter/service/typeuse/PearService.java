package com.youngzi.contentcenter.service.typeuse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class PearService implements IFruit {

    @Override
    public TypeEnum type() {
        return TypeEnum.PEAR;
    }

    @Override
    public String hanle() {
        String price = "4人民币";
        log.info("------>   梨一斤定价4元   <--------");
        return price;
    }

    @Override
    @Resource(type = PearService.class)
    public <T extends IFruit> void init(T service) {
        FruitFactory.add(type(), service);
    }

}

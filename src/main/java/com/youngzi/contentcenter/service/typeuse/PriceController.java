package com.youngzi.contentcenter.service.typeuse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

    @GetMapping("/{type}")
    public String get(@PathVariable String type) {
        String price = FruitFactory.get(type).hanle();
        return price;
    }
}

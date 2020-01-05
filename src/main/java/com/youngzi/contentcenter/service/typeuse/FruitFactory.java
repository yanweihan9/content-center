package com.youngzi.contentcenter.service.typeuse;

import java.util.HashMap;
import java.util.Map;

public class FruitFactory {

    static Map<String, IFruit> typeMap = new HashMap<>();

    public static <T extends IFruit> void add(TypeEnum type, T service) {
        typeMap.put(type.getType(), service);
    }

    public static IFruit get(String type) {
        IFruit iFruit = typeMap.get(type);
        if (iFruit == null) {
            throw new RuntimeException("服务类型找不到");
        }
        return iFruit;
    }

}

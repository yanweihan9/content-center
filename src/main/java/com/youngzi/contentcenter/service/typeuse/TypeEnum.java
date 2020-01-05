package com.youngzi.contentcenter.service.typeuse;

import lombok.Getter;

@Getter
public enum TypeEnum {
    APPLE("apple"),
    PEAR("pear");

    private String type;

    TypeEnum(String type) {
        this.type = type;
    }
}

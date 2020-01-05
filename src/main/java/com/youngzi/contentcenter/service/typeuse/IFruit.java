package com.youngzi.contentcenter.service.typeuse;

public interface IFruit {

    /**
     * 水果类型
     *
     * @return
     */
    TypeEnum type();

    /**
     * 对水果的处理
     */
    String hanle();

    /**
     * 初始化
     */
    <T extends IFruit> void init(T service);
}

package com.lhc.cesignpattern.decorator_pattern.component;

/**
 * @ClassName DataSource
 * @Description 抽象构建：定义了读取和写入操作的通用数据接口
 * @Author Administrator
 * @Date 2023/8/11 20:06
 * @Version 1.0
 */
public interface DataSource {

    //写
    void writeData(String data);

    //读
    String readData();

}
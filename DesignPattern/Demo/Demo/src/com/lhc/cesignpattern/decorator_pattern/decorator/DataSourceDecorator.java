package com.lhc.cesignpattern.decorator_pattern.decorator;

import com.lhc.cesignpattern.decorator_pattern.component.DataSource;

/**
 * @ClassName DataSourceDecorator
 * @Description 抽象基础装饰
 * @Author Administrator
 * @Date 2023/8/11 20:08
 * @Version 1.0
 */
public class DataSourceDecorator implements DataSource {

    //引入 部件
    private DataSource wrappee;

    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
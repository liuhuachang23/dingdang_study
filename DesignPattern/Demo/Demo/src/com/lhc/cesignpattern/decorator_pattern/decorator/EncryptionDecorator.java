package com.lhc.cesignpattern.decorator_pattern.decorator;

import com.lhc.cesignpattern.decorator_pattern.component.DataSource;

import java.util.Base64;

/**
 * @ClassName EncryptionDecorator 加密装饰
 * @Description 加密装饰
 * @Author Administrator
 * @Date 2023/8/11 20:08
 * @Version 1.0
 */
public class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    @Override
    public String readData() {
        return decode(super.readData());
    }

    //编码
    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    //解码
    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}

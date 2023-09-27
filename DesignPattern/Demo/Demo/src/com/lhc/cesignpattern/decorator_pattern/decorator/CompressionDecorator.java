package com.lhc.cesignpattern.decorator_pattern.decorator;

import com.lhc.cesignpattern.decorator_pattern.component.DataSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @ClassName CompressionDecorator
 * @Description 压缩装饰
 * @Author Administrator
 * @Date 2023/8/11 20:08
 * @Version 1.0
 */
public class CompressionDecorator extends DataSourceDecorator {

    private int compLevel = 6;

    public CompressionDecorator(DataSource source) {
        super(source);
    }

    //写
    @Override
    public void writeData(String data) {
        super.writeData(compress(data));
    }

    //读
    @Override
    public String readData() {
        return decompress(super.readData());
    }

    //压缩
    private String compress(String stringData) {
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
            dos.write(data);
            dos.close();
            bout.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }

    //解压
    private String decompress(String stringData) {
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b = iin.read()) != -1) {
                bout.write(b);
            }
            in.close();
            iin.close();
            bout.close();
            return new String(bout.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }
}

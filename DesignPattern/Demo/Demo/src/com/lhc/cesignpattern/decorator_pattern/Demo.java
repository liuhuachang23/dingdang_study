package com.lhc.cesignpattern.decorator_pattern;

import com.lhc.cesignpattern.decorator_pattern.component.DataSource;
import com.lhc.cesignpattern.decorator_pattern.component.FileDataSource;
import com.lhc.cesignpattern.decorator_pattern.decorator.CompressionDecorator;
import com.lhc.cesignpattern.decorator_pattern.decorator.DataSourceDecorator;
import com.lhc.cesignpattern.decorator_pattern.decorator.EncryptionDecorator;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author Administrator
 * @Date 2023/8/11 20:09
 * @Version 1.0
 */
public class Demo {
    public static void main(String[] args) {

        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(             //压缩装饰
                new EncryptionDecorator(                                    //加密装饰
                        new FileDataSource("out/OutputDemo.txt")
                )
        );

        System.out.println("- 输入数据 ----------------");
        System.out.println(salaryRecords);

        System.out.println("- 写入文件 并且 压缩-->加密 ----------------------");
        encoded.writeData(salaryRecords);

        System.out.println("- 读取文件 --------------");
        DataSource plain = new FileDataSource("out/OutputDemo.txt");
        System.out.println(plain.readData());

        System.out.println("- 读取文件 并且 解密 解压 --------------");
        System.out.println(encoded.readData());

    }
}

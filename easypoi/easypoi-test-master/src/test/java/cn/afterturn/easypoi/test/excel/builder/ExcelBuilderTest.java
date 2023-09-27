package cn.afterturn.easypoi.test.excel.builder;

import cn.afterturn.easypoi.excel.ExcelBuilder;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExcelBuilder 测试类
 */
public class ExcelBuilderTest {

    @Test
    public void addOneRow() {
        ExcelBuilder eb = ExcelBuilder.create().setRowHeight(20);
        CellStyle cellStyle = getHeaderStyle(eb.getWorkbook());
        //生成表头
        eb.addOneRow("小明自己生成的表格", 22, 50, getHeaderStyle(eb.getWorkbook()))
                .addOneRow("（共×名）", 22)
                //生成3行空数据，避免下面创建行
                .addNoneRow().addNoneRow().addNoneRow()
                .addOneCell("把握原则", 3, 0, 4, 0)
                .addOneCell("2022年6月（测算时间1）", 3, 1, 3, 7)
                .addOneCell("2022年12月（测算时间2）", 3, 8, 3, 14)
                .addOneCell("2023年6月（测算时间3）", 3, 15, 3, 21);
        String[] names = new String[]{"姓名", "出生\n年月", "上班\n时间", "年限", "转正时间", "年限", "备注"};
        //这里可以动态生成几个
        eb.addCells(names, 4, 1)
                .addCells(names, 4, 8)
                .addCells(names, 4, 15);
        //先生成主要的数据，再生成条件N，避免没空row的问题
        List<Map> dataList = new ArrayList<>();
        Map<String, String> data = new HashMap<>();
        data.put("name", "张三");
        data.put("birthday", "1965.02");
        data.put("leveltime", "2015.04");
        data.put("years", "20");
        data.put("jobytime", "2008.06");
        data.put("jobyyears", "5");
        data.put("remark", "");
        dataList.add(data);
        dataList.add(data);
        dataList.add(data);
        dataList.add(data);
        dataList.add(data);
        dataList.add(data);
        dataList.add(data);
        dataList.add(data);
        dataList.add(data);
        String[] cellNames = new String[]{"name", "birthday", "leveltime", "years", "jobytime", "jobyyears", "remark"};
        eb.addRows(dataList, cellNames, 5, 1,BorderStyle.THIN)
                .addOneCell("条件1", 5, 0, 5 + dataList.size() - 1, 0,BorderStyle.THIN);
        dataList.remove(0);
        eb.addRows(dataList, cellNames, 5, 8);
        dataList.remove(0);
        eb.addRows(dataList, cellNames, 5, 15);
        // 换一行新的
        eb.addRows(dataList, cellNames, 14, 1)
                .addOneCell("条件2", 14, 0, 14 + dataList.size() - 1, 0);
        dataList.remove(0);
        eb.addRows(dataList, cellNames, 14, 8);
        dataList.remove(0);
        eb.addRows(dataList, cellNames, 14, 15);

        eb.write("D:\\home\\excel\\自定义组合excel.xlsx");
    }

    public CellStyle getHeaderStyle(Workbook workbook) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 30);
        titleStyle.setFont(font);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return titleStyle;
    }
}

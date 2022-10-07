package com.software.statisticssystem.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WriteExcel {
    public void writeExcel(List<Map<String, Object>> list) throws IOException, WriteException {
        String[] title = new String[]{"姓名", "学号", "班级"};
        String fileName = "./src/main/resources/static/Excel/list.xlsx";
        File file = new File(fileName);
        // 判断file是否存在
        if (file.exists()) {
            file.delete();
            System.out.println("原有表单删除成功");
        }
        file.createNewFile();
        // 创建工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet sheet1 = workbook.createSheet("sheet1", 0);
        Label label = null;
        //设置列名
        for (int j = 0; j < title.length; j++) {
            label = new Label(j, 0, title[j]);
            sheet1.addCell(label);
        }
        System.out.println(list);
        // 方便定义行
        int count = 0;
        // 将list中的数据添加至工作簿中
        for (Map<String, Object> stringObjectMap : list) {
            count++;
            label = new Label(0, count, (String) stringObjectMap.get("stu_name"));
            sheet1.addCell(label);
            label = new Label(1, count, (String) stringObjectMap.get("stu_id"));
            sheet1.addCell(label);
            label = new Label(2, count, (String) stringObjectMap.get("stu_class"));
            sheet1.addCell(label);
        }
        workbook.write();
        workbook.close();
    }
}

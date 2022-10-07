package com.software.statisticssystem;

import com.software.statisticssystem.File.ReadCsv;
import com.software.statisticssystem.File.ReadExcel;
import com.software.statisticssystem.File.WriteExcel;

import java.sql.ResultSet;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        // 读年级总名单
        ReadExcel readExcel = new ReadExcel();
        List studentList = readExcel.excelRead("./src/main/resources/static/ExcelDate/GradeList.xlsx", 244);
        // 传入导出的csv数据
        ReadCsv readCsv = new ReadCsv();
        List<String[]> CSVList = readCsv.csvRead("./src/main/resources/static/CSV/test.csv");
        // 读出csv中已完成名单数据
        List finishList = new ArrayList();
        for (String[] strings : CSVList) {
            finishList.add(Arrays.asList(strings).get(2));
        }
        // 创建未完成名单
        List<String> unFinishList = new ArrayList<>();
        for (Object str : studentList) {
            if (!finishList.contains(str)) {
                unFinishList.add((String) str);
            }
        }
        System.out.println(unFinishList);
        // 查询未完成同学所有信息
        SqlDB sqlDB = new SqlDB();
        String sql = "SELECT * FROM studentList WHERE stu_name = ";
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < unFinishList.size(); i++) {
            ResultSet resultSet = sqlDB.query(sql + "'" + unFinishList.get(i) + "'");
            while (resultSet.next()) {
                String stu_id = resultSet.getObject("stu_id").toString();
                String stu_name = resultSet.getObject("stu_name").toString();
                String stu_class = resultSet.getObject("stu_class").toString();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("stu_id", stu_id);
                hashMap.put("stu_name", stu_name);
                hashMap.put("stu_class", stu_class);
                list.add(i, hashMap);
            }
            sqlDB.close();
        }
        System.out.println(list);
        // 导出未完成名单Excel格式文件
        WriteExcel writeExcel = new WriteExcel();
        writeExcel.writeExcel(list);
    }
}


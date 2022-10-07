package com.software.statisticssystem.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadExcel {
    public List excelRead(String FileUrl, int number) throws IOException {
        File inputFile = new File(FileUrl);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(inputFile));
        int tabIndex = 0;
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        Row row = null;
        Cell cell1 = null;
        List<String> DataList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            row = sheet.getRow(i + 1);     //指定行
            cell1 = row.getCell(2);  //指定列
            assert false;
            cell1.setCellType(CellType.STRING);
            String cellValue = cell1.getStringCellValue();
            DataList.add(cellValue);
        }
        return DataList;
    }
}

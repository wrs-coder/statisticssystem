package com.software.statisticssystem.File;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.util.List;


public class ReadCsv {
    public List<String[]> csvRead(String csvURL) {
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setLineSeparatorDetectionEnabled(true);
        RowListProcessor rowListProcessor = new RowListProcessor();
        parserSettings.setProcessor(rowListProcessor);
        parserSettings.setHeaderExtractionEnabled(true);
        parserSettings.setLineSeparatorDetectionEnabled(true);
        CsvParser parser = new CsvParser(parserSettings);
        parser.parse(new File(csvURL));
        String[] headers = rowListProcessor.getHeaders();
        List<String[]> rows = rowListProcessor.getRows();
        return rows;
    }
}

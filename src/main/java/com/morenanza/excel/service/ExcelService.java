package com.morenanza.excel.service;

import com.morenanza.excel.model.SheetData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface ExcelService {
    void generateExcel(Map<String, SheetData> sheetDataMap, String fileName) throws IOException;
}

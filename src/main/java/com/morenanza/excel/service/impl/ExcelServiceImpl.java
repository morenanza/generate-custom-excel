package com.morenanza.excel.service.impl;

import com.morenanza.excel.model.SheetData;
import com.morenanza.excel.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void generateExcel(Map<String, SheetData> sheetDataMap, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        for(String sheetName : sheetDataMap.keySet()){
            SheetData sheetData = sheetDataMap.get(sheetName);
            createSheet(workbook, sheetData.getData(), sheetName, sheetData.getHeader());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("./export/"+fileName));
            outputStream.writeTo(fos);
        } catch(IOException ioe) {
            // Handle exception here
            ioe.printStackTrace();
        } finally {
            assert fos != null;
            fos.close();
        }
        workbook.close();

    }

    private void createSheet(Workbook workbook, List<Object[]> data, String sheetName, String[] header){
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(false);
        Sheet sheetList = workbook.createSheet(sheetName);
        createHeader(workbook, sheetList, header);
        for(int i=0; i<data.size(); i++){
            addSheetRow(sheetList, i + 1, style, data.get(i));
        }
    }

    private void addSheetRow(Sheet sheet, int numRow, CellStyle style, Object[] fields){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        Row row = sheet.createRow(numRow);

        for(int i = 0; i<fields.length; i++){
            Cell cell = row.createCell(i);
            if(fields[i] == null){
                cell.setCellValue("");
            } else {
                if(fields[i] instanceof String){
                    String field = (String) fields[i];
                    cell.setCellValue(field);
                } else if(fields[i] instanceof BigInteger){
                    BigInteger field = (BigInteger) fields[i];
                    cell.setCellValue(field.intValue());
                } else if(fields[i] instanceof Date) {
                    Date field = (Date) fields[i];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    cell.setCellValue(sdf.format(field));
                } else if(fields[i] instanceof Integer){
                    Integer field = (Integer) fields[i];
                    cell.setCellValue(field);
                } else if(fields[i] instanceof Double){
                    Double field = (Double) fields[i];
                    cell.setCellValue(field);
                }
            }
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
    }

    private void createHeader(Workbook workbook, Sheet sheet, String[] headerColumn){
        Row headerList = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 11);
        font.setBold(true);
        headerStyle.setFont(font);
        for(int i = 0; i<headerColumn.length; i++){
            Cell headerCell = headerList.createCell(i);
            headerCell.setCellValue(headerColumn[i]);
            headerCell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);
        }
    }
}

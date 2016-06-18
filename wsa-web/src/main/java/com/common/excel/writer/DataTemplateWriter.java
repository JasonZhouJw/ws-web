/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.excel.writer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: DataTemplateWriter.java, v 0.1 2016年4月14日 上午11:05:38 ZHOUJINGWEI598 Exp $
 */
public class DataTemplateWriter {

    public String writeDataTemplate(String url, String data) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(url);
            HSSFWorkbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Input");
            sheet.protectSheet("123");
            Row row = sheet.createRow(1);
            Cell cell = row.createCell(1);
            CellStyle ss = workbook.createCellStyle();
            ss.setLocked(false);
            cell.setCellStyle(ss);
            cell.setCellValue(data);
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

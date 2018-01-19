package com.project.test.apache_poi;


import android.content.res.AssetManager;
import android.os.Environment;

import org.apache.poi.common.usermodel.HyperlinkType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import android.content.res.AssetManager;

public class CreateDocument {

    public void a()throws Exception  {

        //Blank Document
        XWPFDocument document = new XWPFDocument();
        System.out.println("Radiiii");
        String content = "hello world";
        File file;
        FileOutputStream outputStream;
        
        try {

            file = new File(Environment.getExternalStorageDirectory(), "AAAA.docx");

            FileOutputStream out = new FileOutputStream(file);
           document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Write the Document in file system

        //document.write(out);
        document.close();
        System.out.println("createdocument.docx written successully");
    }
}
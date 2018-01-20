package com.project.test.apache_poi;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class CreateDocument {

    public void a(Context context, List<String>values3 , List<String>values2, List<String>values4, List<String> values5)throws Exception  {

        AssetManager am = context.getAssets();

        try {

            XWPFDocument document = new XWPFDocument(am.open("report_template.docx"));

            FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory()+ "/report.docx");
            replaceValuesInTable2(document,values2);
            replaceValuesInTable3(document,values3);
            replaceValuesInTable4(document,values4);
            replaceValuesInTable5(document,values5);

            document.write(out);
            document.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Report.docx written succesfully!");
    }

private void replaceValuesInTable3(XWPFDocument document, List<String>values){



    XWPFTable tbl = document.getTables().get(2);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Vrsta_intervencije")) {
                            System.out.println(  " BROJ TABLICE TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU dasdasd");
                            text = text.replace("Vrsta_intervencije", values.get(0));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Tip_intervencije")) {
                            text = text.replace("Tip_intervencije", values.get(1));
                            r.setText(text, 0);
                        }
                    }
                }
            }


    }

}

    private void replaceValuesInTable2(XWPFDocument document, List<String>values){



        XWPFTable tbl = document.getTables().get(1);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Date_call")) {

                            text = text.replace("Date_call", values.get(0));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Time_call")) {
                            text = text.replace("Time_call", values.get(1));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Date_start")) {
                            text = text.replace("Date_start", values.get(2));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Time_start")) {
                            text = text.replace("Time_start", values.get(3));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Date_arrival")) {
                            text = text.replace("Date_arrival", values.get(4));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Time_arrival")) {
                            text = text.replace("Time_arrival", values.get(5));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Date_localized")) {
                            text = text.replace("Date_localized", values.get(6));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Time_localized")) {
                            text = text.replace("Time_localized", values.get(7));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Date_Extinguished")) {
                            text = text.replace("Date_Extinguished", values.get(8));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Time_Extinguished")) {
                            text = text.replace("Time_Extinguished", values.get(9));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Date_end")) {
                            text = text.replace("Date_end", values.get(10));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Time_end")) {
                            text = text.replace("Time_end", values.get(11));
                            r.setText(text, 0);
                        }
                    }
                }
            }


        }

    }
    private void replaceValuesInTable4(XWPFDocument document, List<String>values) {


        XWPFTable tbl = document.getTables().get(3);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Destroyed_space")) {
                            System.out.println(" BROJ TABLICE TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU dasdasd");
                            text = text.replace("Destroyed_space", values.get(1));
                            r.setText(text, 0);
                        }

                    }
                }
            }


        }
    }

    private void replaceValuesInTable5(XWPFDocument document, List<String>values) {


        XWPFTable tbl = document.getTables().get(4);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Location_city")) {

                            text = text.replace("Location_city", values.get(0));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Location_place")) {

                            text = text.replace("Location_place", values.get(1));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Location_street")) {

                            text = text.replace("Location_street", values.get(2));
                            r.setText(text, 0);
                        }

                    }
                }
            }


        }
    }



    }
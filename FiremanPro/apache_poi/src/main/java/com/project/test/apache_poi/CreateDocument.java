package com.project.test.apache_poi;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.java.awt.Color;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class CreateDocument {

    public void a(Context context, List<String>values3 , List<String>values2, List<String>values4, List<String> values5, String values7,List<String>values8,List<String>values9, List<String>values10, List<String>values11, List<String>values12)throws Exception  {

        AssetManager am = context.getAssets();

        try {
            System.out.println();
            XWPFDocument document = new XWPFDocument(am.open("report_template.docx"));

            FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory()+ "/report.docx");
            System.out.println(document.getTables().size() + " NUMBER OF TABLES!");
            replaceValuesInTable2(document,values2);
            replaceValuesInTable3(document,values3);
            replaceValuesInTable4(document,values4);
            replaceValuesInTable5(document,values5);
            replaceValuesInTable7(document,values7);
            replaceValuesInTable8(document,values8);
            replaceValuesInTable9(document,values9);
            replaceValuesInTable10(document,values10);
            replaceValuesInTable11(document,values11);
            replaceValuesInTable12(document, values12);

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

    if (values.size() > 0) {
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
                        if (text != null && text.contains(values.get(0))) {
                            System.out.println(" BROJ TABLICE TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU dasdasd");


                            cell.setColor("575757");
                        }
                        if (text != null && text.contains(values.get(2))) {
                            System.out.println(" BROJ TABLICE TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU dasdasd");

                            cell.setColor("575757");
                        }
                        if (text != null && text.contains(values.get(3))) {
                            System.out.println(" BROJ TABLICE TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU dasdasd");

                            cell.setColor("575757");
                        }

                        if (text != null && text.contains(values.get(4))) {
                            System.out.println(" BROJ TABLICE TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU dasdasd");

                            cell.setColor("575757");
                        }
                        if (text != null && text.contains(values.get(5))) {
                            System.out.println(" BROJ TABLICE TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU dasdasd");

                            cell.setColor("575757");
                        }


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

    private void replaceValuesInTable7(XWPFDocument document, String values) {


        XWPFTable tbl = document.getTables().get(6);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Description")) {

                            text = text.replace("Description", values);
                            r.setText(text, 0);
                        }

                    }
                }
            }


        }
    }
    private void replaceValuesInTable8(XWPFDocument document, List<String> values) {


        XWPFTable tbl = document.getTables().get(7);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("number_surface")) {

                            text = text.replace("number_surface", values.get(0));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("number_out")) {

                            text = text.replace("number_out", values.get(1));
                            r.setText(text, 0);
                        }

                    }
                }
            }


        }
    }
    private void replaceValuesInTable9(XWPFDocument document, List<String> values) {


        XWPFTable tbl = document.getTables().get(8);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Public_service")) {

                            text = text.replace("Public_service", values.get(0));
                            r.setText(text, 0);
                        }


                    }
                }
            }


        }
    }
    private void replaceValuesInTable10(XWPFDocument document, List<String> values) {


        XWPFTable tbl = document.getTables().get(9);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Navalno_vrsta")) {

                            text = text.replace("Navalno_vrsta", values.get(0));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Navalno_sati")) {

                            text = text.replace("Navalno_sati", values.get(1));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Kombi_vrsta")) {

                            text = text.replace("Kombi_vrsta", values.get(2));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Kombi_sati")) {

                            text = text.replace("Kombi_sati", values.get(3));
                            r.setText(text, 0);
                        }


                    }
                }
            }


        }
    }
    private void replaceValuesInTable11(XWPFDocument document, List<String> values) {


        XWPFTable tbl = document.getTables().get(10);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Nav_cijena")) {

                            text = text.replace("Nav_cijena", values.get(0));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Com_cijena")) {

                            text = text.replace("Com_cijena", values.get(1));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Teh_cijena")) {

                            text = text.replace("Teh_cijena", values.get(2));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Ladder_Cijena")) {

                            text = text.replace("Ladder_Cijena", values.get(3));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Tanker_cijena")) {

                            text = text.replace("Tanker_cijena", values.get(4));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Vehicle_cijena")) {

                            text = text.replace("Vehicle_cijena", values.get(5));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Trans_cijena")) {

                            text = text.replace("Trans_cijena", values.get(6));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("KM_prijedjeni")) {

                            text = text.replace("KM_prijedjeni", values.get(7));
                            r.setText(text, 0);
                        }


                        if (text != null && text.contains("Sat_rada")) {

                            text = text.replace("Sat_rada", values.get(8));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Osiguranje_s_voz")) {

                            text = text.replace("Osiguranje_s_voz", values.get(9));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Elektro_pump")) {

                            text = text.replace("Elektro_pump", values.get(10));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Prah_za_gasenje")) {

                            text = text.replace("Prah_za_gasenje", values.get(11));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("CO2_Kg")) {

                            text = text.replace("CO2_Kg", values.get(12));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Pjenilo_kg")) {

                            text = text.replace("Pjenilo_kg", values.get(13));
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Apsorbent_kg")) {

                            text = text.replace("Apsorbent_kg", values.get(14));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Ostali_troskovi")) {

                            text = text.replace("Ostali_troskovi", values.get(15));
                            r.setText(text, 0);
                        }



                    }
                }
            }


        }
    }
    private void replaceValuesInTable12(XWPFDocument document, List<String> values) {


        XWPFTable tbl = document.getTables().get(11);
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);

                        if (text != null && text.contains("Sudonik_1")) {

                            text = text.replace("Sudonik_1", values.get(0));
                            r.setText(text, 0);
                        }


                    }
                }
            }


        }
    }

    }
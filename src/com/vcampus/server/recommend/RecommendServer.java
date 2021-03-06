package com.vcampus.server.recommend;

import com.vcampus.client.main.recommend.AppRec;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class RecommendServer {
    static String  local_path = System.getProperty("user.dir");
    static String filepath = local_path+"/src/resources/assets/CollegeRank.xlsx";

    public static String getRec(HashMap stuInfo)
    {
        return getCollege(CalcuScore(stuInfo));
    }
    public static int CalcuScore(HashMap StuInfo)
    {
        int score;
        String college = StuInfo.get("uni").toString();
        String major = StuInfo.get("major").toString();
        int gaokao = (int) StuInfo.get("gaokao");
        int CET4 = (int) StuInfo.get("CET4");
        int CET6 = (int) StuInfo.get("CET6");
        int GPA = (int) StuInfo.get("GPA");
        int math = (int) StuInfo.get("math");
        int majorGPA = (int) StuInfo.get("majorGPA");
        score = (gaokao + CET4/710*100 + CET6/710*100 + GPA + math + majorGPA)/6;
        System.out.print(StuInfo);
        return score;
    }
    public static String getCollege(int index){
        String college = null;
        List<List<Object>> rank;
        rank = getRank();
        int recIndex = (int) ((rank.size()-1) * (100 - index)/100);
        System.out.print(recIndex);
        college = rank.get(recIndex+1).get(1).toString();
        return college;
    }

    public static List<List<Object>> getRank(){
        List<List<Object>> sheetList = new ArrayList<>();
        try {
            Workbook wb = openExcel(filepath);
            Sheet sheet = wb.getSheetAt(0);

            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                // ???????????????
                Row row = sheet.getRow(rowNum);
                // ???????????????????????????List
                List<Object> rowList = new ArrayList<>();
                // ?????????
                for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                    Cell cell = sheet.getRow(rowNum).getCell(cellNum);
                    rowList.add(getStringCellValue(cell));
                }
                sheetList.add(rowList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return sheetList;

    }
    public static Workbook openExcel(String filepath){
        try {
            InputStream is = new FileInputStream(filepath);
        return new XSSFWorkbook(is);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getStringCellValue(Cell cell) {
        String cellvalue = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                cellvalue = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = cell.getDateCellValue();
                    cellvalue = sdf.format(date);
                } else {
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellvalue = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                cellvalue = "";
                break;
            default:
                cellvalue = "";
                break;
        }
        if (cellvalue == "") {
            return "";
        }
        return cellvalue;
    }
    public static void main(String[] args){
        RecommendServer app = new RecommendServer();
        System.out.print((app.getCollege(1)));
        ;
    }
}

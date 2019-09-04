package com.scorer.client.tools;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minidev.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ExcelUtils {

    public static String getTimetableData(MultipartFile timetableFile) {
        String resultData = "";
        try {
            //根据路径获取这个操作excel的实例
            HSSFWorkbook wb = new HSSFWorkbook(timetableFile.getInputStream());
            //获取第一页
            HSSFSheet sheet = wb.getSheetAt(0);
            String[][] timetable = new String[7][8];
            //从第二行第三列开始遍历
            //循环sesheet页中数据从第二行开始，第一行是标题
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                //获取每一行数据
                HSSFRow row  = sheet.getRow(i);
                for(int colIndex = 2; colIndex < 9;colIndex++){
                    if(row.getCell(colIndex)!=null){
                        row.getCell(colIndex).setCellType(Cell.CELL_TYPE_STRING);
                        timetable[colIndex-2][i-1] = row.getCell(colIndex).getStringCellValue();
                    }
                }
            }
            Gson gson = new Gson();
            resultData = gson.toJson(timetable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    public static void exportFileBak(HttpServletResponse response){
        String fileName = "timetable.xlsx";
        String tempFilePath = "/static/" + fileName;
        InputStream f= ExcelUtils.class.getResourceAsStream(tempFilePath);
        response.reset();
        response.setContentType("application/x-msdownload;charset=utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            ServletOutputStream sout = response.getOutputStream();
            bis = new BufferedInputStream(f);
            bos = new BufferedOutputStream(sout);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
            bos.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ResponseEntity<byte[]> exportFile(HttpServletResponse response){
        String fileName = "timetable.xlsx";
        String tempFilePath = "/static/" + fileName;
        InputStream f= ExcelUtils.class.getResourceAsStream(tempFilePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-_Stream");
        headers.add("Connection", "close");
        headers.add("Accept-Ranges", "bytes");
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        byte[] res = readInputStream(f);
        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     */
    private static byte[] readInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len;
        boolean ck = false;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                ck=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] reByte = ck?bos.toByteArray():null;
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reByte;
    }
}

package com.example.springs.controller;

import com.example.springs.PropertiesTemplateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘金龙
 * @title: DocController
 * @projectName spring-s
 * @description: TODO
 * @date 2019/5/109:51
 */
public class DocController {


    /**
     * 区模板下载  武汉2
     * @return
     */
    @RequestMapping("downloadModelarea")
    public static void downloadModelarea(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String templatePath ;
        //readTemplate 是一个方法
        templatePath =readTemplate("666.xls");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Access-Control-Expose-Headers", "fileName");
        response.setHeader("fileName", URLEncoder.encode("填报模板","UTF-8"));
        response.setHeader("Content-Disposition", "attachment;filename=" .concat(String.valueOf(URLEncoder.encode("核查企业导入模板.xlsx", "UTF-8"))));

        //获取输入流
        bis = new BufferedInputStream(new FileInputStream(templatePath));
        //输出流
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        //关闭流
        bis.close();
        bos.close();
    }



    public static  String readTemplate(String filename){
        String strPath = "";
        if(System.getProperty("os.name").contains("Windows")){
            strPath = PropertiesTemplateUtils.getValue("excelUrlWindows");
        }else if(System.getProperty("os.name").contains("Linux")){
            strPath = PropertiesTemplateUtils.getValue("excelUrlLinux");
        }
        String templatePath = strPath+filename;
        return templatePath;
    }

    public static void main(String[] args) {



    }

    public  void  readexcel(){
   /* InputStream is = new FileInputStream("");
    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
    Student student = null;
    List<Student> list = new ArrayList<Student>();
    // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
        if (hssfSheet == null) {
            continue;
        }
        //判断企业数量
        //int num = 0 ;
        // 循环行Row
        //		HashMap<Object, Object> hashMap = new HashMap<>();
        //		hashMap.put("company","区排查案例");
        //		hashMap.put("city","");
        //		JSONArray array=JSONArray.fromObject(hashMap);

        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow != null) {

                student = new Student();
                HSSFCell no = hssfRow.getCell(0);
                HSSFCell name = hssfRow.getCell(1);
                HSSFCell age = hssfRow.getCell(2);
                HSSFCell score = hssfRow.getCell(3);
                student.setNo(getValue(no));
                student.setName(getValue(name));
                student.setAge(getValue(age));
                student.setScore(Float.valueOf(getValue(score)));
                list.add(student);
                num++;

            }             }
    }
            return list;*/

    }

}

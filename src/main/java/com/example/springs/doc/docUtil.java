package com.example.springs.doc;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class docUtil {

    private static Logger log=Logger.getLogger(String.valueOf(docUtil.class));

    public static void main(String[] args) throws Exception {

    //    testWrite();
        downzip();
    }



    public static void testWrite() throws Exception {
        String templatePath = "D:\\3.doc";
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //把range范围内的${reportDate}替换为当前的日期
        range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        range.replaceText("{name}", "狗贼");
        range.replaceText("{info}", "无耻狗贼安敢在次饶舌");

        OutputStream os = new FileOutputStream("D:\\write.docx");
        //把doc输出到输出流中
        doc.write(os);
        closeStream(os);
        closeStream(is);
    }
    /**
     * 关闭输入流
     * @param is
     */
    private static  void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭输出流
     * @param os
     */
    private static void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File createNewFile(File file)throws IOException {
        if (!file.exists()) {
            mkdir(file.getParentFile());
            file.createNewFile();
        }
        return file;
    }

    private static void mkdir(File dir){
        if (!dir.getParentFile().exists()) {
            mkdir(dir.getParentFile());
        }
        dir.mkdir();
    }



    /*public static void getBuild(String  tmpFile, Map<String, String> contentMap, String exportFile){

        InputStream inputStream = docUtil.class.getClassLoader().getResourceAsStream(tmpFile);
//        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(tmpFile);
        HWPFDocument document = null;
        try {
            document = new HWPFDocument(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }

        //导出到文件
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.write(byteArrayOutputStream);
            OutputStream outputStream = new FileOutputStream(exportFile);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    public static  void downzip() throws Exception {
        byte[] buffer = new byte[1024];
        // 生成的ZIP文件名为Demo.zip
        String strZipName = "D:/Demo.zip";//生成文件的目录及命令
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipName));
        // 需要同时下载的两个文件result.txt ，source.txt
        File[] file1 = { new File("C:/specialReportFile/0f1d26b9-6ad7-479c-a1b1-dfe865adae65.docx"), new File("C:/specialReportFile/2f7d8b23-ba4e-41dc-8569-22ad0da09c90.jpg"), new File("C:/specialReportFile/4b2ac73d-c1b5-4ac8-93cc-bf557dc2dfe1.png")};
        for (int i = 0; i < file1.length; i++){
            int i1= i+1;
            String fname= "附件"+i1;
            String substring = file1[i].toString().substring(file1[i].toString().lastIndexOf(".") + 1);
            fname  =    fname+"."+substring;
            FileInputStream fis = new FileInputStream(file1[i]);
            out.putNextEntry(new ZipEntry(fname));
            int len;
            // 读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();

        }
        out.close();
        System.out.println("生成Demo.zip成功");

    }



}

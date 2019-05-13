package com.example.springs;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author 刘金龙
 * @title: exportUtil
 * @projectName spring-s
 * @description: TODO
 * @date 2019/5/109:36
 */
public class exportUtil {

    public  void   exportFileUtil(String name , String url, HttpServletResponse response){
        String pathstr="";
        if(System.getProperty("os.name").contains("Windows")){
            pathstr = PropertiesUtils.getValue("uploadImgUrlWindows");
        }else if(System.getProperty("os.name").contains("Linux")){
            pathstr = PropertiesUtils.getValue("uploadImgUrlLinux");
        }
        try {
            String u = pathstr +""+ url;
            File file = new File(u);
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            //创建文件输入流
            FileInputStream in = null;
            in = new FileInputStream(file);
            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓存区
            byte buffer[] = new byte[1024];
            int len = 0;
            while((len = in.read(buffer))>0) {
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

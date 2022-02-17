package controller;

import util.HttpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class Download {

   public  static  String downFile(String uuid) throws Exception {
       HttpURLConnection connection = HttpUtil.creatUtil("http://127.0.0.1:8088/file/download", "GET");
       connection.setRequestProperty("uuid",uuid);
       connection.connect();
       if(connection.getResponseCode()!=200){
           return "状态码"+connection.getResponseCode();
       }
       else{
           InputStream in= connection.getInputStream();
            String fileType= connection.getHeaderField("filetype");
           //创建目录
           File directory = new File("D:\\qiyuesuo2\\");
           if (!directory.exists()) {
               if (!directory.mkdirs()) {
                   return "创建目录失败";
               }
               System.out.println("创建目录成功");
           }
           OutputStream out=new FileOutputStream("D:\\qiyuesuo2\\"+uuid+fileType);
           byte[] buf=new byte[1024];
           int bytesRead;
           while ((bytesRead=in.read(buf))>0){
               out.write(buf,0,bytesRead);
           }
            return "下载成功";
       }
   }
}

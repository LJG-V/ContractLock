package controller;

import util.HttpUtil;

import java.io.*;
import java.net.HttpURLConnection;

public class Upload {
    public static String uploadFile(String uploadFilePaths) throws Exception {

        HttpURLConnection connection = HttpUtil.creatUtil("http://127.0.0.1:8088/file/upload", "POST");
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "---------------------------";
        String response = "";
        /**
         * 模仿表单请求的格式
         */
        try{
            //设置请求参数
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            //获取请求内容输出流
            DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
            String fileType=uploadFilePaths.substring(uploadFilePaths.indexOf("."));
            File file=new File(uploadFilePaths);
            if (!file.exists()){
                return "指定文件路劲不存在";
            }
            String fileName = file.getName();
            //写文件
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " + "name=\"file\"; " + "filename=\"");
            //防止中文乱码
            ds.write(fileName.getBytes());
            ds.writeBytes("\"" + end);
            ds.writeBytes("Content-Type: " + fileType + end);
            ds.writeBytes(end);
            //根据路径读取文件
            FileInputStream fis = new FileInputStream(uploadFilePaths);
            byte[] buffer = new byte[1024];
            int length = -1;
            while((length = fis.read(buffer)) != -1){
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            fis.close();
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            ds.writeBytes(end);
            ds.flush();
            try{
                //获取URL的响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String s = "";
                String temp = "";
                while((temp = reader.readLine()) != null){
                    s += temp;
                }
                response = s;
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            ds.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("上传失败");
        }
        return response;
    }
}

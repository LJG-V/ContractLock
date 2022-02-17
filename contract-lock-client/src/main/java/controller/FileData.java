package controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.HttpUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class FileData {

    public static JSONObject getData(String uuid) throws Exception {
        HttpURLConnection connection = HttpUtil.creatUtil("http://127.0.0.1:8088/file/getFileInfo?uuid="+uuid, "GET");
        InputStream is= connection.getInputStream();//以输入流的形式返回
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte [] buffer=new byte[1024];
        int len=0;
        while((len=is.read(buffer))!=-1){
            baos.write(buffer, 0, len);
        }
        String jsonString=baos.toString();
        baos.close();
        is.close();
        //转换成json数据处理
        JSONObject jsonObject= JSON.parseObject(jsonString);
        return jsonObject;

    }
}

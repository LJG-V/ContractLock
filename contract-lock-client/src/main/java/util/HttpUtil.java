package util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

    public static HttpURLConnection creatUtil(String url,String method) throws Exception {
        URL connectionUrl=new URL(url);
        /*统一资源*/
        URLConnection urlConnection=connectionUrl.openConnection();
        /*http连接类*/
        HttpURLConnection connection= (HttpURLConnection) urlConnection;
        connection.setDoInput(true);
        connection.setDoOutput(true);
        if(method.equals("POST"))
        {
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
        }else {
            connection.setRequestMethod("GET");
        }
        // 设置字符编码连接参数
        connection.setRequestProperty("Connection", "Keep-Alive");
        // 设置字符编码
        connection.setRequestProperty("Charset", "UTF-8");
        return connection;
    }

}

package top.lvjguo.contractlockserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class UploadFileUtil {

    /**
     * 上传路径
     */
    @Value("${upload.path}")
    private String uploadPath;

    private final Logger logger =  LoggerFactory.getLogger(getClass());

    public void upload(String path, String fileName, InputStream inputStream)  throws Exception {

        //创建目录
        File directory = new File(uploadPath + path);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                logger.error("创建目录失败");
            }
            logger.info("创建目录成功");
        }

        // 写入文件
        File file = new File(uploadPath + path + "\\" + fileName);
        if (file.createNewFile()) {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = new byte[1024];
            int length;
            while ((length = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, length);
            }
            bos.flush();
            inputStream.close();
            bis.close();
            bos.close();
        }
    }

    //获取文件存储位置
    public String getFileAccessUrl(String filePath,String name) {
        return uploadPath + filePath  + name;
    }
}

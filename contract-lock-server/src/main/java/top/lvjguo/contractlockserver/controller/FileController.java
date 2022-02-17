package top.lvjguo.contractlockserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.lvjguo.contractlockserver.entity.FileEntity;
import top.lvjguo.contractlockserver.service.UploadFileService;
import top.lvjguo.contractlockserver.utils.UploadFileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private UploadFileService fileService;
    @Autowired
    private UploadFileUtil uploadFileUtil;
    private final Logger logger =  LoggerFactory.getLogger(getClass());

    /**
     * 上传文件控制器
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public String FileUpload(MultipartFile file)  throws Exception {

        //uuid
        String uuid = UUID.randomUUID().toString();
        logger.info("上传文件的UUID="+uuid);
        //原始文件名
        String oldName = file.getOriginalFilename();
        //文件类型
        String type = "";
        if (oldName != null) {
            type = oldName.substring(oldName.indexOf("."), oldName.length());
        }
        //文件大小
        Long size= file.getSize();

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        //当前目录
        String path = dateFormat.format(date) + "\\";
        //创建时间
        Date time=new Date(System.currentTimeMillis());

        try {
            //上传文件
            uploadFileUtil.upload(path,uuid+type,file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("uuidName",uuid);
        map.put("oldName",oldName);
        map.put("url",uploadFileUtil.getFileAccessUrl(path,uuid+type));
        map.put("type",type);
        map.put("time",time);
        map.put("size",size);

        //数据库记录元数据
        fileService.add(map);
        logger.info("上传成功");
        return uuid;
    }

    /**
     * 文件下载控制器
     */
    @GetMapping("/download")
    @ResponseBody
    public void FileDownload(HttpServletRequest request,HttpServletResponse response) throws IOException {
        FileEntity fileEntity = fileService.getFileUrl(request.getHeader("uuid"));
        if(fileEntity==null){
            //返回异常码
            response.setStatus(401);
            logger.info("文件不存在");
            return;
        }
        //定位数据源
        File f=new File(fileEntity.getUrl());
        //建立管道
        InputStream in=new FileInputStream(f);
        OutputStream out=new DataOutputStream(response.getOutputStream());
        response.setHeader("fileType",fileEntity.getType());
        response.setHeader("uuid",fileEntity.getUuidName());
        byte[] buf=new byte[1024];
        int bytesRead;
        while ((bytesRead=in.read(buf))>0){
            out.write(buf,0,bytesRead);
        }
        in.close();
        out.close();
        logger.info("文件下载成功");

    }


    /**
     * 获取文件元数据
     */
    @GetMapping("/getFileInfo")
    @ResponseBody
    public FileEntity getFileInfo(String uuid) throws IOException {
        return fileService.getFileUrl(uuid);
    }
}

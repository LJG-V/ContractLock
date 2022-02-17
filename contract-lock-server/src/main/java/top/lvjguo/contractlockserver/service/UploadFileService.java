package top.lvjguo.contractlockserver.service;

import org.springframework.web.multipart.MultipartFile;
import top.lvjguo.contractlockserver.entity.FileEntity;

import java.util.Map;

public interface UploadFileService {

    FileEntity getFileUrl(String uuid);

    void add(Map<String, Object> map);
}

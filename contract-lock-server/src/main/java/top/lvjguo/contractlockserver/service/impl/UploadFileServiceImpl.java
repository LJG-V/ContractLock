package top.lvjguo.contractlockserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjguo.contractlockserver.entity.FileEntity;
import top.lvjguo.contractlockserver.mapper.UploadFileMapper;
import top.lvjguo.contractlockserver.service.UploadFileService;


import java.util.Map;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Override
    public FileEntity getFileUrl(String uuid) {
        return uploadFileMapper.findUrl(uuid);
    }

    @Override
    public void add(Map<String, Object> map) {
        uploadFileMapper.addFile(map);
    }
}

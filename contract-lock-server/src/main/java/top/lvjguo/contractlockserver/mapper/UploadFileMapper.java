package top.lvjguo.contractlockserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.lvjguo.contractlockserver.entity.FileEntity;

import java.util.Map;

@Mapper
public interface UploadFileMapper {

    void addFile(Map<String, Object> map);

    FileEntity findUrl(String uuid);
}

package top.lvjguo.contractlockserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {

    /**
     * id
     */
    private Integer id;
    /**
     * 文件UUID
     */
    private String uuidName;

     /**
      * 原始文件名
      */
    private String oldName;

    /**
     * 文件保存目录
     */
    private String url;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyyMMdd")//页面写入数据库时格式化
    private Date time;

}

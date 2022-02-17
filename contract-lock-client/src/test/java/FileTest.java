import controller.Download;
import controller.FileData;
import controller.Upload;
import org.junit.Test;

public class FileTest {
    /*测试上传功能*/
    @Test
    public void upload() throws Exception {
        System.out.println( Upload.uploadFile("D:\\1.docx"));
    }
    /*测试下载功能*/
    @Test
    public void down() throws Exception {
        System.out.println(Download.downFile("1b4a5784-d038-407f-908c-82ded2fe6a12"));
    }
    /*测试得到元数据功能*/
    @Test
    public void fileData() throws Exception {
        System.out.println( FileData.getData("1b4a5784-d038-407f-908c-82ded2fe6a12"));
    }
}

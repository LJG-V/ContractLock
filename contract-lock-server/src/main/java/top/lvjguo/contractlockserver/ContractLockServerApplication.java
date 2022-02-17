package top.lvjguo.contractlockserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class ContractLockServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractLockServerApplication.class, args);
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}

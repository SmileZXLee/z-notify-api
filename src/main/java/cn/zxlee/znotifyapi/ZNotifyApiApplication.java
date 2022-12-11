package cn.zxlee.znotifyapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zxlee.znotifyapi.mapper")
public class ZNotifyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZNotifyApiApplication.class, args);
    }

}

package com.nebula.practicevueblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nebula
 * @date 2021/10/25 20:57
 */
@SpringBootApplication
//@MapperScan("com/nebula/practicevueblog/mapper")
public class PracticevueblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticevueblogApplication.class, args);
    }

}

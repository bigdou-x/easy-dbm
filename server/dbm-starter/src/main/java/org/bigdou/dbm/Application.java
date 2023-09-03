package org.bigdou.dbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@EntityScan
@SpringBootApplication(scanBasePackages = {"org.bigdou.dbm", "com.alibaba.cola"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

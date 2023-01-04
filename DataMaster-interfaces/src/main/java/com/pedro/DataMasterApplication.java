package com.pedro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * DataMaster Application
 */
@SpringBootApplication
@Configurable
public class DataMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataMasterApplication.class, args);
    }
}

package com.pedro;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configurable
public class DataMasterApplication
{
    public static void main(String[] args) {
        SpringApplication.run(DataMasterApplication.class, args);
    }
}

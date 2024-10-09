package com.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ErpApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.medical;

import com.medical.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MedicalApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MedicalApplication.class, args);
    }
}

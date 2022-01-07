package com.medical;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.medical")
@EnableJpaRepositories
@EnableTransactionManagement
public class MedicalApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        final SpringApplicationBuilder app = new SpringApplicationBuilder(MedicalApplication.class);
        app.run();
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(MedicalApplication.class);
    }
}

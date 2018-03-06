package org.apache.skywalking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("org.apache.skywalking")
public class ServiceApplication {

    public static void main(String[] args) {

        Object[] sources = new Object[] {ServiceApplication.class};
        SpringApplication.run(sources, args);

    }
}

package com.fjapi.flickjunkies.fjweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.fjapi.flickjunkies"})
@EntityScan("com.fjapi.flickjunkies.fjcore.entity")
@EnableJpaRepositories("com.fjapi.flickjunkies.fjcore.repository")
public class FlickjunkiesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FlickjunkiesApplication.class, args);
    }
}

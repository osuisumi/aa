package com.haoyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;



//@SpringBootApplication
@Import({MybatisConfig.class})
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(SpringApplication.run(new Object[] { Application.class }, args));
  }
}

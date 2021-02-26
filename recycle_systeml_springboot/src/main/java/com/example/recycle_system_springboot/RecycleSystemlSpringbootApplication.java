package com.example.recycle_system_springboot;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.recycle_system_springboot.dao")
public class RecycleSystemlSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecycleSystemlSpringbootApplication.class, args);
    }

}

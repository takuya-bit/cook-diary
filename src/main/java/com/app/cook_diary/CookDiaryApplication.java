package com.app.cook_diary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.app.cook_diary.mapper")
public class CookDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookDiaryApplication.class, args);
	}

}

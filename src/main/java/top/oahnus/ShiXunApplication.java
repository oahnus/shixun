package top.oahnus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"top.oahnus.mapper"})
public class ShiXunApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiXunApplication.class, args);
	}
}

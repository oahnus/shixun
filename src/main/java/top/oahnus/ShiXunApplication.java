package top.oahnus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShiXunApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiXunApplication.class, args);
	}
}

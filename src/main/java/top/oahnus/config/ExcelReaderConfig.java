package top.oahnus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import top.oahnus.entity.Company;
import top.oahnus.entity.Student;
import top.oahnus.entity.Teacher;

/**
 * Created by oahnus on 2017/3/26 21:01.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "excel")
@PropertySource("classpath:excelreader-config.properties")
public class ExcelReaderConfig {
    private Company company;
    private Student student;
    private Teacher teacher;
}

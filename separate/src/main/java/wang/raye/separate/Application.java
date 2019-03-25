package wang.raye.separate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * KBErp，对接ERP，负责从ERP数据库查询和读取数据
 * 
 * @author Raye
 * @since 2016年10月7日09:38:20
 */
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
		
	}
}

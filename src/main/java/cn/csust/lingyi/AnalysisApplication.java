package cn.csust.lingyi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication
@MapperScan("cn.csust.lingyi.mapper")
public class AnalysisApplication {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
		multipartConfigFactory.setMaxFileSize(DataSize.of(100, DataUnit.MEGABYTES));
		multipartConfigFactory.setMaxRequestSize(DataSize.of(100, DataUnit.MEGABYTES));
		return multipartConfigFactory.createMultipartConfig();
	}
	public static void main(String[] args) {
		SpringApplication.run(AnalysisApplication.class, args);
	}

}

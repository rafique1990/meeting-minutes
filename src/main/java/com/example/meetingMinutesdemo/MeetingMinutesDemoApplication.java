package com.example.meetingMinutesdemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
//@EnableSwagger2
@EnableJpaRepositories(basePackages ={"com.example.meetingMinutesdemo.repositories"})
public class MeetingMinutesDemoApplication extends SpringBootServletInitializer  implements ApplicationRunner {

	public static void main(String[] args) {



		SpringApplication.run(MeetingMinutesDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Hello World from Application Runner");
	}

	@Bean
public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.meetingMinutesdemo")).build();
	}

}
////public class MeetingMinutesDemoApplication {
////
////	public static void main(String[] args) {
////		SpringApplication.run(MeetingMinutesDemoApplication.class, args);
////	}
////
////}

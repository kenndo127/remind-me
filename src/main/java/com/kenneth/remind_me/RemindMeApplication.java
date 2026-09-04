package com.kenneth.remind_me;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Remind API",
				version = "1.0",
				description = "Be reminded of your check-ins with daily emails"
		)
)
public class RemindMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemindMeApplication.class, args);
	}

}

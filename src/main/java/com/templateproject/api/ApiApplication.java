package com.templateproject.api;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import com.templateproject.api.config.KeyGenerator;
import com.templateproject.api.config.SecurityConfiguration;
import com.templateproject.api.service.TokenService;
import com.templateproject.api.utils.InstantTypeAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.templateproject.api.config.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.Instant;

@SpringBootApplication(scanBasePackages = "com.templateproject.api")
@Import(SecurityConfiguration.class)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	@Bean
	public Gson gson() {
		return new GsonBuilder()
				.registerTypeAdapter(Instant.class, new InstantTypeAdapter())
				.create();
	}
}

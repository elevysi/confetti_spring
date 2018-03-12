package com.elevysi.site.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableFeignClients
//@RefreshScope
//@EnableOAuth2Client
public class BlogBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogBootApplication.class, args);

	}

}

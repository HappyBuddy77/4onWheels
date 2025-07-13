package com._onWheels._onWheels;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Controller implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// registry.addViewController("/HomePage").setViewName("HomePage");
		// registry.addViewController("/").setViewName("home");
		// registry.addViewController("/login").setViewName("login");
	}

}

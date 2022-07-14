package iagopm.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ConfigMvc implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// This interceptor apply to URL like /admin/*
		// Exclude /admin/oldLogin
		registry.addInterceptor(tokenInterceptor())//
				.addPathPatterns("/api/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
        .addResourceHandler("/**")
        .addResourceLocations("/");	
	}

	@Bean
	TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}
}
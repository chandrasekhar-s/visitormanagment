package org.tpark.visitor.pass.mngmt.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("org.tpark.visitor.pass.mngmt")
@EnableWebMvc
@PropertySource("classpath:jpa.properties")
public class Config extends WebMvcConfigurerAdapter {

	 @Bean  
	    public UrlBasedViewResolver setupViewResolver() {  
	        UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
	        resolver.setPrefix("/WEB-INF");  
	        resolver.setSuffix(".htm");  
	        resolver.setViewClass(JstlView.class);  
	        return resolver;  
	    }  
	 
	 
} 

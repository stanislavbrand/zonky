package cz.zonky.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import cz.zonky.demo.model.Loan;

@Configuration
@PropertySource("classpath:demo.properties")
public class SpringConfiguration {
	
	@Value("${rest.url}")
	private String rootUri;
	
	@Bean
	public RestTemplateBuilder  restTemplateBuilder(){
		return new RestTemplateBuilder().rootUri(rootUri);
	}
	
	@Bean
	public List<Loan> loanCache(){
		return new ArrayList<>();
	}
	
}

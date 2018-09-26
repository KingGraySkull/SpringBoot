package util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"pojos","util"})
@EnableJpaRepositories(basePackages = {"repository"})
public class Config 
{
	
}

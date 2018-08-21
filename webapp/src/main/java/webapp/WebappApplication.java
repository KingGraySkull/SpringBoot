package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"controllers","services","dtos","config",})
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class WebappApplication extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(WebappApplication.class);
	}

	public static void main(String[] args) 
	{
		SpringApplication.run(WebappApplication.class, args);
	}
}
	
package restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"controllers","services"})
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class RestappApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(RestappApplication.class, args);
	}
}

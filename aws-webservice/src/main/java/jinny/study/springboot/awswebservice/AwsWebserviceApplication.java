package jinny.study.springboot.awswebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class AwsWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsWebserviceApplication.class, args);
	}

}

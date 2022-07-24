package jinny.study.springboot.awswebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing	// JPA Auditing 활성화
@SpringBootApplication
public class AwsWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsWebserviceApplication.class, args);
	}

}

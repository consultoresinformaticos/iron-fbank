package py.edu.una.ironbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;

@SpringBootApplication
@AutoConfigureWebMvc
public class IronbankFeApplication {

	public static void main(String[] args) {		
		SpringApplication.run(IronbankFeApplication.class, args);
		
	}
	
}

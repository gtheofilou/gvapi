package gr.gt.gvapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import gr.gt.gvapi.utils.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	AppProperties.class
})
public class GvapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GvapiApplication.class, args);
	}

}

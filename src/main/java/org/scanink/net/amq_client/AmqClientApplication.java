package org.scanink.net.amq_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AmqClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqClientApplication.class, args);
	}

}

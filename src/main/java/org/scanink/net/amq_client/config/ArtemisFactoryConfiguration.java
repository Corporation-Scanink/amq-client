package org.scanink.net.amq_client.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("spring.artemis")
public class ArtemisFactoryConfiguration {


	String brokerUrl;
	String user;
	String password;
}

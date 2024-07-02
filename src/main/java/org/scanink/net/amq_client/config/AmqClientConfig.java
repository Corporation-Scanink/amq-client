package org.scanink.net.amq_client.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
@ConfigurationProperties("amq-client")
public class AmqClientConfig {
	
	String destination;
	List<ConsumerConfig> consumers;

}
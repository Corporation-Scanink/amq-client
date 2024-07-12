package org.scanink.net.amq_client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableJms
@EnableScheduling
public class AmqConfig {
	
}

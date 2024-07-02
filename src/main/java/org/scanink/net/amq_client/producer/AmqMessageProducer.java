package org.scanink.net.amq_client.producer;

import org.scanink.net.amq_client.config.AmqClientConfig;
import org.scanink.net.amq_client.consumer.AmqMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class AmqMessageProducer {

	private final JmsTemplate jmsTemplate;
	
	private final  AmqClientConfig config;
	
	public void send(AmqMessage message) {
		log.info("Sending message to {}: {}", config.getDestination(), message.getMessage());
		jmsTemplate.convertAndSend(config.getDestination(), new Gson().toJson(message));
	}
}

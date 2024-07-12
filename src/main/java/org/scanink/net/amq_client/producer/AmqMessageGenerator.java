package org.scanink.net.amq_client.producer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.scanink.net.amq_client.config.AmqClientConfig;
import org.scanink.net.amq_client.config.ConsumerConfig;
import org.scanink.net.amq_client.consumer.AmqMessage;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AmqMessageGenerator {

	private static final String MESSAGE_PREFIX = "Envoie de message automatic to ";
	private final AmqMessageProducer producer;
	
	private final AmqClientConfig config;
	
	private List<String> consumerNames;
	
	
	@PostConstruct
	public void init() {
		this.getConusmerNames();
	}
	
	private List<String> getConusmerNames() {
		if (consumerNames == null || consumerNames.isEmpty()) {
			
			consumerNames = config.getConsumers().stream().map(ConsumerConfig::getName).collect(toList());
			consumerNames.add("NONAME");
			consumerNames.add("NON Error Generating Name");
		}
		
		return consumerNames;
	}
	
	private String generateRandomConsumerName() {
		
		int index = ThreadLocalRandom.current().nextInt(0, getConusmerNames().size());
		return getConusmerNames().get(index);
		
	}
	
	//@Scheduled(fixedDelay = 50)
	public void produceMessages() {
		
		producer.send(new AmqMessage().message(MESSAGE_PREFIX + generateRandomConsumerName()));
	}
	
}

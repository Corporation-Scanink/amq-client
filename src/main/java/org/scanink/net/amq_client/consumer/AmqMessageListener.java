package org.scanink.net.amq_client.consumer;

import org.scanink.net.amq_client.config.AmqClientConfig;
import org.scanink.net.amq_client.config.ConsumerConfig;
import org.springframework.jms.support.JmsUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class AmqMessageListener implements MessageListener {
	
	private String name;
	private String group;
	private String destination;
	private String url;
	private RestTemplate restTemplate = new RestTemplate();
	
	
	AmqMessageListener()  {
		super();
	}
	
	public static AmqMessageListener build(AmqClientConfig clientConfig, ConsumerConfig consumerConfig) {
		return new AmqMessageListener()
				.destination(clientConfig.getDestination())
				.name(consumerConfig.getName())
				.group(consumerConfig.getGroup())
				.url(clientConfig.getUrl());
	}
	
	private  AmqMessageListener url(String url) {

		this.url = url;
		return this;
	}

	public AmqMessageListener destination(String destination) {
		this.destination = destination;
		return this;
	}
	
	public AmqMessageListener name(String name) {
		this.name = name;
		return this;
	}
	
	public AmqMessageListener group(String group) {
		this.group = group;
		return this;
	}
	
	public String absoluteUri() {
		return url + "/" + name;
	}

	@Transactional
	@Override
	public void onMessage(Message message) {
		try {
			
			
			String messageBody = message.getBody(String.class);
			AmqMessage amqMessage = new Gson().fromJson(messageBody, AmqMessage.class);
			
			log.info("Sending Message to {}: id={}, message={}", absoluteUri(), message.getJMSMessageID(), amqMessage.getMessage());
			
			RestMessage restMessage = new RestMessage();
			restMessage.setId(message.getJMSMessageID());
			restMessage.setMessage(amqMessage.getMessage());
			
			restTemplate.postForObject(absoluteUri(), restMessage, RestMessage.class);			
			
			
		} catch (JMSException jmsex) {
			log.info(jmsex.getLocalizedMessage());
			throw JmsUtils.convertJmsAccessException(jmsex);
		} catch (RestClientException restex) {
			log.info(restex.getLocalizedMessage());
			throw new RuntimeException(restex.getLocalizedMessage(),restex);
		}
		
	}
	
	
}

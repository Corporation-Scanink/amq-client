package org.scanink.net.amq_client.consumer;

import org.scanink.net.amq_client.config.AmqClientConfig;
import org.scanink.net.amq_client.config.ConsumerConfig;
import org.springframework.jms.support.JmsUtils;

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
	
	
	AmqMessageListener()  {
		super();
	}
	
	public static AmqMessageListener build(AmqClientConfig clientConfig, ConsumerConfig consumerConfig) {
		return new AmqMessageListener()
				.destination(clientConfig.getDestination())
				.name(consumerConfig.getName())
				.group(consumerConfig.getGroup());
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

	@Override
	public void onMessage(Message message) {
		try {
			String messageBody = message.getBody(String.class);
			AmqMessage amqMessage = new Gson().fromJson(messageBody, AmqMessage.class);
			
			if (amqMessage.getMessage().contains(name)) {
				throw new JMSException("My name is called: ROLLBACK!!");
			}
			
			log.info("{}:{} - Recieved Message: id={}, message={}", group, name, message.getJMSMessageID(), amqMessage.getMessage());
			
		} catch (JMSException e) {
			log.info(e.getLocalizedMessage());
			throw JmsUtils.convertJmsAccessException(e);
		}
		
	}
	
	
}

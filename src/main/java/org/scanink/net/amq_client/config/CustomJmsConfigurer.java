package org.scanink.net.amq_client.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.scanink.net.amq_client.consumer.AmqMessageListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

import jakarta.jms.JMSException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@AllArgsConstructor
public class CustomJmsConfigurer implements JmsListenerConfigurer  {
	
	
	private final static String ADRESS_SEPERATOR = "::";
	private final static String FQDN_CHAR = "/";
	
	private final AmqClientConfig amqClientConfig;

	private final ArtemisFactoryConfiguration artemisConfig;

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		// TODO Auto-generated method stub
		
				
		amqClientConfig.getConsumers().forEach(consumerConfig -> {
			try {
			ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory();
			amqConnectionFactory.setBrokerURL(artemisConfig.getBrokerUrl());
	        amqConnectionFactory.setUser(artemisConfig.getUser());
	        amqConnectionFactory.setPassword(artemisConfig.getPassword());
			
			DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
	        containerFactory.setConnectionFactory(amqConnectionFactory);
			
	        AmqMessageListener amqMessageListerner = AmqMessageListener.build(amqClientConfig, consumerConfig);
			SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
	        endpoint.setId(amqMessageListerner.getGroup() + FQDN_CHAR + amqMessageListerner.getName());
	        endpoint.setDestination(amqMessageListerner.getDestination() +  ADRESS_SEPERATOR + amqMessageListerner.getGroup() + FQDN_CHAR + amqMessageListerner.getName());
	        endpoint.setMessageListener(amqMessageListerner);
			
			
			registrar.registerEndpoint(endpoint, containerFactory);
			
			} catch (JMSException jemx) {
				log.info(jemx.getLocalizedMessage());
				jemx.printStackTrace();
			}
		});
		
	}

	
	
}

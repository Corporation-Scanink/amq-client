package org.scanink.net.amq_client.consumer;

import org.springframework.jms.listener.AbstractPollingMessageListenerContainer;

import jakarta.jms.JMSException;

public class AmqPollingMessageListenerContainer extends AbstractPollingMessageListenerContainer {

	@Override
	public void setConcurrency(String concurrency) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean sharedConnectionEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doInitialize() throws JMSException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doShutdown() throws JMSException {
		// TODO Auto-generated method stub

	}

}

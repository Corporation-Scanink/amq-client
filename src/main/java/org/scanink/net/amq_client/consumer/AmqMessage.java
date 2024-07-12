package org.scanink.net.amq_client.consumer;

import java.io.Serializable;

import lombok.Data;

@Data
public class AmqMessage implements Serializable {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	private String message;
	
	public AmqMessage message(String message) {
		this.message = message;
		return this;
	}
	

}

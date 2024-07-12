package org.scanink.net.amq_client.consumer;

import java.io.Serializable;

import lombok.Data;

@Data
public class RestMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String message;
}

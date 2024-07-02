package org.scanink.net.amq_client.config;

import lombok.Data;

@Data
public class ConsumerConfig {

	private String name;
	private String group;
}

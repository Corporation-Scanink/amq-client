package org.scanink.net.amq_client.producer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.scanink.net.amq_client.consumer.AmqMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/amq")
@AllArgsConstructor
public class AmqMessageController {
	
	private final AmqMessageProducer producer;
	
	@PostMapping()
	public String postMethodName(@RequestBody AmqMessage message) {

		producer.send(message);
		
		return "{ \"messageSentStatus\" ; \"OK\" }";
	}
	
}

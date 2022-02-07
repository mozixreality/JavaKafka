package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;  
import org.apache.kafka.clients.consumer.ConsumerRecord;  
import org.apache.kafka.clients.consumer.ConsumerRecords; 
import org.apache.kafka.clients.consumer.KafkaConsumer;  
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer {
	public static void main(String[] args) {		
		final String bootStrapServers = "127.0.0.1:9092";
		final String consumerGroupID = "cooperate";
		
		Properties p = new Properties();
		p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupID);
		p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(p);
		
		consumer.subscribe(Arrays.asList("mozixreality"));
		
		
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(100000);
			for (ConsumerRecord record: records) {
				System.out.printf("Received record(key=%s value=%s) %s, %s, %s \n",
		                record.key(), 
		                record.value(),
		                record.topic(),
		                record.partition(),
		                record.offset()
		        );
			}
		}
	}
}

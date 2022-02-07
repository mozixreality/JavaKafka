package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
	public static void main(String[] args) {		
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	
		final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		final ProducerRecord<String, String> record = new ProducerRecord<>("mozixreality", "key-1", "ohoh");
		
		producer.send(record);
		
        System.out.printf("sent record(key=%s value=%s)\n",
                record.key(), 
                record.value()
        );
        
		producer.close();
	}
}

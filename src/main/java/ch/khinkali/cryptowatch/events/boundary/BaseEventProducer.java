package ch.khinkali.cryptowatch.events.boundary;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

public class BaseEventProducer<KEY, VALUE> {
    private static final Logger logger = Logger.getLogger(BaseEventProducer.class.getName());

    private Producer<KEY, VALUE> producer;

    protected void init(Properties kafkaProperties) {
        try {
            kafkaProperties.put("transactional.id", UUID.randomUUID().toString());
            producer = new KafkaProducer<>(kafkaProperties);
            producer.initTransactions();
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    public void publish(String topic, VALUE event) {
        final ProducerRecord<KEY, VALUE> record = new ProducerRecord<>(topic, event);
        try {
            producer.beginTransaction();
            producer.send(record);
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            logger.severe(e.getMessage());
            producer.close();
        } catch (KafkaException e) {
            logger.severe(e.getMessage());
            producer.abortTransaction();
        }
    }

    public void close() {
        producer.close();
    }

}

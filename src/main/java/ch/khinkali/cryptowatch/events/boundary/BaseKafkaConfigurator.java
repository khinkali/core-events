package ch.khinkali.cryptowatch.events.boundary;

import java.util.Properties;

public class BaseKafkaConfigurator {

    private Properties kafkaProperties;

    protected void initProperties() {
        kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", System.getenv("KAFKA_ADDRESS"));
        setConsumerProperties();
        setProducerProperties();
    }

    private void setProducerProperties() {
        kafkaProperties.put("batch.size", 16384);
        kafkaProperties.put("linger.ms", 0);
        kafkaProperties.put("buffer.memory", 33554432);
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", EventSerializer.class.getCanonicalName());
    }

    private void setConsumerProperties() {
        kafkaProperties.put("isolation.level", "read_committed");
        kafkaProperties.put("enable.auto.commit", false);
        kafkaProperties.put("auto.offset.reset", "earliest");
        kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProperties.put("value.deserializer", EventDeserializer.class.getCanonicalName());
    }

    public Properties getKafkaProperties() {
        final Properties properties = new Properties();
        properties.putAll(kafkaProperties);
        return properties;
    }

}

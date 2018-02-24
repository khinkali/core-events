package ch.khinkali.cryptowatch.events.boundary;

import ch.khinkali.cryptowatch.events.entity.BaseEvent;
import org.apache.kafka.common.serialization.Serializer;

import javax.json.Json;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;

public class EventSerializer implements Serializer<BaseEvent> {

    private static final Logger logger = Logger.getLogger(EventSerializer.class.getName());

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(final String topic, final BaseEvent event) {
        return Json.createObjectBuilder()
                .add("class", event.getClass().getCanonicalName())
                .add("data", event.getJson())
                .build()
                .toString()
                .getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void close() {
        // nothing to do
    }

}

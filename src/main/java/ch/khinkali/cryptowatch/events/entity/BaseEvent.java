package ch.khinkali.cryptowatch.events.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Date;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
public abstract class BaseEvent {

    public enum JSON_KEYS {
        ID("id"), TIMESTAMP("timestamp");

        @Getter
        String jsonKey;

        JSON_KEYS(String jsonKey) {
            this.jsonKey = jsonKey;
        }
    }

    private String id;
    private Long timestamp;

    public BaseEvent() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = new Date().getTime();
    }

    public JsonObjectBuilder getJsonBuilder() {
        return Json.createObjectBuilder()
                .add(JSON_KEYS.ID.getJsonKey(), id)
                .add(JSON_KEYS.TIMESTAMP.getJsonKey(), timestamp);
    }

    public abstract JsonObject getJson();
}

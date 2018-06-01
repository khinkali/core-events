package ch.khinkali.cryptowatch.events.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.json.Json;
import javax.json.JsonObject;

@AllArgsConstructor
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

    public BaseEvent(JsonObject jsonObject) {
        this(jsonObject.getString(JSON_KEYS.ID.getJsonKey()),
                jsonObject.getJsonNumber(JSON_KEYS.TIMESTAMP.getJsonKey()).longValue());
    }

    public JsonObject getJson() {
        return Json.createObjectBuilder()
                .add(JSON_KEYS.ID.getJsonKey(), id)
                .add(JSON_KEYS.TIMESTAMP.getJsonKey(), timestamp)
                .build();
    }
}

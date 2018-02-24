package ch.khinkali.cryptowatch.events.entity;


import lombok.Getter;

import javax.json.Json;
import javax.json.JsonObject;

@Getter
public class UserCreated extends BaseEvent {
    public static final String TOPIC = "users";

    private final String userId;
    private final String username;

    public UserCreated(final String userId, final String username) {
        this.userId = userId;
        this.username = username;
    }

    public UserCreated(JsonObject jsonObject) {
        this(jsonObject.getString("userId"),
                jsonObject.getString("username"));
    }

    @Override
    public JsonObject getJson() {
        return Json.createObjectBuilder()
                .add("userId", userId)
                .add("username", username)
                .build();
    }
}


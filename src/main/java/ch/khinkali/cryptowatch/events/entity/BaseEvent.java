package ch.khinkali.cryptowatch.events.entity;

import javax.json.JsonObject;

public interface BaseEvent {

    String getId();

    JsonObject getJson();
}

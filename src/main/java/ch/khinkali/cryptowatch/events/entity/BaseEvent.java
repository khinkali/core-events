package ch.khinkali.cryptowatch.events.entity;

import javax.json.JsonObject;

public abstract class BaseEvent {

    public abstract JsonObject getJson();
}

package com.melrs.mingle.domain.balance.events;

import com.melrs.mingle.data.model.MingleItem;

public class MingleItemEvent {

    private final int mingleItemId;
    private final EventType eventType;

    public MingleItemEvent(int mingleItemId, EventType eventType) {
        this.mingleItemId = mingleItemId;
        this.eventType = eventType;
    }

    public static MingleItemEvent create(int mingleItemId, EventType eventType) {
        return new MingleItemEvent(mingleItemId, eventType);
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getMingleItem() {
        return mingleItemId;
    }
}

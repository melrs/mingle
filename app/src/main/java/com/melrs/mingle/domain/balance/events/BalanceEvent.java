package com.melrs.mingle.domain.balance.events;

import com.melrs.mingle.data.model.UserBalance;

public class BalanceEvent {

        private final UserBalance balance;
        private final EventType eventType;

        public BalanceEvent(UserBalance balance, EventType eventType) {
            this.balance = balance;
            this.eventType = eventType;
        }

        public static BalanceEvent create(UserBalance balance, EventType eventType) {
            return new BalanceEvent(balance, eventType);
        }

        public EventType getEventType() {
            return eventType;
        }

        public UserBalance getBalance() {
            return balance;
        }
}

package com.company.enum_state;

public enum DeckCount {
    Invalid(-1),
    One(1),
    Two(2),
    Three(3),
    Four(4);

    private final int value;

    DeckCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

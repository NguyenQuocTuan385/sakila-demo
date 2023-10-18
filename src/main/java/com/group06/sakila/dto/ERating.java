package com.group06.sakila.dto;

public enum ERating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");
    public final String label;

    ERating(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

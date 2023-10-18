package com.group06.sakila.dto;

public enum ESpecialFeatures {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    public final String label;
    ESpecialFeatures(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

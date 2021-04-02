package com.leverx.app.entity.dog.enums;

public enum PawColour {
    BLACK("BLACK"), PINK("PINK");

    private final String colour;

    PawColour(String code) {
        this.colour = code;
    }

    public String getColour() {
        return colour;
    }
}

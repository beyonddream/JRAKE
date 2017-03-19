package com.github.askdrcatcher.jrake;

/**
 * Created by askdrcatcher on 3/19/17.
 */
public class Word {

    private String value;

    public Word(String value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == null || value.trim().isEmpty();
    }

    public String getAsLowerCase() {
        return value.trim().toLowerCase();
    }
}

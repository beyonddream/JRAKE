package com.github.askdrcatcher.jrake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by askdrcatcher on 3/17/17.
 */
public class KeyWords {

    List<String> value;

    public KeyWords () {
        this.value = new ArrayList<String>();
    }

    public void add (String word) {
        value.add(word);
    }

}

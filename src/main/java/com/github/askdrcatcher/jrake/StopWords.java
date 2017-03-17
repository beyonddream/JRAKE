package com.github.askdrcatcher.jrake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by askdrcatcher on 3/16/17.
 */
public class StopWords {

    private List<String> words;

    public StopWords () {
        this.words = new ArrayList<String>();
    }

    public void add (String line) {
        if (!line.startsWith("#")) { //add the line which is not a comment
            this.words.add(line);
        }
    }

    public List<String> getAll() {
        return words;
    }
}


package com.github.askdrcatcher.jrake;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by askdrcatcher on 3/17/17.
 */
public class Sentence {

    String value;

    public Sentence (String value) {
        this.value = value;
    }

    public Phrase generatePhrasesFrom (Pattern stopWordPattern) {
        Matcher matcher = stopWordPattern.matcher(value);
        String sentenceWithoutStopWords = matcher.replaceAll("|");
        return new Phrase().addWords(sentenceWithoutStopWords.split("\\|"));
    }

}

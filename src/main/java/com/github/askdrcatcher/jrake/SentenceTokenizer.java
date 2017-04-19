package com.github.askdrcatcher.jrake;

/**
 * Created by askdrcatcher on 3/16/17.
 */
public class SentenceTokenizer {

    String grammar;

    public SentenceTokenizer () {
        this.grammar =  "[.!?,;:\\t\\\\-\\\\\"\\\\(\\\\)\\\\\\'\\u2019\\u2013]";
    }

    public SentenceTokenizer (String grammar) {
        this.grammar = grammar;
    }

    public Sentences split (String input) {
        return new Sentences().addSentences(input.split(grammar));
    }
}

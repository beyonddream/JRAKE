package com.github.askdrcatcher.jrake;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    public Pattern getStopWordsPattern() {
        final StringBuilder stopWordsPatternBuilder = new StringBuilder();
        for(final String stopWord: words) {
            stopWordsPatternBuilder.append("\\b");
            stopWordsPatternBuilder.append(stopWord);
            stopWordsPatternBuilder.append("\\b");
            stopWordsPatternBuilder.append("|");
        }
        String stopWordsPatternString = stopWordsPatternBuilder.toString();
        String stopWordPatternString  = StringUtils.chop(stopWordsPatternString); //remove last "|"
        return Pattern.compile(stopWordPatternString, Pattern.CASE_INSENSITIVE);
    }
}


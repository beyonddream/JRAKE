package com.github.askdrcatcher.jrake;

import com.github.askdrcatcher.jrake.util.FileUtil;

import java.io.IOException;

/**
 * Created by askdrcatcher on 3/16/17.
 */
class StopList {

    private StopWords stopWords;

    public StopList() {
        this.stopWords = new StopWords();
    }

    public StopList generateStopWords(FileUtil fileUtil) throws IOException {
        final FileUtil fileUtilIterator = fileUtil.iterator();
        while(fileUtilIterator.hasNext()) {
            stopWords.add(fileUtilIterator.next());
        }
        return this;
    }

    public StopWords getStopWords() {
        return stopWords;
    }
}

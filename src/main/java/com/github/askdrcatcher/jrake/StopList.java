package com.github.askdrcatcher.jrake;

import com.github.askdrcatcher.jrake.file.FileUtil;

import java.io.IOException;

/**
 * Created by askdrcatcher on 3/16/17.
 */
class StopList {

    private FileUtil fileUtil;
    private StopWords stopWords;

    public StopList(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
        this.stopWords = new StopWords();
    }

    public void generateWords () throws IOException {
        final FileUtil fileUtilIterator = fileUtil.iterator();
        while(fileUtilIterator.hasNext()) {
            stopWords.add(fileUtilIterator.next());
        }
    }

    public StopWords getStopWords() {
        return stopWords;
    }
}

package com.github.askdrcatcher.jrake.util.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by askdrcatcher on 3/16/17.
 */
public class FileUtil implements Iterator<String> {

    private String filePath;
    private LineIterator iterator;

    public FileUtil(String filePath) {
        this.filePath = filePath;
    }

    private boolean isFilePathEmpty() {
        if (filePath == null) return true;
        filePath = filePath.trim();
        return filePath.isEmpty();
    }

    private File getFile() {
        ClassLoader thisClassLoader = getClass().getClassLoader();
        URL fileUrl = this.isFilePathEmpty() ?
                thisClassLoader.getResource("FoxStoplist.txt") :
                thisClassLoader.getResource(filePath);
        return new File(fileUrl.getFile());
    }

    private File getUserProvidedFile() {
        ClassLoader thisClassLoader = getClass().getClassLoader();
        URL fileUrl = thisClassLoader.getResource(filePath);
        return new File(fileUrl.getFile());
    }

    public FileUtil iterator() throws IOException {
        if(this.iterator != null) //close already created iterator
            LineIterator.closeQuietly(iterator);
        this.iterator = FileUtils.lineIterator(getFile(), "UTF-8");
        return this;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = iterator.hasNext();
        if (!hasNext)
            LineIterator.closeQuietly(iterator);
        return hasNext;
    }

    @Override
    public String next() {
        return iterator.nextLine();
    }
}

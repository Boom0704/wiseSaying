package org.example;

public class Word {
    static int wordCount = 1;
    String words;
    String writer;
    int num =0;

    Word(String words, String writer){
        this.words = words;
        this.writer = writer;
        this.num = wordCount;
        wordCount++;
    }
}

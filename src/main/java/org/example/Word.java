package org.example;
import java.io.*;

public class Word implements Serializable{
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
    public static void saveWordCount() {
        try {
            FileOutputStream fos = new FileOutputStream("wordCount.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(wordCount);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadWordCount() {
        try {
            FileInputStream fis = new FileInputStream("wordCount.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            wordCount = ois.readInt();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


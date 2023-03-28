package org.example;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        App app = new App(sc);

        Word.loadWordCount();
        app.loadWordArrayList();

        app.run();

        Word.saveWordCount();
        app.saveWordArrayList();

        sc.close();
    }
}
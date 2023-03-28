package org.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class App {
    private final Scanner sc;
    ArrayList<Word> wordArrayList;
    public App(Scanner sc) {
        this.sc = sc;
        this.wordArrayList= new ArrayList<Word>();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            // trim() : 혹시 있을지 모를 좌우공백제거된 버전으로 주세요.
            String command = sc.nextLine().trim();

            if (command.equals("종료")) {
                break;
            }

            else if (command.equals("등록")) {
                regist();
                command = "";
                System.out.print("\n");
            }

            else if (command.equals("초기화")) {
                wordArrayList= new ArrayList<Word>();
                Word.wordCount = 1;
                Word.saveWordCount();
                saveWordArrayList();
                command = "";
                System.out.print("\n");
            }

            else if (command.equals("목록")) {
                listWish();
                command = "";
                System.out.print("\n");
            }

            else if (command.contains("삭제")) {
                try{
                    String[] ArraysStr = command.split("\\?id=");
                    int num = Integer.parseInt(ArraysStr[1]);
                    deleteWish(num);
                    command = "";
                    System.out.print("\n");
                }
                catch (Exception e){
                    System.out.print("삭제 사용법을 숙지해주세요 \n 사용법은 삭제?id=(숫자) 입니다.");
                    command = "";
                    System.out.print("\n");
                }
            }

            else if (command.contains("수정")) {
                try{
                    String[] ArraysStr = command.split("\\?id=");
                    int num = Integer.parseInt(ArraysStr[1]);
                    modifyWish(num);
                    command = "";
                    System.out.print("\n");
                }
                catch (Exception e){
                    System.out.print("수정 사용법을 숙지해주세요 \n 사용법은 수정?id=(숫자) 입니다.");
                    command = "";
                    System.out.print("\n");
                }
            }
        }
    }
    public void regist() {
        System.out.print("등록 : ");
        String command1 = sc.nextLine().trim();
        System.out.print("작가 : ");
        String command2 = sc.nextLine().trim();
        Word word = new Word(command1, command2);
        System.out.println(word.num +"번 명언이 등록되었습니다.");
        wordArrayList.add(word);
    }

    public void listWish() {
        System.out.println("번호 / 작가 / 명언");
        for(int i=0; i < wordArrayList.size() ; i ++ ){
            // i는 0부터 10보다 작을때 까지 i를 1씩 증가하면서 반복
            System.out.println(wordArrayList.get(i).num+" / " + wordArrayList.get(i).writer+" / " + wordArrayList.get(i).words);
        }

    }

    public void deleteWish(int num) {
        try{
            for(int i=0; i < wordArrayList.size() ; i ++ ){
                if(wordArrayList.get(i).num==num){
                    wordArrayList.remove(i);
                }
            }
            System.out.println(num + "번 명언이 삭제되었습니다.");
        }
        catch (Exception e){
            System.out.println(num + "번 명언은 존재하지 않습니다.");
        }
    }
    public void modifyWish(int num) {
        try{
            Word word = wordArrayList.get(num-1);
            System.out.println("명언(기존) : " + word.words);
            System.out.print("등록 : ");
            String command1 = sc.nextLine().trim();
            System.out.println("작가(기존) : " + word.writer);
            System.out.print("작가 : ");
            String command2 = sc.nextLine().trim();

            word.words = command1;
            word.writer = command2;

            for(int i=0; i < wordArrayList.size() ; i ++ ){
                if(wordArrayList.get(i).num==num){
                    wordArrayList.set(i, word);
                }
            }

            System.out.println(word.num +"번 명언이 수정되었습니다.");

        }
        catch (Exception e){
            System.out.println(num + "번 명언은 존재하지 않습니다.");
        }
    }

    public void saveWordArrayList() {
        try {
            FileOutputStream fos = new FileOutputStream("wordArrayList.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wordArrayList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWordArrayList() {
        try {
            FileInputStream fis = new FileInputStream("wordArrayList.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            wordArrayList = (ArrayList<Word>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package ru.vershinin.lesson6.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) {

        File file = new File("wordlist.txt");
        List<String> words=GetDictionary.readFile(file);
        int sizeords=words.size();
        Random r = new Random();


        List<String> proposal= new  ArrayList<>();

        for (int i = 0; i <r.nextInt(15) ; i++) {
            proposal.add(words.get(r.nextInt(sizeords)));
        }
        System.out.println(proposal.toString());


    }


}

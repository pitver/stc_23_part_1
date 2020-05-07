package ru.vershinin.lesson6.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
        List<String> pr=TextActions.getProposal(words);
        String a = null;
        for (String s:pr){
            a+=s+" ";
        }

        System.out.println(TextActions.addPunctuationMark(a.substring(4).trim()));




    }


}

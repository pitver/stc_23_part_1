package ru.vershinin.lesson6.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextActions {

    protected static List<String> getProposal(List<String> words){
        int sizeWords=words.size();
        Random r = new Random();
        int lenthRandon=r.nextInt(15);
        List<String> proposal= new ArrayList<>();
        for (int i = 0; i <= lenthRandon; i++) {
            String temp=words.get(r.nextInt(sizeWords));
            if(i==0){
              temp=firstUpperCase(temp);
            }
            proposal.add(temp);
            /*if(i==lenthRandon){
                proposal.add("!");
            }*/
        }
        return proposal;
    }
    private static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    protected static String addPunctuationMark(String text){
        Random random = new Random();
        int a=random.nextInt(2);
        switch (a){
            case 0:text+=".";
            break;
            case 1:text+="?";
            break;
            case 2:text+="!";
            break;

        }

        return text;

    }

}

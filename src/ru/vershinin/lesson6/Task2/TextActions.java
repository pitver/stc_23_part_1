package ru.vershinin.lesson6.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextActions {

    protected static List<String> getProposal(List<String> words,boolean checkParagraf){
        int sizeWords=words.size();
        Random random = new Random();
        int lenthRandon=random.nextInt(15);
        List<String> proposal= new ArrayList<>();
        for (int i = 0; i <= lenthRandon; i++) {
            String temp=words.get(random.nextInt(sizeWords));
// проверка на условие что это начала абзаца и это первое слово в предложении, ести истина- то первую букву делаем заглавной и делаем отсутп
            if(checkParagraf&(i==0)){
                temp="  "+firstUpperCase(temp);
            }else if (i==0){
                // проверка на условие что это первое слово в предложении, ести истина, то первую букву делаем заглавной
              temp=firstUpperCase(temp);
            }
            proposal.add(temp);
        }
        return proposal;
    }
    private static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    protected static String addPunctuationMark(String text){
        Random random = new Random();
        int randomPunctuation=random.nextInt(3);
        switch (randomPunctuation){
            case 0:text+=". ";
            break;
            case 1:text+="! ";
            break;
            case 2:text+="? ";
            break;

        }
        return text;

    }

}

package ru.vershinin.lesson6.Task2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextActions {

    /**
     * @param words - список слов из словаря, не подготовленный
     * @param checkParagraph - флаг для проверки условия, нужно ли начинать с нового абзаца
     * @param probability - верояность вхождения отдельного слова из массива
     * @return - возвращает подготовленный тескт
     */
    protected static List<String> getProposal(List<String> words, boolean checkParagraph, double probability) {
        int sizeWords = words.size();
        Random random = new Random();
        int lengthRandom = random.nextInt(15);
        List<String> proposal = new ArrayList<>();
        for (int i = 0; i <= lengthRandom; i++) {
            String temp;
            double num =Math.random();
            //проверка условия вероятности вхождения слова из массива слов в общий список слов, если true, то добавляем это слово
            if (roundTwoDecimals(num)!= roundTwoDecimals(probability)) {
              temp = words.get(random.nextInt(sizeWords));
            } else {
              temp=RandomChar.getRandomChar();
                System.out.println("есть совпадение "+temp);
            }
// проверка на условие что это начала абзаца и это первое слово в предложении, ести истина- то первую букву делаем заглавной и делаем отсутп
            if (checkParagraph && (i == 0)) {
                temp = "  " + firstUpperCase(temp);
            } else if (i == 0) {
                // проверка на условие что это первое слово в предложении, ести истина, то первую букву делаем заглавной
                temp = firstUpperCase(temp);
            }
            proposal.add(temp);
        }
        return proposal;
    }

    /**
     * @param word - список слов
     * @return - возводит первый символ любого предложения в верхний регистр
     */
    private static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * @param number - число которое нужно округлить
     * @return- готовое число типа Double, округленное до 2 згаков
     */
    private static double roundTwoDecimals(double number) {
        double roundOff = Math.round(number * 100.0) / 100.0;
        return roundOff;
    }

    /**
     * @param text - список предложений
     * @return - возвращает предложения со знаками препинания
     */
    protected static String addPunctuationMark(String text) {
        Random random = new Random();
        int randomPunctuation = random.nextInt(3);
        switch (randomPunctuation) {
            case 0:
                text += ". ";
                break;
            case 1:
                text += "! ";
                break;
            case 2:
                text += "? ";
                break;
        }
        return text;
    }
}

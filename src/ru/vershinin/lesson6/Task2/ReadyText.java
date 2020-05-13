package ru.vershinin.lesson6.Task2;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * GetReadyText
 *
 * @author Вершинин Пётр
 */
public class ReadyText {

    /**
     * @param words - список слов из словаря, не подготовленный
     * @param chance - верояность вхождения отдельного слова из массива
     * @return - возвращает подготовленный тескт
     */
    protected static String getText(List<String> words, double chance) {

        //временная коллекция
        List<String> tempList;
        //итоговый текст
        StringBuilder readyText = new StringBuilder();
        Random rd = new Random();
        // проверка нужно ли добавлять абзац, если true то добавляем
        boolean checkParagraf = false;
        int countLine = rd.nextInt(20);
        for (int i = 0; i < countLine; i++) {
            //получаем необработанное предложение
            tempList = TextActions.getProposal(words, checkParagraf, chance);
            checkParagraf = false;
            // добавляем пробелы и запятые между словами в одном предложении
            for (String line : tempList) {
                int randomСomma = rd.nextInt(20);
                readyText.append(line);
                if (randomСomma % 7 == 0) {
                    readyText.append(", ");
                } else {
                    readyText.append(" ");
                }
            }

            String tempText = readyText.toString().trim();
            tempText = TextActions.addPunctuationMark(tempText);
            //рандомно выбираем разрыв и перевод каретки
            int countParagraph = rd.nextInt(40);
            if (countParagraph % 6 == 0) {
                tempText += System.lineSeparator();
                checkParagraf = true;
            }
            readyText = new StringBuilder(tempText);
        }

        return readyText.toString();
    }
}

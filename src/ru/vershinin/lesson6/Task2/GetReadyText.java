package ru.vershinin.lesson6.Task2;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * GetReadyText
 *
 * @author Вершинин Пётр
 */
public class GetReadyText {

    protected static String getText(int size,List<String> words) {


        //временная коллекция
        List<String> tempList;
        //итоговый текст
        String readyText = "";
        Random rd = new Random();
        // проверка нужно ли добавлять абзац, если true то добавляем
        boolean checkParagraf = false;





        int countLine = rd.nextInt(size);

        for (int i = 0; i < countLine; i++) {

            /*if (i == 0) {
                checkParagraf = true;/// получение первого слова певого абзаца
            }*/
            //получаем необработанное предложение
            tempList = TextActions.getProposal(words, checkParagraf);
            checkParagraf = false;
            // добавляем пробелы между словами в одном предложении
            for (String line : tempList) {
                readyText += line + " ";
            }

            readyText = TextActions.addPunctuationMark(readyText.trim());
            //рандомно выбираем разрыв и перевод каретки
            int countParagraf = rd.nextInt(40);
            if (countParagraf % 6 == 0) {
                readyText += System.lineSeparator();
                checkParagraf = true;
            }
        }

        return readyText;
    }
}

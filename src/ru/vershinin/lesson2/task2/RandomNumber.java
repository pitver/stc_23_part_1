package ru.vershinin.lesson2.task2;

/**
 * RandomNumber
 *
 * @author Вершинин Пётр
 */
public class RandomNumber {


    public static void main(String[] args) {
        GenRandomNum gr = new GenRandomNum();
        int minNum = -2;//min. для рандомных чисел
        int maxNum = 135;//max.для рандомных чисел
        int count = 14;// количество генерируемых чисел
        int numK = 2;// число к из условия
        gr.searchNumber(minNum, maxNum, count, numK);
    }
}

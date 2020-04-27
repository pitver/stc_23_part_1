package ru.vershinin.lesson3;

import java.util.Random;

/**
 * Main
 *
 * @author Вершинин Пётр
 */
public class Main {
    public static void main(String[] args) {

        
        Integer[] arrNum=new Integer[25];
        Random rnd = new Random();
        for (int i = 0; i <arrNum.length ; i++) {
            arrNum[i]=rnd.nextInt(400);
        }
            MathBox mb= new MathBox(arrNum);

        mb.dump(mb.num);
        Integer res =mb.summator(mb);
        System.out.println("сумма всех элементов коллекции = "+res);
        mb.splitter(2);
        mb.dump(mb.num);
        mb.deleteObject(4);
        mb.dump(mb.num);


    }
}

package ru.vershinin.lesson7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GetSortArray {

    /**
     * формирует массив отсортированных элементов
     *
     * @param countNumbers - длина массива
     * @return - возвращаем отсортированный массив
     */
    protected static int[] getNumbers( int countNumbers) {
        Set<Integer> tempNum = new HashSet<>();
        for(int i = 0; i < countNumbers; i++) {
            Random rd=new Random();
            int num=rd.nextInt(50);
            if(num>1){
                tempNum.add(num);
            }
        }
        int[] arr = new int[tempNum.size()];
        int indexArr = 0;
        for (int countTempNum : tempNum)
        {
            System.out.print(countTempNum);
            System.out.print(" ");
            arr[indexArr++] = countTempNum;

        }
        Arrays.sort(arr);
        System.out.println();
        return arr;
    }
}

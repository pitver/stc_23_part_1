package ru.vershinin.lesson7;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class GetSortArray {
    protected static int[] getNumbers(Set<Integer> tempNum, int n) {

        for(int i = 0; i < n; i++) {
            Random rd=new Random();
            int num=rd.nextInt(10);
            if(num>0){
                tempNum.add(num);

            }
        }
        int[] arr = new int[tempNum.size()];
        int i = 0;
        for (int x : tempNum)
        {
            System.out.print(x);
            System.out.print(" ");
            arr[i++] = x;

        }
        Arrays.sort(arr);
        System.out.println();
        return arr;
    }
}

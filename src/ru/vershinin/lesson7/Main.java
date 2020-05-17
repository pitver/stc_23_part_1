package ru.vershinin.lesson7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Set<Integer> tempNum = new HashSet<>();
        int[] arr = GetSortArray.getNumbers(tempNum, 5);
        BigInteger bigInteger = new BigInteger("1");
        ExecutorService executor = Executors.newFixedThreadPool(1);

        List<Future<BigInteger>> resultList = new ArrayList<>();
        FactorialCalculator calculator = null;

        for (int a = 0; a < arr.length; a++) {
            if (a == 0) {
                calculator = new FactorialCalculator(arr[a], 2, bigInteger);
            } else {
                calculator = new FactorialCalculator(arr[a], arr[a - 1], bigInteger);
            }
            Future<BigInteger> result = executor.submit(calculator);

            resultList.add(result);

        }

        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


}

class FactorialCalculator implements Callable<BigInteger> {

    private final Integer number;
    private final Integer a;
    private final BigInteger b;


    public FactorialCalculator(Integer number, Integer a, BigInteger b) {
        this.number = number;
        this.a = a;
        this.b = b;
    }


    @Override
    public BigInteger call() throws Exception {

        BigInteger resultCalc = b;
        for (int i = a; i <= number; i++) {
            resultCalc = resultCalc.multiply(new BigInteger(i + ""));
        }

        System.out.printf("Factorial of %d is :: %d\n", number, resultCalc);
        return resultCalc;
    }
}








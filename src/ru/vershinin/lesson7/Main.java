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

        ExecutorService executor =  Executors.newFixedThreadPool(2);


        List<Future<BigInteger>> resultList = new ArrayList<>();

        for (int a=0;a<arr.length; a++) {
            FactorialCalculator calculator = new FactorialCalculator(arr[a]);
            Future<BigInteger> result = executor.submit(calculator);
            resultList.add(result);
        }

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < resultList.size(); i++)
        {
            Future<BigInteger> result = resultList.get(i);
            BigInteger number = null;
            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Main: Task %d: %d\n", i, number);
        }

        executor.shutdown();
    }


}

class FactorialCalculator implements Callable<BigInteger> {
    private final Integer number;


    public FactorialCalculator(Integer number) {
        this.number = number;

    }

    @Override
    public BigInteger call() throws Exception {

        //int result = 1;
        BigInteger result = new BigInteger("1");

        if ((number == 0) || (number == 1)) {
            result = new BigInteger("1");
        } else {
            for (int i = 2; i <= number; i++) {
                result = result.multiply(new BigInteger(i + ""));
               // TimeUnit.MILLISECONDS.sleep(20);
            }
        }

        System.out.printf("Factorial of %d is :: %d\n", number, result);
        return result;
    }
}

/*
            Result result = new Result(arr[0], 0, arr.length / 2);

            ExecutorService service = Executors.newFixedThreadPool(1);


            Future<BigInteger> future = service.submit(result);

            try {
                String res = String.valueOf(future.get());
                System.out.println(res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }      // Result result1 = new Result(arr,arr.length/2,arr.length);






    static class Result implements Callable<BigInteger> {
        private int n;
        private int a;
        private int b;

        public Result(int num,int a,int b) {
            this.n = num;
            this.a = a;
            this.b = b;
        }



        *//**
 * Computes a result, or throws an exception if unable to do so.
 *
 * @return computed result
 * @throws Exception if unable to compute a result
 *//*
        @Override
        public BigInteger call() throws Exception {
            BigInteger result = new BigInteger("1");
            for (int i = a; i <=b ; a++) {
                result=result.multiply(new BigInteger(i + ""));
            }
            return result;
        }
    }*/







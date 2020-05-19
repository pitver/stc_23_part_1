package ru.vershinin.lesson7;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Set<Integer> tempNum = new HashSet<>();
        int[] arr = GetSortArray.getNumbers(tempNum, 5);
        ExecutorService executor = Executors.newFixedThreadPool(1);

        List<Future<BigInteger>> resultList = new ArrayList<>();
        FactorialCalculator calculator = null;

        for (int a = 0; a < arr.length; a++) {
            calculator = new FactorialCalculator(arr[a]);
            Future<BigInteger> result = executor.submit(calculator);
            resultList.add(result);
        }
/*        for (Future<BigInteger> res : resultList) {
            BigInteger num = res.get();
            System.out.println(num);
        }*/

        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


}

class FactorialCalculator implements Callable<BigInteger> {
    public static Map<Integer, BigInteger> factoryMap = new ConcurrentHashMap<>();
    private final Integer number;
    private int count = 1;
    BigInteger resultCalc = new BigInteger("1");
    public FactorialCalculator(Integer number) {
        this.number = number;

    }


    @Override
    public BigInteger call() throws Exception {
       // Integer maxKey = Collections.max(factoryMap.keySet());

        Iterator iterator = factoryMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            if (key < number) {
                count=key;
            }else {
                return factoryMap.get(number);
            }
        }


        for (int i = count; i <= number; i++) {
            resultCalc = (factoryMap.get(count)).multiply(new BigInteger(i + ""));
            factoryMap.put(i, resultCalc);

        }
        System.out.printf("Factorial of %d is :: %d\n", number, factoryMap.get(number));
        return resultCalc;
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







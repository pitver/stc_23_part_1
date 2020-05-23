package ru.vershinin.lesson7;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

public class Main {


    /**
     * вычисления факторила путем
     * распараллеливания вычисления для разных чисел
     * с запоминания факториал для одного числа и использованием
     * для выесдения другого
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] arr = GetSortArray.getNumbers(5);// получаем отсортированный массив
        ExecutorService executor = Executors.newFixedThreadPool(1);
        List<Future<BigInteger>> resultList = new ArrayList<>();
        FactorialCalculator calculator;
        // циклом проходимся по всему массиву
        for (int a = 0; a < arr.length; a++) {
            calculator = new FactorialCalculator(arr[a]);
            Future<BigInteger> result = executor.submit(calculator);
            resultList.add(result);
        }

        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);// блокируем на 1 сек
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();//закрываем
    }


}

class FactorialCalculator implements Callable<BigInteger> {

    public static Map<Integer, BigInteger> factoryMap = new ConcurrentHashMap<>();
    private final Integer number;
    private static int count = 1;
    static BigInteger resultCalc = new BigInteger("1");


    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    /**
     * для эфективного вычисления сохраняем предъидущий результат факториала
     * в потокобезопасную коллекцию ConcurrentHashMap<>(), где key- это число из массива, value- факториал этого числа,
     * и при вычичлении нового значения факториала используем уже вычесленный
     *
     * @return возвращает значение факториала, тип Future<BigInteger>
     * @throws Exception
     */
    @Override
    public BigInteger call() throws Exception {
           // получаем ключ и значения факториала
        Iterator iterator = factoryMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            if (key < number) {
                count = key;
                resultCalc = factoryMap.get(count);
            }
        }


        count++;//чтобы избежать повторного использования предъидущего значения ключа, увеличиваем его на 1
        for (int i = count; i <= number; i++) {
            resultCalc = resultCalc.multiply(new BigInteger(i + ""));
            factoryMap.put(i, resultCalc);
        }

        System.out.printf("Factorial of %d is :: %d\n", number, factoryMap.get(number));
        return resultCalc;
    }
}








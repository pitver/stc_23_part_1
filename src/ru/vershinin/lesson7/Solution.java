package ru.vershinin.lesson7;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;



public class Solution {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1000; i <= 1010; i++) {
            futures.add(executor.submit(getTask(i)));
        }

        futures.add(executor.submit(getTask(10000000)));

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

    }

    public static Callable<String> getTask(final int i) {
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                long summ = 0;
                for (int j = 1; j < i + 1; j++) {
                    summ += j;
                }
                return String.valueOf(summ);
            }
        };

        return task;
    }
}


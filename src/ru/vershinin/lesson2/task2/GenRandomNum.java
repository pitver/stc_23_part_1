package ru.vershinin.lesson2.task2;

/**
 * GenRandomNum
 *
 * @author Вершинин Пётр
 */
public class GenRandomNum {
    /**
     * get random number
     *
     * @param min -min. для рандомных чисел
     * @param max -max.для рандомных чисел
     * @return result рандомное число
     * @throws Exception если result<0
     */
    private   double getRandomNum(double min, double max) throws Exception {
        double result = (Math.random()*((max-min)+1))+min;
        if(result<0){
            throw new Exception("Exception: result <0!");
        }
        return result;
    }

    /**
     * get square root
     *
     * @param sqrt-число для вычисления квадратного корня
     * @return квадратный корень из заданного числа
     */
    private double getSQRT(double sqrt){
       return Math.sqrt(sqrt);
    }

    /**
     * generating random numbers. For each number k, calculate the square root of q.
     * If sqrt(q) is equal to k, then display this number on the screen.
     * Provide that the initial numbers can be negative, in this case, generate an exception.
     *
     * @param minNum min. для рандомных чисел
     * @param maxNum max.для рандомных чисел
     * @param count количество генерируемых чисел
     * @param numK число к из условия
     */
    protected void searchNumber(int minNum, int maxNum, int count, int numK) {
        try {
            for (int i = 0; i < count; i++) {
                double res = getRandomNum(minNum, maxNum);
                if (i % numK == 0) {
                    double sqrt = getSQRT(res);
                    if(Math.round(sqrt)==numK){
                        System.out.println("number "+sqrt);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

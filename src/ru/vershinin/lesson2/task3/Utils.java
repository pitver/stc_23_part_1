package ru.vershinin.lesson2.task3;

import java.util.Random;

/**
 * Класс для формирования массива Person
 * Utils
 *
 * @author Вершинин Пётр
 */
public class Utils {
    /**
     * генерация рандомного поля age тип int
     * @param min -минимум для random
     * @param max -максимум для random
     * @return  return результат вычесления random
     */
    protected static int getRandomAge(double min, double max) {
        int result = (int) ((Math.random() * ((max - min) + 1)) + min);
        return result;
    }

    /**
     * метод генерирует текстовые поле, используюя символы с "a" по "z",
     *
     * @return возвращает сгенерирование String поле- generatedString
     */
    protected static String getRandomName() {
        /**
         * символ 'a'
         */
        int leftLimit = 97; // letter 'a'
        /**
         * символ 'z'
         */
        int rightLimit = 122; // letter 'z'
        /**
         * длина генерируемого поля
         */
        int targetStringLength = 5;
        Random random = new Random();

        /**
         * В цикле формируется поле, путем добавления в StringBuilder buffer
         * рандомного символа
         */
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    /**
     * генерация рандомного поля gender тип Gender
     * по умолчанию формируется Gender.MALE, но если остаток от деления
     * рандомного значения ==0, то  формируется Gender.FEMALE
     *  @param min -минимум для random
     * @param max -максимум для random
     * @return gender
     */
    protected static Gender getRandomGender(double min, double max) {

        Gender gender= Gender.MALE;
        int result = (int) ((Math.random() * ((max - min) + 1)) + min);
        if(result%2==0){
          gender= Gender.FEMALE;
        }
        return gender;
    }
}

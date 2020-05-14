package ru.vershinin.lesson6.Task2;

import java.util.Random;

public class RandomChar {
    /**
     * метод генерирует текстовые поле, используюя символы с "a" по "z",
     *
     * @return возвращает сгенерирование String поле- generatedString
     */
    protected static String getRandomChar() {
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
        int targetStringLength = 7;
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
}

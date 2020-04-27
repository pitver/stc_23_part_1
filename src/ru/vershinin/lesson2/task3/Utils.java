package ru.vershinin.lesson2.task3;

import java.util.Random;

/**
 * Utils
 *
 * @author Вершинин Пётр
 */
public class Utils {
    protected static int getRandomAge(double min, double max) {
        int result = (int) ((Math.random() * ((max - min) + 1)) + min);
        return result;
    }

    protected static String getRandomName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();


        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    protected static Gender getRandomGender(double min, double max) {

        Gender gender= Gender.MALE;
        int result = (int) ((Math.random() * ((max - min) + 1)) + min);
        if(result%2==0){
          gender= Gender.FEMALE;
        }
        return gender;
    }
}

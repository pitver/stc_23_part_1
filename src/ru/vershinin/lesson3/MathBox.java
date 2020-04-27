package ru.vershinin.lesson3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * MathBox
 *
 * @author Вершинин Пётр
 */
public class MathBox extends ObjectBox {
    ObjectBox ob = new ObjectBox();
    Set<Integer> num = new HashSet<Integer>() {
    };

    /**
     * Конструктор на вход получает массив Number.
     * Элементы не могут повторяться.
     * Элементы массива внутри объекта раскладываются в подходящую коллекцию
     * @param numbers
     */
    public MathBox(Object[] numbers) {
        try {
            if (numbers.getClass().getName().contains("Object")) {
                throw new RuntimeException("объект должен быиметь тип Number");
            }
            for (Object n : numbers) {
                num = (Set<Integer>) ob.addObject(n);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * возвращающает сумму всех элементов коллекции.
     * @param mathBox
     * @return
     */
    public Integer summator(MathBox mathBox) {
        Integer result = 0;
        for (Integer n : mathBox.num) {
            result = result + n;
        }
        return result;
    }

    /**
     * выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
     * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
     * @param div -случайное значение
     */
    public void splitter(int div) {
        Set<Integer> tempNum = new HashSet<Integer>() {
        };
        for (Integer n : num) {
            try {
                Integer resDiv = n / div;
                tempNum.add(resDiv);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        num.clear();
        for (Integer m : tempNum) {
            num.add(m);
        }
    }

    @Override
    public Object deleteObject(Object delObj) {
        return num = (Set<Integer>) ob.deleteObject(delObj);
    }
}

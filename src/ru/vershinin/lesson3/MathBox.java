package ru.vershinin.lesson3;

import java.util.HashSet;
import java.util.Set;

/**
 * MathBox
 *
 * @author Вершинин Пётр
 */
public class MathBox extends ObjectBox<Number> {

    /**
     * конструктор
     * @param numbers тип Number[]
     */
    public MathBox(Number[] numbers) {
        super(numbers);
    }

    /**
     * добавление нового объекта в коллекцию
     * @param addObj тип T
     */
    @Override
    public void addObject(Number addObj) {
        try {
            if (addObj.getClass().getName().contains("Object")) {
                throw new TypeException("объект должен быиметь тип Number");
            }
            super.addObject(addObj);
        } catch (TypeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * возвращающает сумму всех элементов коллекции.
     *
     *
     * @return
     */
    public Number summator() {
        int result = 0;
        for (Number n : obj) {
            result = result + n.intValue();
        }
        return result;
    }

    /**
     * выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
     * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
     *
     * @param div -случайное значение
     */
    public void splitter(int div) {
        Set<Number> tempNum = new HashSet<>();
        for (Number n : obj) {
            try {
                Integer resDiv = n.intValue() / div;
                tempNum.add(resDiv);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
        obj.clear();
        obj.addAll(tempNum);

    }

    /**
     * @param num тип T
     */
    @Override
    public void deleteObject(Number num) {
        super.deleteObject(num);
    }
}

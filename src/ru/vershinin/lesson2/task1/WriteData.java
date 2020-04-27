package ru.vershinin.lesson2.task1;

import java.util.List;


/**
 * WriteData
 *
 * @author Вершинин Пётр
 */
public class WriteData {

    /**
     * calling an error NPE
     */
    protected void subTask_1() {

        try {
            String data = null;
            data.hashCode();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * print some text
     *
     * @param data some String[] value
     */
    protected void subTask_2(String[] data) {
        for (int i = 0; i < data.length; i++) {
            try {
                System.out.println(data[i + 1]);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * calling an error using throw
     *
     * @param list some List<String> value
     */
    protected void subTask_3(List<String> list) {
        for (String text : list) {
            try {
                if (text == null) {
                    throw new SomeException("Exception: text is null!");
                }
                System.out.println(text);
            } catch ( SomeException e) {
                e.printStackTrace();
            }
        }

    }

}

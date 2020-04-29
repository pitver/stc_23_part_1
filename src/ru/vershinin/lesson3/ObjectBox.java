package ru.vershinin.lesson3;

import java.util.*;

/**
 * ObjectBox
 *
 * @author Вершинин Пётр
 */
public class ObjectBox<T> {
      Set<T> obj;
    /**
     *  Конструктор на вход получает массив T[].
     *  Элементы не могут повторяться.
     *  Элементы массива внутри объекта раскладываются в подходящую коллекцию
     *
     * @param object
     */
    public ObjectBox(T[] object) {
        obj=new HashSet<>(Arrays.asList(object));
    }
    /**
     * добавление объекта в коллекцию.
     * @param addObj тип T
     * @return
     */
    public void addObject(T addObj){
        obj.add(addObj);
    }
    /**
     * проверка наличия объекта в коллекции и при наличии удаляющий его
     * @param num тип T
     */
    public void deleteObject(T num){

        Iterator <T> iter=obj.iterator();
        while (iter.hasNext()){
            T ob= iter.next();
            if(ob==num){
                iter.remove();
            }
        }
    }

    /**
     * вывод содержимого коллекции в строку.
     *
     */
    public void dump( ){
        System.out.println(toString());
    }

    /**
     * переопределенный метод, для вывода сформированной коллекции
     * @return obj-коллеция
     */
    @Override
    public String toString() {
        return "Содержимое коллекции {" +
                "obj=" + obj +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(obj, objectBox.obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obj);
    }
}

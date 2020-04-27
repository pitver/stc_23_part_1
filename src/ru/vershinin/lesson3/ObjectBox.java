package ru.vershinin.lesson3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * ObjectBox
 *
 * @author Вершинин Пётр
 */
public class ObjectBox {
    Set<Object> obj= new HashSet<Object>(){};

    @Override
    public String toString() {
        return "ObjectBox{" +
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

    /**
     * добавление объекта в коллекцию.
     * @param addObj
     * @return
     */
    public Object addObject(Object addObj){
        obj.add(addObj);
        return obj;
    }

    /**
     * проверка наличия объекта в коллекции и при наличии удаляющий его
     * @param delObj
     * @return
     */
    public Object deleteObject(Object delObj){
        boolean check=false;
        for (Object s:obj){
            if(s==delObj){
                check=true;
            }
        }
        if(check){
                obj.remove(delObj);
        }
        return obj;
    }

    /**
     * вывод содержимого коллекции в строку.
     * @param printObj
     */
    public void dump(Object printObj){
        System.out.println(printObj.toString());
    }
}

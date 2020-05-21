package ru.vershinin.lesson8.example;

/**
 * MyEnum
 *
 * @author Вершинин Пётр
 */
public enum MyEnum {
    EnumElement1(0),
    EnumElement2(1),
    EnumElement3(2);

    int id;

    MyEnum(int id)
    {
        this.id=id;
    }

    int getId()
    {
        return id;
    }
}

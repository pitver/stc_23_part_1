package ru.vershinin.lesson24.pojo;

/**
 * Shop
 *
 * @author Вершинин Пётр
 */
public class Shop {
    protected int numberOrder;
    Order order;

    public Shop() {
    }

    public Shop(int numberOrder, Order order) {
        this.numberOrder = numberOrder;
        this.order = order;
    }

    public int getNumberOrder() {
        // при тестировании закоментить
        /*Random rd= new Random();
        return rd.nextInt(9999);*/
        return 33;
    }


}

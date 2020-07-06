package ru.vershinin;

/**
 * ZeroNumber
 *
 * @author Вершинин Пётр
 */
public class ZeroNumber extends Number{
    public ZeroNumber(Number number) {
        super(number);
    }

    @Override
    public void process(int n) {
        if(n==0){
            System.out.println("zero");
        }else {
            super.process(n);
        }
    }
}

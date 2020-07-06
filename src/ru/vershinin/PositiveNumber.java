package ru.vershinin;

/**
 * PositiveNumber
 *
 * @author Вершинин Пётр
 */
public class PositiveNumber extends Number{
    public PositiveNumber(Number number) {
        super(number);
    }

    @Override
    public void process(int n) {
        if(n>0){
            System.out.println("positive");
        }else {
            super.process(n);
        }
    }
}

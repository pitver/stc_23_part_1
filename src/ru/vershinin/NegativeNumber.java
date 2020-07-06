package ru.vershinin;

/**
 * NegativeNumber
 *
 * @author Вершинин Пётр
 */
public class NegativeNumber extends Number{
    public NegativeNumber(Number number) {
        super(number);
    }

    @Override
    public void process(int n) {
        if(n<0){
            System.out.println("negativa");
        }else {
            super.process(n);
        }
    }
}

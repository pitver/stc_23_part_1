package ru.vershinin;

public class Main {

    public static void main(String[] args) {
	Number number = new NegativeNumber(new ZeroNumber(new PositiveNumber(null)));
	number.process(4);
	number.process(0);
	number.process(-4);
    }
}

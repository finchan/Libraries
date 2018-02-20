package com.xavier.designpattern.dp22decorator.sample;

/**
 * Created by Xavier on 2018-02-19.
 */
public class Client {
    public static void main(String[] args) {
        Component c1 = new ConcreteComponent();

        Decorator d1 = new MonthPrizeDecorator(c1);
        Decorator d2 = new SumPrizeDecorator(d1);

        double x = d1.calcPrize("Xavier", null, null);
        System.out.println("Xavier's prize: " + x);

        double j = d2.calcPrize("Javier", null, null);
        System.out.println("Javier's prize: " + j);

        Decorator d3 = new GroupPrizeDecorator(d2);
        double z = d3.calcPrize("Zavier", null, null);
        System.out.println("Zavier's prize: " + z);
    }
}

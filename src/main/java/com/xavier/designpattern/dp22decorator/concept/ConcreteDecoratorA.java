package com.xavier.designpattern.dp22decorator.concept;

/**
 * Created by Xavier on 2018-02-19.
 */
public class ConcreteDecoratorA extends Decorator{
    private String addedState;
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    public String getAddedState() {
        return addedState;
    }

    public void setAddedState(String addedState) {
        this.addedState = addedState;
    }

    @Override
    public void operation() {
        //Add additional codes---<
        super.operation();
        //Add additional codes--->
    }
}

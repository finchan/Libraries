package com.xavier.designpattern.dp22decorator.concept;

/**
 * Created by Xavier on 2018-02-19.
 */
public class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    private void addedBehavior() {
    }

    public void operation( ) {
        //Add additional codes
        super.operation();
        addedBehavior();
        //Add additional codes
    }
}

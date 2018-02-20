package com.xavier.designpattern.dp22decorator.concept;

/**
 * Created by Xavier on 2018-02-19.
 */
public abstract class Decorator extends Component {

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

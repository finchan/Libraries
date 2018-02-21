package com.xavier.designpattern.dp12observer.concept;

/**
 * Created by Xavier on 2018-02-21.
 */
public class ConcreteObserver implements  Observer{
    private String observerState;

    public void update(Subject subject) {
        observerState = ((ConcreteSubject) subject).getSubjectState();
    }
}

package com.xavier.designpattern.dp12observer.concept;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier on 2018-02-21.
 */
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update(this);
        }
    }
}

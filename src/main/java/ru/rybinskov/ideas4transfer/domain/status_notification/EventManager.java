package ru.rybinskov.ideas4transfer.domain.status_notification;

import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class EventManager {

    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    protected void notify(OrderStatus status){
        for (Observer observer : observers) {
            observer.update(this, status);
        }
    }

}

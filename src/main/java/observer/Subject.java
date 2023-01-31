package observer;

import java.util.List;

public abstract class Subject {
    public abstract void registrazione(Observer o);
    public abstract void rimuoviObserver(Observer o);
    public abstract void notifyObservers();
    protected List<Observer> observerList;
}

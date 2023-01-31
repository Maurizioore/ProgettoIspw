package observer;

import java.util.List;

public abstract class Subject {
    public abstract void Registrazione(Observer o);
    public abstract void RimuoviObserver(Observer o);
    public abstract void notifyObservers();
    protected List<Observer> observerList;
}

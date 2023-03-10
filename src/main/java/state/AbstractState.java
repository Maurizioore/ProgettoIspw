package state;

public abstract class AbstractState {
    //classe astratta, trova i suoi figli in OnlineState e offlineState, La classe state machine imp
    //è collegata da una composizione con questa classe e tramite questo collegamento( che e' segnato da un attributo)
    //la classeStateMachineImpl riesce a mantenere in quella variabile lo stato corrente in cui l'utente si trova
    public static AbstractState getInitialState(){
        return new OfflineState();
    }
    public abstract void login(StateMachineImpl s);
    public abstract void logout(StateMachineImpl s);
}

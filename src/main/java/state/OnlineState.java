package state;

public class OnlineState extends AbstractState{

    @Override
    public void login(StateMachineImpl s) {
        //non fa nulla come metodo sono solo costretto a implementarlo da contratto con il padre
    }
    @Override
    public void logout(StateMachineImpl s) {
        //devo cambiare stato e passare da online a offline
        //creo un nuovo stato OnlineState
        AbstractState newState= new OfflineState();
        //chiamo il metodo changeToState di StateMachineImpl per assegnare alla variabile che tiene lo stato
        //corrente, il nuovo stato che ho creato
        s.changeToState(newState);

    }

    @Override
    public String toString(){
        return "ONLINE";
    }
}

package state;

public class OfflineState extends AbstractState{

    @Override
    public void login(StateMachineImpl s) {
        //devo cambiare stato e passare da offline a online
        //creo un nuovo stato OnlineState
        AbstractState newState= new OnlineState();
        //chiamo il metodo changeToState di StateMachineImpl per assegnare alla variabile che tiene lo stato
        //corrente, il nuovo stato che ho creato
        s.changeToState(newState);

    }
    @Override
    public void logout(StateMachineImpl s){
        //non fa nulla come metodo sono solo costretto a implementarlo da contratto con il padre
    }


    @Override
    public String toString(){
        return "OFFLINE";
    }

}

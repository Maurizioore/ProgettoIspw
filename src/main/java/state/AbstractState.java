package state;

public abstract class AbstractState {
    public void entry(StateMachineImpl s){};
    public static AbstractState getInitialState(){
        AbstractState abstractState=new OfflineState();
        return abstractState;
    }
    public abstract void exit();
    public abstract void login(StateMachineImpl s);
    public abstract void logout(StateMachineImpl s);
}

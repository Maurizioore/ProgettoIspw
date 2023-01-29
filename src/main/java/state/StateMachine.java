package state;

public interface StateMachine {
    //la classe account conosce solo i metodi di questa classe, non sa nulla riguardo il abstarctState online offline
    //e tutti gli altri metodi che implementa StateMachineImpl
    public void goNext(Events e);

}

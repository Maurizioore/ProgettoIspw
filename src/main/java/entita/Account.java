package entita;

import state.Events;
import state.StateMachine;
import state.StateMachineImpl;

public class Account {
    //variabile stateMachine, rappresenta la composizione che il mio client ha con l'interfaccia
    private StateMachine stateMachine;
    //nel momento della creazione di un account il suo stato corrente viene messo offline, quindi la variabile
    //current state associata a stateMachineImpl sarà offline State kind of AbstractState
    public Account (){
        stateMachine=new StateMachineImpl();
    }
    //quando viene fatto l'accesso passo online, passo come evento il login_event che indica proprio che
    //ho svolto il login, questo metodo lo riceverà stateMachineImpl che e' un kind of StateMachine
    public void passaOnline(){
        this.stateMachine.goNext(Events.LOGIN);
    }
    //quando viene fatto il logout passo offline
    public void passaOffline(){
        this.stateMachine.goNext(Events.LOGOUT);
    }
    //metodo che torna lo stato attuale dell'account
    public String getStatoAttuale(){return this.stateMachine.toString();}
}

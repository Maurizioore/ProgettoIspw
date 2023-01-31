package entita;
import observer.Observer;
import observer.Subject;
import state.Events;
import state.StateMachine;
import state.StateMachineImpl;

import java.util.ArrayList;

public class Account extends Subject {
    //variabile stateMachine, rappresenta la composizione che il mio client ha con l'interfaccia
    private String nomeUtente;
    private String codiceUtente;
    private static Account account;
    private static StateMachine stateMachine;
    //nel momento della creazione di un account il suo stato corrente viene messo offline, quindi la variabile
    //current state associata a stateMachineImpl sarà offline State kind of AbstractState
    public static Account getInitialAccount(){
        if(account==null){
           account=new Account();
        }
        return account;
    }
    private Account (){
        stateMachine=new StateMachineImpl();
        this.observerList=new ArrayList<>();
    }
    //quando viene fatto l'accesso passo online, passo come evento il login_event che indica proprio che
    //ho svolto il login, questo metodo lo riceverà stateMachineImpl che e' un kind of StateMachine
    public void passaOnline(){
        this.stateMachine.goNext(Events.LOGIN);
        //nel momento in cui passa online notifica i suoi observer
        notifyObservers();
    }
    //quando viene fatto il logout passo offline
    public void passaOffline(){

        this.stateMachine.goNext(Events.LOGOUT);
        this.codiceUtente=null;
        this.nomeUtente=null;
        notifyObservers();
    }
    //metodo che torna lo stato attuale dell'account
    public String getStatoAttuale(){return this.stateMachine.toString();}

    @Override
    public void Registrazione(Observer o) {
        this.observerList.add(o);
    }

    @Override
    public void RimuoviObserver(Observer o) {
        int i=this.observerList.indexOf(o);
        if(i>0) {
            this.observerList.remove(i);
        }
    }
    @Override
    public void notifyObservers() {
        for(Observer o:this.observerList){
            o.update(getStatoAttuale(),getNomeUtente(),getCodiceUtente());
        }
    }
    public void setCredenziali(String nomeUtente,String codiceUtente){
        //questo metodo viene chiamato da login e logout i quali successivamente chiamano passa online e passa offline,
        //saranno subito questi altri 2 a notificare il tutto
        this.nomeUtente=nomeUtente;
        this.codiceUtente=codiceUtente;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getCodiceUtente() {
        return codiceUtente;
    }
}

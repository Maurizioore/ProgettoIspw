package state;

public class StateMachineImpl implements StateMachine{
    //questa classe mantiene un riferimento allo stato corrente dell'account, lo fa con una variabile chiamata
    //current state che altro non rappresenta la composizione che la mia StateMachineImpl possiede con la classe
    //Abstract State
    private AbstractState currentState;
    public StateMachineImpl(){
        //il costruttore viene chiamato una volta sola, cioe' quando viene creato l'account,
        //nel momento della creazione un account viene messo in stato offline
        this.currentState=AbstractState.getInitialState();
        //una serie di passaggi che mostrano l'attuale stato dell'utente, all'inizio e' offline quindi viene mostrato
        //l'output OFFLINE
        this.currentState.entry(this);
    }
    @Override
    public void goNext(Events e) {
        //switch case per scegliere quale metodo chiamare in base all'evento che si e' verificato

        switch (e){
            //il metodo passaOnline porta con se l'evento login
            case LOGIN:
                //viene chiamato il metodo login associato al current state dell'account, se sono offline viene
                //chiamato il login di OfflineState( che svolge il login), se sono ONLINE, viene chiamato il login
                //di OnlineState che non fa nulla ( lo implemento senza fargli fare niente perche sono
                // costretto dal contratto firmato con AbstractSate) dato che se sono online non ha senso fare login
                this.currentState.login(this);
                break;
            case LOGOUT:
                //il metodo passaOffline porta con se l'evento logout
                //discorso duale a quello del login per il logout, qui se sono offline chiamo il logout di offline
                //che non fa nulla, se sono ONLINE, chiamo il logout di OnlineState che farà il logout
                this.currentState.logout(this);
                break;
            default:
                break;
        }
    }
    public void changeToState(AbstractState s){
        //exit dallo stato corrente
        currentState.exit();
        //entriamo nel nuovo stato
        currentState=s;
        s.entry(this);
    }
    public void showState(){
        System.out.println(this.currentState.toString());
    }
    public AbstractState getState(){
        return this.currentState;
    }

    @Override
    public String toString(){
        System.out.println("sono una sout presente in state machine imple, il to string del courrent state e' il seguente " +
                String.valueOf(this.currentState));
        return String.valueOf(this.currentState);
    }

}

package bean;

import entita.Account;
import observer.Observer;
import observer.Subject;

public class BeanObserverAccount implements Observer {
    //ha un riferimento a subject, e' colui che implementa l'observer, nel diagramma uml e' come se fosse il
    //concrete observer che riceve gli update e che ha un riferimento a concrete subject, nella mia app
    //questo riferimento non e' verso concrete subject ma verso l'interfaccia subject perche non voglio
    //che sia lui che cambi lo stato del mio concrete subject, inoltre la costruisco come singleton questa classe
    private String nomeUtente;
    private String statoAttuale;
    private Subject subject;
    private static BeanObserverAccount beanObserver;
    public static BeanObserverAccount getObserver(){
        if(beanObserver==null){
            //registro questo bean Observer ( concrete observer) ad un subject ( in questo caso type of subject)
            beanObserver=new BeanObserverAccount(Account.getInitialAccount());
        }
        return beanObserver;
    }

    private BeanObserverAccount(Subject subject){
        //assegno alla variabile locale il type of Subject
        this.subject=subject;
        //registro questo concrete observer alla lista di observer presente in subject e questa lista e' conosciuta
        //anche dal concrete subject il quale la usa per inviare gli update ai concrete observer
        this.subject.registrazione(this);
    }
    //viene chiamato da una concrete subject verso tutti gli observer che la guardano, viene chiamata quando accade un cambiamento
    //che richiede la notifica ai concrete observer
    @Override
    public void update(String stato,String nomeUtente) {
        //il concrete subject ha cambiato qualcosa e questo cambiamento lo ha norificato a tutti i suoi observer
        //questo observer cambia quindi lo stato delgi attributi che hanno interessato il cambiamento , e questi attributi
        //sono proprio quelli che va a vedere il controller grafico senza accesso, che usa la composizione con il
        //concrete observer (this) per comunicare all'utente in modo grafico i cambiamenti che ci sono stati
        this.statoAttuale=stato;
        this.nomeUtente=nomeUtente;
    }
    public String getStatoAttuale(){
        return this.statoAttuale;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }
}

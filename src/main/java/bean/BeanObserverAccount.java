package bean;

import entita.Account;
import observer.Observer;
import observer.Subject;

public class BeanObserverAccount implements Observer {
    //ha un riferimento a subject
    private String nomeUtente;
    private String statoAttuale;
    private Subject subject;
    private static BeanObserverAccount beanObserver;
    public static BeanObserverAccount getObserver(){
        if(beanObserver==null){
            beanObserver=new BeanObserverAccount(Account.getInitialAccount());
        }
        return beanObserver;
    }

    private BeanObserverAccount(Subject subject){

        this.subject=subject;
        this.subject.Registrazione(this);
    }
    @Override
    public void update(String stato,String nomeUtente) {
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

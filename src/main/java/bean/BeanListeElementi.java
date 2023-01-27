package bean;

import java.util.ArrayList;
import java.util.List;

public class BeanListeElementi {
    //questo bean viene creato dal controller grafico attive e viene passato al controller applicativo
    //il controller applicativo riempi le 3 liste definite qui dentro e queste verranno poi usate dal
    //controller grafico per mostrare il giusto output all'utente
    //i setter vengono fatti dal controller applicativo, i getter dal controller grafico
    public List<String> listaNumeriSeriali;
    public List<String> listaIndirizzi;
    public List<String> listaStato;

    public List<String> listaIndirizziBucaStradale;
    public List<String> listaProfonditaBucaStradale;
    public List<String> listaStatoBucaStradale;

    public BeanListeElementi(){
        listaNumeriSeriali=new ArrayList<>();
        listaIndirizzi=new ArrayList<>();
        listaStato=new ArrayList<>();
        listaIndirizziBucaStradale=new ArrayList<>();
        listaProfonditaBucaStradale=new ArrayList<>();
        listaStatoBucaStradale=new ArrayList<>();
    }
    //per i pali
    public void gestisciListaNumeriSeriali(String nuovoElemento){
        //questo e' un setter
        listaNumeriSeriali.add(nuovoElemento);
    }
    public void gestisciIndirizzi(String nuovoIndirizzo){
        listaIndirizzi.add(nuovoIndirizzo);
    }
    public void gestisciStato(String nuovoStato){
        listaStato.add(nuovoStato);
    }
    public String restituisciNumeroSeriale(int i){
        return listaNumeriSeriali.get(i);
    }
    public  String restituisciIndirizzo(int i){
        return listaIndirizzi.get(i);
    }
    public String restituisciStato(int i){
        return listaStato.get(i);
    }

    //per le buche
    public void gestisciListaProfonditaBucaStradale(String nuovoElemento){
        listaProfonditaBucaStradale.add(nuovoElemento);
    }
    public void gestisciIndirizziBuca(String nuovoIndirizzo){
        listaIndirizziBucaStradale.add(nuovoIndirizzo);
    }
    public void gestisciStatoBucaStradale(String nuovoStato){
        listaStatoBucaStradale.add(nuovoStato);
    }
    public String restituisciProfonditaBuca(int i){
        return listaProfonditaBucaStradale.get(i);
    }
    public  String restituisciIndirizzoBucaStradale(int i){
        return listaIndirizziBucaStradale.get(i);
    }
    public String restituisciStatoBucaStradale(int i){
        return listaStatoBucaStradale.get(i);
    }

}
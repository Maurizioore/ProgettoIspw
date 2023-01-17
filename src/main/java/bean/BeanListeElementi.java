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

    public BeanListeElementi(){
        listaNumeriSeriali=new ArrayList<>();
        listaIndirizzi=new ArrayList<>();
        listaStato=new ArrayList<>();
    }
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

}

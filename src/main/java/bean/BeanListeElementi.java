package bean;

import com.example.progettoispw.controllergrafici.TypeOfSegnalazione;

import java.util.ArrayList;
import java.util.List;

public class BeanListeElementi {
    //questo bean viene creato dal controller grafico attive e viene passato al controller applicativo
    //il controller applicativo riempi le 3 liste definite qui dentro e queste verranno poi usate dal
    //controller grafico per mostrare il giusto output all'utente
    //i setter vengono fatti dal controller applicativo, i getter dal controller grafico
    //questa classe infrange la legge di demetra( principi di conoscenza minima) perche e' come se conoscesse il tipo di
    //dato che deve prendere, e un cambiamento su un altra classe influenza per forza anche questa, purtroppo e' una decisione
    //necessaria per gli scopi di questo bean
    public final List<String> listaNumeriSeriali;
    public final List<String> listaIndirizzi;
    public final List<String> listaStato;

    public final List<String> listaIndirizziBucaStradale;
    public final List<String> listaProfonditaBucaStradale;
    public final List<String> listaStatoBucaStradale;

    private TypeOfSegnalazione typeOfSegnalazione;

    public BeanListeElementi(TypeOfSegnalazione type_of_segnalazione){
        listaNumeriSeriali=new ArrayList<>();
        listaIndirizzi=new ArrayList<>();
        listaStato=new ArrayList<>();
        listaIndirizziBucaStradale=new ArrayList<>();
        listaProfonditaBucaStradale=new ArrayList<>();
        listaStatoBucaStradale=new ArrayList<>();
        this.typeOfSegnalazione=type_of_segnalazione;
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
    public TypeOfSegnalazione restituisciTipoSegnalazione(){return  this.typeOfSegnalazione;}

}

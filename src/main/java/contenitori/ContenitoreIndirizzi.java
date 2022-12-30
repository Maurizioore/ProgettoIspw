package contenitori;

import java.util.ArrayList;

public class ContenitoreIndirizzi {
    //classe che crea un contenitore di tutti i pali dell'illuminazione che ho a disposizione nella località
    //coperta dalla mia applicazione, utilizza un singleton in modo che viene creata sempre e solo un istanza
    //e quindi una sola array list che contenente i numeri seriali
    //devo crearne una simile per gli indirizzi
    private static ContenitoreIndirizzi contenitoreIndirizzi=null;
    private static ArrayList<String> contenitore=null;
    private ContenitoreIndirizzi(){
        contenitore = new ArrayList<>();
        riempiContenitore(contenitore);
    }
    private void riempiContenitore(ArrayList<String> contenitore){
        contenitore.add("via roma");
        contenitore.add("via maia");
        contenitore.add("contrada giulio cesare");
        contenitore.add("via torino");
    }

    public static ContenitoreIndirizzi getInstance(){
        if(contenitoreIndirizzi==null){
            contenitoreIndirizzi=new ContenitoreIndirizzi();
        }
        return contenitoreIndirizzi;
    }
    public ArrayList<String> ottieniContenitore(){
        if(contenitore==null){
            return null;
        }
        return contenitore;
    }
}

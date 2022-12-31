package contenitori;
import java.util.ArrayList;
import java.util.List;


public class ContenitorePaliIlluminazione implements Contenitore{
    //classe che crea un contenitore di tutti i pali dell'illuminazione che ho a disposizione nella località
    //coperta dalla mia applicazione, utilizza un singleton in modo che viene creata sempre e solo un istanza
    //e quindi una sola array list che contenente i numeri seriali
    //devo crearne una simile per gli indirizzi
    private static ContenitorePaliIlluminazione contenitorePaliIlluminazione=null;
    private static ArrayList<String> contenitore=new ArrayList<>();
    private ContenitorePaliIlluminazione(){
        riempiContenitore(contenitore);
    }
    private void riempiContenitore(ArrayList<String> contenitore){
        contenitore.add("000000000000");
        contenitore.add("111111111111");
        contenitore.add("222222222222");
        contenitore.add("123456789123");
    }

    public static Contenitore getInstance(){
        if(contenitorePaliIlluminazione==null){
            contenitorePaliIlluminazione=new ContenitorePaliIlluminazione();
        }
        return contenitorePaliIlluminazione;
    }
    public List<String> ottieniContenitore(){
        return contenitore;
    }
}

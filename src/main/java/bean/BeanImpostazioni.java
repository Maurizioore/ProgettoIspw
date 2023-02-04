package bean;

public class BeanImpostazioni {
    private String nome;
    private String cognome;
    private int eta;
    private String sesso;
    public BeanImpostazioni(){

    }
    public BeanImpostazioni(String nome,String cognome,int eta,String sesso){
        this.nome=nome;
        this.cognome=cognome;
        this.sesso=sesso;
        this.eta=eta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }
}

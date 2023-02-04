package entita;

import java.io.Serializable;

public class Impostazioni implements Serializable {
    //entità serializzabile, unica in tutto il sistema
    private String nome;
    private String cognome;
    private String sesso;
    private int eta;

    public Impostazioni(String nome, String cognome,int eta,String sesso){
        this.nome=nome;
        this.cognome=cognome;
        this.eta=eta;
        this.sesso=sesso;
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

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }
}

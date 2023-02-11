package utility;

public class Printer {
    private Printer(){}
    public static void print(String messaggio){
        System.out.println(messaggio);
    }
    public static void error(String errore){
        System.err.println(errore);
    }
}

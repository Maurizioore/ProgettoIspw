package cli;

public class Clear {

    private Clear(){}

    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
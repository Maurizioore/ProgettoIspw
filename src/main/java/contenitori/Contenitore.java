package contenitori;

import java.util.ArrayList;
import java.util.List;

public interface Contenitore {

    private void riempiContenitore(ArrayList<String> contenitore) {
    }

    List<String> ottieniContenitore();

    static Contenitore getInstance(){return null;}
}

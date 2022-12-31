package contenitori;

import java.util.ArrayList;
import java.util.List;

public interface Contenitore {
    List<String> ottieniContenitore();
    static Contenitore getInstance(){return null;}
}

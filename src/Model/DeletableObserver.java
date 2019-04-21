package Model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public interface DeletableObserver {
    void delete(Deletable d, CopyOnWriteArrayList<GameObject> loot);
}

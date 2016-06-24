package interfaces;

import items.Item;
import items.User;

import java.util.ArrayList;

/**
 * Created by Test on 13.06.2016.
 */
public interface Selectable {
    public ArrayList<Item> select(String nameDB);
}

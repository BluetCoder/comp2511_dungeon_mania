package dungeonmania.entity;

import java.util.List;
import dungeonmania.inventory.*;

public abstract interface DropInterface {
    /**
     * List of possible items that a enemy entity can drop based on chances
     * @return
     */
    public List<Item> drop(String gamemode);
}

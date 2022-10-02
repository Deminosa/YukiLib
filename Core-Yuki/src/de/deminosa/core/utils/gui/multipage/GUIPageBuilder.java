package de.deminosa.core.utils.gui.multipage;

import java.util.HashMap;

import javax.annotation.Nonnull;

import de.deminosa.core.entitys.User;
import de.deminosa.core.utils.gui.GUI;

public class GUIPageBuilder {
    
    private final HashMap<Integer, GUI> pages;
    private final HashMap<User, Integer> userIndex;

    public GUIPageBuilder() {
        this.pages = new HashMap<>();
        this.userIndex = new HashMap<>();
    }

    public void addPage(@Nonnull GUI gui) {
        int index = getPages().size();
        while (getPages().containsKey(index)) {
            index++;
        }
        setPage(index, gui);
    }

    public void setPage(int index, @Nonnull GUI gui) {
        pages.put(index, gui);
    }

    public GUI getGUIPage(int index) {
        return pages.get(index);
    }

    /**
     * <p>Open the first index</p>
     * @param player
     */
    public void openPage(User player) {
        openPage(player, 0);
    }

    /**
     * <p>Open the next index GUI</p>
     * @param player
     */
    public void openNextPage(User player) {
        int currentIndex = userIndex.get(player);
        int nextIndex = currentIndex++;
        
        if(pages.containsKey(nextIndex)) {
            openPage(player, nextIndex);
        }
    }

    /**
     * <p>Open the last index</p>
     * @param player
     */
    public void openLastPage(User player) {
        int currentIndex = userIndex.get(player);
        int nextIndex = currentIndex--;

        if(nextIndex < 0) nextIndex = 0;

        if(pages.containsKey(nextIndex)) {
            openPage(player, nextIndex);
        }
    }

    /**
     * <p>Open the GUI in the index</p>
     * @param player
     * @param index
     */
    public void openPage(User player, int index) {
        GUI gui = pages.get(index);
        gui.open(player);
        userIndex.put(player, index);
    }

    public HashMap<Integer, GUI> getPages() {
        return pages;
    }

}

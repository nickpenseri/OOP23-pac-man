package it.unibo.model.map.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.model.map.api.MapSelector;

/** class that manages the choice of the map. */
public class MapSelectorImpl implements MapSelector {

    private final List<String> mapList;
    private final Random rand;
    private final List<Boolean> checkList;
    private int count = 3;

    /**
     * initializes the class fields.
     */
    public MapSelectorImpl() {
        this.mapList = List.of("/map1.txt", "/map2.txt", "/map3.txt");
        this.rand = new Random();
        this.checkList = new ArrayList<>();
        for (int i = 0; i < this.mapList.size(); i++) {
            this.checkList.add(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMapName() {
        do {
            if (this.count == 0) {
                this.count = this.mapList.size() - 1;
                this.checkList.clear();
                for (int i = 0; i < this.mapList.size(); i++) {
                    this.checkList.add(true);
                }
            }
            final int index = this.rand.nextInt(this.mapList.size());
            if (this.checkList.get(index).equals(true)) {
                this.checkList.add(index, false);
                return this.mapList.get(index);
            }
        } while (this.count > 0);
        return "/map1.txt";
    }

}

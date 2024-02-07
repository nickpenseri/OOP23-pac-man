package it.unibo.model.map.impl;

import java.util.List;
import java.util.Random;

import it.unibo.model.map.api.MapSelector;

/** class that manages the choice of the map. */
public class MapSelectorImpl implements MapSelector {

    private final List<String> mapList;
    private final Random rand;
    private final List<Boolean> checkList;
    private int count = 4;

    /**
     * initializes the class fields.
     */
    public MapSelectorImpl() {
        this.mapList = List.of("map1.txt", "map2.txt", "map3.txt", "map4.txt");
        this.rand = new Random();
        this.checkList = List.of(true, true, true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMapName() {
        do {
            if (this.count == 0) {
                this.count = 4;
                this.checkList.clear();
                this.checkList.addAll(List.of(true, true, true, true));
            }
            final int index = this.rand.nextInt(this.mapList.size() - 1);
            if (this.checkList.get(index).equals(true)) {
                this.checkList.add(index, false);
                return this.mapList.get(index);
            }
        } while (this.count > 0);
        return "map1.txt";
    }

}

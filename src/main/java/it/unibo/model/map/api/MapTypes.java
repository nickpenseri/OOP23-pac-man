package it.unibo.model.map.api;

/**
 * enumeration of the various objects present in the map.
 */
public enum MapTypes {

    // 0 pickable 1 no-pickable 2 spawn-pac-man 3-spawn-ghost 4-gate-ghost 5 wall.

    /**
     * pickable objects.
     */
    PICKABLE,

    /**
     * no-pickable objects.
     */
    NO_PICKABLE,

    /**
     * spawn pac-man.
     */
    SPAWN_PAC_MAN,

    /**
     * spawn ghosts.
     */
    SPAWN_GHOST,

    /**
     * gate-ghosts.
     */
    GATE_GHOST,

    /**
     * walls.
     */
    WALL,

    /**
     * object behind walls.
     */
    BEHIND_WALL,
    /**
     * floor.
     */
    FLOOR;
}

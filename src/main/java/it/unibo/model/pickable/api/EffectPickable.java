package it.unibo.model.pickable.api;

/** Un puckabble item with some effect to do. */
public interface EffectPickable extends Pickable {
    /** Do the effect of the pickable item. */
    void doEffect();
}

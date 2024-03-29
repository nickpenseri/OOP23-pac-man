package it.unibo.model.pickable.impl;

import java.net.URL;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect and give you 50 points.
 */
public abstract class EffectPickableImpl extends PickableImpl implements EffectPickable {
    private final URL imageUrl = EffectPickable.class.getResource("/image/pickable/PickableEffect.png");
    static final int POINTS = 50;

    /**
     * Constructor of the EffectPickableImpl.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public EffectPickableImpl(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Return the Url of the image of the pickable.
     * 
     * @return the Url of the image of the pickable.
     */
    @Override
    public URL getImageUrl() {
        return imageUrl;
    }

    /**
     * Do the effect of the pickable.
     * 
     * @param pacman the pacman that will be affected by the effect.
     * @param ghosts the list of ghosts that will be affected by the effect.
     */
    @Override
    public abstract void doEffect(PacMan pacman, List<Ghost> ghosts);

}

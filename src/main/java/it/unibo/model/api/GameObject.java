package it.unibo.model.api;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;

public interface GameObject {

    Point2D.Double getPosition();

    void setPosition(Point2D.Double position);

    Image getImage();

    Dimension2D getDimension();
}

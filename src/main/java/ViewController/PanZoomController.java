package ViewController;

import java.awt.geom.AffineTransform;

public class PanZoomController {

    AffineTransform atx = new AffineTransform();

    double zoomIntensity = 0.2, scale = 1;
    double[] translate = {0, 0};

    public void pan(double[] vector)
    {
        translate[0] += vector[0];
        translate[1] += vector[1];

        atx.translate(vector[0], vector[1]);
    }

    public void zoomOnWorldPosition(double[] position, double zoomDirection)
    {
        double[] p = new double[position.length];
        atx.transform(position, 0, p, 0, position.length-1);

        zoomOnScreenPosition(p, zoomDirection);
    }

    public void zoomOnScreenPosition(double[] position, double zoomDirection)
    {
        double zoom = 1d + (zoomDirection * zoomIntensity);

        atx.translate(-translate[0], -translate[1]);

        atx.scale(zoom, zoom);

        translate[0] += (position[0] / (scale * zoom)) - (position[0] / scale);
        translate[1] += (position[1] / (scale * zoom)) - (position[1] / scale);

        atx.translate(translate[0], translate[1]);

        scale *= zoom;
    }

    public AffineTransform getTransform() {
        return atx;
    }

    public double getScale() {
        return scale;
    }
}

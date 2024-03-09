package View;

import ViewController.PanZoomController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class PanZoomPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    PanZoomController panZoomController;
    Point mousePosition;

    public PanZoomPanel(PanZoomController pzc)
    {
        panZoomController = pzc;

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(panZoomController.getTransform());

        renderImplementation(g);

        g2d.dispose();
    }

    protected abstract void renderImplementation(Graphics g);

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        mousePosition = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        double scale = panZoomController.getScale();
        double[] vec = {
                (e.getX() - mousePosition.getX()) / scale,
                (e.getY() - mousePosition.getY()) / scale
        };

        panZoomController.pan(vec);

        mousePosition = e.getPoint();
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int wheel = e.getWheelRotation() < 0 ? 1 : -1;
        double[] mouse = {e.getX() - this.getX(), e.getY() - this.getY()};

        panZoomController.zoomOnScreenPosition(mouse, wheel);
    }
}

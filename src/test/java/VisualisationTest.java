import View.PanZoomPanel;
import ViewController.PanZoomController;

import javax.swing.*;
import java.awt.*;

public class VisualisationTest {

    public static void main(String[] args) throws InterruptedException {
        JFrame window = new JFrame("Test Window");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(600,600);

        PanZoomController panZoomController = new PanZoomController();
        PanZoomPanel view = new PanZoomPanel(panZoomController) {
            double[][] data = new double[][]{
                    {0, 0},
                    {1, 0},
                    {0, 1},
                    {1, 1},
            };

            @Override
            protected void renderImplementation(Graphics g) {
                for (double[] d: data)
                {
                    g.setColor(Color.black);
                    g.fillOval((int)d[0], (int)d[1], 1, 1);
                }
            }
        };

        window.add(view);
        window.setVisible(true);

        panZoomController.pan(new double[]{view.getWidth() / 2, view.getHeight() / 2});
        panZoomController.zoomOnWorldPosition(new double[]{1, 1}, 100);

        while(true)
        {
            view.repaint();

            Thread.sleep(100);
        }
    }
}

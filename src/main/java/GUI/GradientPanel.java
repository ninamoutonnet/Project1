package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* This class creates a panel with a faded background */

public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();

        //Green option
        Color color9 = new Color(90,128,90);
        Color color10 = new Color(144,190,144);


        GradientPaint gp = new GradientPaint(0, 0, color10, 0, h, color9);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
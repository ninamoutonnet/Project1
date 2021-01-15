package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GradientPanel extends JPanel {

    //will allow the creation of a panel with a faded background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();

        //Green option
        Color color9 = new Color(40,128,40);
        Color color10 = new Color(144,190,144);


        GradientPaint gp = new GradientPaint(0, 0, color9, 0, h, color10);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
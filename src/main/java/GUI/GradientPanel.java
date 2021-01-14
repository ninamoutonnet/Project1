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

        //Pink purple option
        //Color color1 = new Color(222,2,103);
        //Color color2 = new Color(97,9,85);

        //Pink purple option
        Color color2 = new Color(172,2,80);
        Color color1 = new Color(95,5,95);

        //light blue to blue option
        Color color3 = new Color(102,178,255);
        Color color4 = new Color(153,204,255);

        //Yellows option
        Color color5 = new Color(255,255,204);
        Color color6 = new Color(255,222,113);

        //Purple option
        Color color7 = new Color(55,0,104);
        Color color8 = new Color(122,0,122);

        //Green option
        Color color9 = new Color(40,128,40);
        Color color10 = new Color(144,190,144);


        GradientPaint gp = new GradientPaint(0, 0, color9, 0, h, color10);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
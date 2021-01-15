package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LogoPanel extends JPanel {
    /*Reference 1 - taken from https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel*/
    private BufferedImage image;

    public LogoPanel(String path) {
        try {
            image = ImageIO.read(new File(path)); //path to logo
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //The getWidth and getHeight functions make this scalable to any monitor size
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); //drawing logo
        /* end of reference 1*/
    }
}

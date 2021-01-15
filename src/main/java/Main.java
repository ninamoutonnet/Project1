import main_screen.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Main {


    static GraphicsConfiguration gc;

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame(gc);
        frame.setSize(1000, 1000);
        frame.setTitle("Over the counter drug management");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        UI dUIc = new UI();
        frame.add(dUIc.getMainPanel());
        frame.setVisible(true);

    }

    public void MakeGetRequest() {
        try {
            URL myURL = new URL("http://imperial.ac.uk");
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myURL.openStream()));

            String inputLine;
            // Read the body of the response
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Something is wrong with URL");
        }
    }

}

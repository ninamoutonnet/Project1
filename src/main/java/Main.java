import main_screen.UI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

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
}

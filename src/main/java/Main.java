import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    static GraphicsConfiguration gc;

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame(gc);
        frame.setSize(1000, 1000);
        frame.setTitle("Over the counter drug management");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        UI dUIc = new UI();
        frame.add(dUIc.getMainPanel());

        /*TestSetup stp = new TestSetup();
        stp.Show();
        stp.PopulatePanel(); */

        frame.setVisible(true);

        /*String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        try {
        // Registers the driver Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }

        Connection conn= DriverManager.getConnection(dbUrl, "postgres", "jivajiva2");

        try {
            Statement s=conn.createStatement();
            String sqlStr = "INSERT INTO clients (familyname,givenname,cardnumber,ccv,expdate) values ('moutonnet','nina','1234','222','01/04/24');";
            s.execute(sqlStr);

            s.close();
            conn.close();
        }
        catch (Exception e) {
        }


    }

    /*
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
    }*/
}

}

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JOptionPaneAddClient {
    public JOptionPaneAddClient() throws SQLException {
        JTextField famname = new JTextField(10);
        JTextField givname = new JTextField(10);
        JTextField cardNb = new JTextField(13);
        JTextField CCV = new JTextField(3);
        JTextField expDate = new JTextField(6);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(new JLabel("Family name:"));
        myPanel.add(famname);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Given name:"));
        myPanel.add(givname);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Card Number:"));
        myPanel.add(cardNb);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("CCV:"));
        myPanel.add(CCV);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Exp date:"));
        myPanel.add(expDate);

        // dialog box - for now no icon (Plain message)
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please enter the client's details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // Get the inputs from the person using the app --> information for one client
            String FN = famname.getText();
            String GN = givname.getText();
            String CN = cardNb.getText();
            String Ccv = CCV.getText();
            String ED = expDate.getText();

            // Include this new client in the DB on Heroku (see ProjectServlet for more details)
            makePostRequest(FN, GN, CN, Ccv, ED); // make sure that the client is not added if he/she is already in the DB - need to find a solution for that

            /*
            //COMMUNICATION WITH THE DB ON THE COMPUTER
            String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
            try {
                // Registers the driver Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
            }

            Connection conn= DriverManager.getConnection(dbUrl, "postgres", "jivajiva2");

            try {
                Statement s=conn.createStatement();
                String FN = famname.getText();
                String GN = givname.getText();
                String CN = cardNb.getText();
                String Ccv = CCV.getText();
                String ED = expDate.getText();

                String sqlStr = "INSERT INTO clients (familyname,givenname,cardnumber,ccv,expdate) values( '"+famname.getText()+"','"+givname.getText()+"','"+cardNb.getText()+"','"+CCV.getText()+"','"+expDate.getText()+"');";
                s.execute(sqlStr);

                s.close();
                conn.close();
            }
            catch (Exception e){
                System.out.println("Problem with inserting SQL into the db");
            }

            System.out.println("Family name: " + famname.getText());
            System.out.println("Given name: " + givname.getText());
            System.out.println("Card Number: " + cardNb.getText());
            System.out.println("CCV: " + CCV.getText());
            System.out.println("Exp date: " + expDate.getText());
        } */

        }
    }

    // Method making a POST request to the servlet to add a new client to the DB - check that POST is the correct method
    public static void makePostRequest(String FN, String GV, String CN, String Ccv, String ED) {
        try {
            // This is the SQL query included in the body of the POST request = instructions
            String message = "INSERT INTO clients (lastname,firstname,cardnumber,ccv,expdate) values( '"+FN+"','"+GV+"','"+CN+"','"+Ccv+"','"+ED+"');";
            byte[] body = message.getBytes(StandardCharsets.UTF_8);

            // The URL maps to the servlet (check that this is the correct way to say it)
            URL myURL = new URL("https://projectservlet.herokuapp.com/DBaccess");
            HttpURLConnection conn = null;

            conn = (HttpURLConnection) myURL.openConnection();
            // Set up the header
            conn.setRequestMethod("POST"); // sets the HTTP method
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);

            // Write the body of the request
            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body, 0, body.length);
            }

            // BufferedReader is a Java class to read the text from an I stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            // Read the body of the response
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
            }
            bufferedReader.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong");
        }
    }
}


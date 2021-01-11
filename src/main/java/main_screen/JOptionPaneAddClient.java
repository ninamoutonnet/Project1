package main_screen;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JOptionPaneAddClient {

    //check if a string is only digits
    public static boolean onlyDigits(String str) {
        // Traverse the string from

        int n =str.length();
        for (int i = 0; i < n; i++) {
           //if each char is between 0 and 9 return true
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                System.out.println("char is : " + str.charAt(i));
            }
            else {
                return false;
            }
        }
        return true;
    }

    public String encryption (String psw)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(psw.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return (generatedPassword);
    }

    public JOptionPaneAddClient() throws SQLException {
        JTextField famname = new JTextField(20);
        JTextField givname = new JTextField(20);
        JTextField cardNb = new JTextField(16);
        JTextField CCV = new JTextField(3);
        JTextField expDate = new JTextField(8);
        JTextField password = new JTextField(8);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(6, 2));
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
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Password:"));
        myPanel.add(password);

        // dialog box - for now no icon (Plain message)
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please enter the client's details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        //check if the text fields are properly populated in order to not push anything to the db that has incorrect data
        boolean familyName = famname.getText().isEmpty();
        boolean givenName = givname.getText().isEmpty();
        boolean passW = password.getText().isEmpty();
        boolean cardNumber = cardNb.getText().length()==16 && onlyDigits(cardNb.getText()); //also check its only numbers
        System.out.println("result is : " + onlyDigits(cardNb.getText()));
        boolean ccvNumber = CCV.getText().length()==3 && onlyDigits(CCV.getText());
        //check the date is in the proper format
        boolean expirationDate = expDate.getText().length()==8 && (expDate.getText().charAt(2)=='/') && (expDate.getText().charAt(5)=='/') ;

        if(result==JOptionPane.OK_OPTION && (passW==true || familyName==true || givenName==true || cardNumber==false || ccvNumber==false || expirationDate==false)){
            JPanel error = new JPanel();
            error.setVisible(true);
            JLabel errorMsg = new JLabel("Error, please enter valid values for the fields, try again");
            error.add(errorMsg);
            // dialog box - for now no icon (Plain message)
            int result2 = JOptionPane.showConfirmDialog(null, error,
                    "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

        }

        //only adds to the db if all the fields are the proper length and not empty.
        if (result == JOptionPane.OK_OPTION && !(familyName) && !(passW) && !(givenName) && (cardNumber) && (ccvNumber) && (expirationDate) ) {
            // Get the inputs from the person using the app --> information for one client
            String FN = famname.getText();
            String GN = givname.getText();
            String CN = cardNb.getText();
            String Ccv = CCV.getText();
            String ED = expDate.getText();
            String PW = password.getText();

            String PW_encrypted = encryption(PW);

            // Include this new client in the DB on Heroku (see ProjectServlet for more details)
            makePostRequest(FN, GN, CN, Ccv, ED, PW_encrypted); // make sure that the client is not added if he/she is already in the DB - need to find a solution for that
        }
    }

    // Method making a POST request to the servlet to add a new client to the DB - check that POST is the correct method (could be PUT)
    public static void makePostRequest(String FN, String GV, String CN, String Ccv, String ED, String PW) {
        try {
            // This is the SQL query included in the body of the POST request = instructions
            String message = "INSERT INTO clients (lastname,firstname,cardnumber,ccv,expdate,passwrd) values( '"+FN+"','"+GV+"','"+CN+"','"+Ccv+"','"+ED+"','"+PW+"');";
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


package main_screen;

import GUI.dummyFrame;
import Requests.Post;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/* This class creates the "Add Client" feature of the UI */

public class JOptionPaneAddClient {

    //check if a string is only digits
    public static boolean onlyDigits(String str) {
        // Traverse the string from

        int n = str.length();
        for (int i = 0; i < n; i++) {
            //if each char is between 0 and 9 return true
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                System.out.println("char is : " + str.charAt(i));
            } else {
                return false;
            }
        }
        return true;
    }

    public String encryption(String psw) {
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
            for (int i = 0; i < bytes.length; i++) {
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
        myPanel.add(new JLabel("Exp date (dd/mm/yy) :"));
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
        boolean cardNumber = cardNb.getText().length() == 16 && onlyDigits(cardNb.getText()); //also check its only numbers
        System.out.println("result is : " + onlyDigits(cardNb.getText()));
        boolean ccvNumber = CCV.getText().length() == 3 && onlyDigits(CCV.getText());
        //check the date is in the proper format
        boolean expirationDate = expDate.getText().length() == 8 && (expDate.getText().charAt(2) == '/') && (expDate.getText().charAt(5) == '/');

        if (result == JOptionPane.OK_OPTION && (passW == true || familyName == true || givenName == true || cardNumber == false || ccvNumber == false || expirationDate == false)) {
            JPanel error = new JPanel();
            error.setVisible(true);
            JLabel errorMsg = new JLabel("Error, please enter valid values for the fields, try again");
            error.add(errorMsg);
            // dialog box - for now no icon (Plain message)
            int result2 = JOptionPane.showConfirmDialog(null, error,
                    "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

        }

        //only adds to the db if all the fields are the proper length and not empty.
        if (result == JOptionPane.OK_OPTION && !(familyName) && !(passW) && !(givenName) && (cardNumber) && (ccvNumber) && (expirationDate)) {
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

            //dummy JFrame to have a pop up message to inform the pharmacist that the client was added
            dummyFrame df2 = new dummyFrame();
            JFrame frmOpt2 = df2.dummyFrameConstruction();

            JPanel success = new JPanel();
            success.setVisible(true);
            JLabel errorMsg = new JLabel("The client has been successfully added to the database");
            success.add(errorMsg);
            int result3 = JOptionPane.showConfirmDialog(frmOpt2, success,
                    "Success", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result3==0) frmOpt2.dispose();

        }
    }

    // Method making a POST request to the servlet to add a new client to the DB - check that POST is the correct method (could be PUT)
    public static void makePostRequest(String FN, String GV, String CN, String Ccv, String ED, String PW) {
            // This is the SQL query included in the body of the POST request = instructions
            String query = "INSERT INTO clients (lastname,firstname,cardnumber,ccv,expdate,passwrd) values( '" + FN + "','" + GV + "','" + CN + "','" + Ccv + "','" + ED + "','" + PW + "');";
            new Post(query);
    }
}

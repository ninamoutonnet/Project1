import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class UpdateStock {

    public String GetBrandFromDB() { //returns a String[]
        String ret = null;
        try {
            // This is the SQL query included in the body of the POST request = instructions
            String message = "SELECT brand FROM products";
            byte[] body = message.getBytes(StandardCharsets.UTF_8);

            // The URL maps to the servlet (check that this is the correct way to say it)
            URL myURL = new URL("https://projectservlet.herokuapp.com/DBaccess");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            // Set up the header
            conn.setRequestMethod("GET"); // sets the HTTP method
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
            // Write the body of the request
            //try (InputStream is = conn.getInputStream()) {
              //  is.read(body, 0, body.length);
           // }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                ret = ret + inputLine;
            }
            in.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong");
        }
        return ret;
    }


    //constructor
    public  UpdateStock() {

        JPanel myPanel = new JPanel();
        myPanel.setLocation(100,100);
        myPanel.setLayout(new GridLayout(8, 2));

        myPanel.add(new JLabel("Medicine Name:"));
        String[] choices = {"Name of medicine"}; //GET FROM DB


        String brandNames = GetBrandFromDB();
        System.out.println(brandNames);

        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model = new SortedComboBoxModel<String>(choices);
        JComboBox<String> comboBox = new JComboBox<String>( model );
        comboBox.setVisible(true);
        myPanel.add(comboBox);


        myPanel.add(new JLabel("Amount per box/bottle:"));
        String[] choices2 = {"150 ml", "15 caps"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model2 = new SortedComboBoxModel<String>(choices2);
        JComboBox<String> comboBox2 = new JComboBox<String>( model2 );
        comboBox2.setVisible(true);
        myPanel.add(comboBox2);


        myPanel.add(new JLabel("Quantity to add or remove:"));
       //can take in negative numbers
        JTextField qty = new JTextField(13);
        myPanel.add(qty);


        myPanel.add(new JLabel("Sale price :"));
        String[] choices3 = {"3.6", "1.5"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model3 = new SortedComboBoxModel<String>(choices3);
        JComboBox<String> comboBox3 = new JComboBox<String>( model3 );
        comboBox3.setVisible(true);
        myPanel.add(comboBox3);


        myPanel.add(new JLabel("Purchase price:"));
        String[] choices4 = {"2.6", "0.5"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model4 = new SortedComboBoxModel<String>(choices4);
        JComboBox<String> comboBox4 = new JComboBox<String>( model4 );
        comboBox4.setVisible(true);
        myPanel.add(comboBox4);


        myPanel.add(new JLabel("Description:"));
        String[] choices5 = {"lemon", "blackcurrant"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model5 = new SortedComboBoxModel<String>(choices5);
        JComboBox<String> comboBox5 = new JComboBox<String>( model5 );
        comboBox5.setVisible(true);
        myPanel.add(comboBox5);


        myPanel.add(new JLabel("Category:"));
        String[] choices6 = {"Digestion"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model6 = new SortedComboBoxModel<String>(choices6);
        JComboBox<String> comboBox6 = new JComboBox<String>( model6 );
        comboBox6.setVisible(true);
        myPanel.add(comboBox6);





        // no icon for now
        int result2 = JOptionPane.showConfirmDialog(null, myPanel,
                "Update Stock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    }
}


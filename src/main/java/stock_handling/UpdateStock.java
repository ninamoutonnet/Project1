package stock_handling;

import db_handling.GetDB;
import GUI.SortedComboBoxModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateStock {
    GetDB info = new GetDB();

    /* public String GetBrandFromDB() { //returns a String[]
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
                ret = ret + " "  + inputLine;
            }
            in.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong");
        }
        return ret;
    } */

    //constructor
    public  UpdateStock() {

        JPanel myPanel = new JPanel();
        myPanel.setLocation(100,100);
        myPanel.setLayout(new GridLayout(8, 2));

        // MED NAME
        myPanel.add(new JLabel("Medicine Name:"));
        // need to find better way to do this (arraylist not compatible with la suite)
        ArrayList<String> str1 = info.getBrand(); //GET FROM DB
        int size = str1.size();
        String[] choices = new String[size];
        for (int i = 0; i < size; i++){
             choices[i] = str1.get(i);
        }
        // String brandNames = GetBrandFromDB();
        // System.out.println(brandNames);
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model = new SortedComboBoxModel<String>(choices);
        JComboBox<String> comboBox = new JComboBox<String>( model );
        comboBox.setVisible(true);
        myPanel.add(comboBox);


        myPanel.add(new JLabel("Amount per box/bottle:"));
        ArrayList<String> str2 = info.getAmount(); //GET FROM DB
        String[] choices2 = new String[size];
        for (int i = 0; i < size; i++){
            choices2[i] = str2.get(i);
        }
        //String[] choices2 = {"150 ml", "15 caps"}; //GET FROM DB
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
        ArrayList<String> str3 = info.getSPrice(); //GET FROM DB
        String[] choices3 = new String[size];
        for (int i = 0; i < size; i++){
            choices3[i] = str3.get(i);
        }
        //String[] choices3 = {"3.6", "1.5"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model3 = new SortedComboBoxModel<String>(choices3);
        JComboBox<String> comboBox3 = new JComboBox<String>( model3 );
        comboBox3.setVisible(true);
        myPanel.add(comboBox3);


        myPanel.add(new JLabel("Purchase price:"));
        ArrayList<String> str4 = info.getPPrice(); //GET FROM DB
        String[] choices4 = new String[size];
        for (int i = 0; i < size; i++){
            choices4[i] = str4.get(i);
        }
        //String[] choices4 = {"2.6", "0.5"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model4 = new SortedComboBoxModel<String>(choices4);
        JComboBox<String> comboBox4 = new JComboBox<String>( model4 );
        comboBox4.setVisible(true);
        myPanel.add(comboBox4);


        myPanel.add(new JLabel("Description:"));
        ArrayList<String> str5 = info.getDescription(); //GET FROM DB
        String[] choices5 = new String[size];
        for (int i = 0; i < size; i++){
            choices5[i] = str5.get(i);
        }
        //String[] choices5 = {"lemon", "blackcurrant"}; //GET FROM DB
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model5 = new SortedComboBoxModel<String>(choices5);
        JComboBox<String> comboBox5 = new JComboBox<String>( model5 );
        comboBox5.setVisible(true);
        myPanel.add(comboBox5);


        myPanel.add(new JLabel("Category:"));
        ArrayList<String> str6 = info.getCategory(); //GET FROM DB
        String[] choices6 = new String[size];
        for (int i = 0; i < size; i++){
            choices6[i] = str6.get(i);
        }
        //String[] choices6 = {"Digestion"}; //GET FROM DB
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


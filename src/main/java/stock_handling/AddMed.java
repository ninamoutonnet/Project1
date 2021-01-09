package stock_handling;

import GUI.SortedComboBoxModel;
import db_handling.GetDB_medicine;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class AddMed {
    GetDB_medicine info = new GetDB_medicine();

    public AddMed() {
        JFrame fr = new JFrame();  //dummy JFrame to ensure that the window popping up is on top of anything else
        fr.setVisible(false);
        fr.setLocation(100,100);
        fr.setAlwaysOnTop(true);

        JTextField amount = new JTextField(13);
        JTextField name = new JTextField(13);
        JTextField qty = new JTextField(13);
        JTextField sprice = new JTextField(3);
        JTextField pprice = new JTextField(6);
        JTextField fullstock = new JTextField(6);
        String LIM = " ";
        String CAT = " ";
        String DES = " ";

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(10, 2));
        myPanel.add(new JLabel("Medicine name/brand:"));
        myPanel.add(name);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Medicine amount per box/bottle:"));
        myPanel.add(amount);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Current stock quantity:"));
        myPanel.add(qty);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Sale price:"));
        myPanel.add(sprice);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Purchase price:"));
        myPanel.add(pprice);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Full stock quantity:"));
        myPanel.add(fullstock);

        // Need to extract a string from this
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Is it a limited medication? "));
        String[] choices = {"Yes", "No"};
        final JComboBox<String> limited = new JComboBox<String>(choices);
        limited.setVisible(true);
        myPanel.add(limited);


        //CATEGORY
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Category "));
        ArrayList<String> str1 = info.getCategory(); //GET FROM DB
        String[] choicesCategory = new String[str1.size()];
        for (int i = 0; i < str1.size(); i++){
            choicesCategory[i] = str1.get(i);
        }
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> modelCategory = new SortedComboBoxModel<String>(choicesCategory);
        JComboBox<String> comboBoxCategory = new JComboBox<String>( modelCategory );
        comboBoxCategory.setVisible(true);
        myPanel.add(comboBoxCategory);


        //DESCRIPTION
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Description:"));
        ArrayList<String> str = info.getDescription(); //GET FROM DB
        String[] choicesDescription = new String[str.size()];
        for (int i = 0; i < str.size(); i++){
            choicesDescription[i] = str.get(i);
        }
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model = new SortedComboBoxModel<String>(choicesDescription);
        JComboBox<String> comboBox = new JComboBox<String>( model );
        comboBox.setVisible(true);
        myPanel.add(comboBox);


        int result = JOptionPane.showConfirmDialog(fr, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //get the values of combobox once OK is pressed
            LIM = limited.getSelectedItem().toString(); // this does not work --> always returns 'Yes' even though 'No' is selected
            CAT = comboBoxCategory.getSelectedItem().toString(); // this does not work --> always returns 'Allergy' even though another one is chosen
            DES = comboBox.getSelectedItem().toString();

            // Get the inputs from the person using the app --> information for one client
            String A = amount.getText();
           // String CS = qty.getText();
            Integer CS=0;
            try{
                CS = Integer.parseInt(qty.getText());
            }catch (Exception e){
                System.out.println("Not an current stock integer ");
            }

           // String SP = sprice.getText();
            Double SP =0.0;
            try{
                SP = Double.parseDouble(sprice.getText());
            }catch (Exception e){
                System.out.println("Not a double sales price");
            }
            //String PP = pprice.getText();
            Double PP=0.0;
            try{
                PP = Double.parseDouble(pprice.getText());
            }catch (Exception e){
                System.out.println("Not a double pruchase price");
            }
            //String FS = fullstock.getText();
            Integer FS=0;
            try{
                FS = Integer.parseInt(fullstock.getText());
            }catch (Exception e){
                System.out.println("Not an integer fullstock ");
            }

            String N = name.getText();

            boolean LIM2=true;
            if(LIM.equalsIgnoreCase("yes")){
                LIM2 = true;
            }else if(LIM.equalsIgnoreCase("no")){
                LIM2=false;
            }
            /*System.out.println(A);
            System.out.println(LIM);
            System.out.println(CAT);
            System.out.println(DES);
            System.out.println(CS);
            System.out.println(SP);
            System.out.println(PP);
            System.out.println(FS);
            System.out.println(N);*/



            // Include this new product in the DB on Heroku (see ProjectServlet for more details)
            makePostRequest(N, A, SP, PP, FS, LIM2, DES, CAT, CS); //check that it does not already exist

            fr.dispose(); //discard of the dummy jframe

            /*
           if(comboBox.getSelectedItem()=="OTHER") { //add a new element to the description

                JFrame addDescription = new JFrame("Add a new description");
                addDescription.setVisible(true);
                addDescription.setLocation(100,100);
                addDescription.setAlwaysOnTop(true);
                JPanel myPanel2 = new JPanel();
                //addDescription.add(myPanel2);
                myPanel2.add(new JLabel("Description: "));
                JTextField newDescription = new JTextField(6);
                myPanel2.add(newDescription);
                int result2 = JOptionPane.showConfirmDialog(addDescription, myPanel2,
                        "Please Enter the description ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                //here add the new description to the DB!!!

                //HERE WE ADD THE NEW DESCRIPTION TO THE DB!!!
                if(result2 ==JOptionPane.OK_OPTION){
                    addDescription.dispose();
                }
            }
             */
        }
        else {
            fr.dispose(); //discard of the dummy jframe
        }
    }


    // Method making a POST request to the servlet to add a new client to the DB - check that POST is the correct method (could be PUT)
    public static void makePostRequest(String N, String A, Double SP, Double PP, Integer FS, Boolean LIM, String DES, String CAT, Integer CS) {
        try {
            // This is the SQL query included in the body of the POST request = instructions
            String message = "INSERT INTO products (brand,amount,\"sprice \",pprice,\"fullstock \",\"limitation \",\"description \",\"category \",currentstock) values( '"+N+"','"+A+"',"+SP+","+PP+","+FS+",'"+LIM+"','"+DES+"','"+CAT+"',"+CS+");";
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


package stock_handling;

import GUI.SortedComboBoxModel;
import db_handling.GetDB_medicine;
import GUI.dummyFrame;
import db_handling.checkForMed;

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
        JFrame fr = new JFrame(); //dummy JFrame to ensure that the window popping up is on top of anything else
        fr.setVisible(false);
        fr.setLocation(100,100);
        fr.setAlwaysOnTop(true);

        JTextField amount = new JTextField(13);
        JTextField name = new JTextField(13);
        JTextField qty = new JTextField(13);
        JTextField sprice = new JTextField(3);
        JTextField pprice = new JTextField(6);
        JTextField fullstock = new JTextField(6);
        JTextField description = new JTextField(13);
        String LIM = "";
        String CAT = "";
        String DES = "";

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(10, 2));
        myPanel.add(new JLabel("Medicine brand:"));
        myPanel.add(name);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Medicine name/description:"));
        myPanel.add(description);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Medicine amount per box/bottle:"));
        myPanel.add(amount);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Current stock quantity:"));
        myPanel.add(qty);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Full stock quantity:"));
        myPanel.add(fullstock);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Sale price:"));
        myPanel.add(sprice);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Purchase price:"));
        myPanel.add(pprice);

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
        // Should we offer the choice to detail new category?
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> modelCategory = new SortedComboBoxModel<String>(choicesCategory);
        JComboBox<String> comboBoxCategory = new JComboBox<String>( modelCategory );
        comboBoxCategory.setVisible(true);
        myPanel.add(comboBoxCategory);
        // + need to eliminate all the strings that are the same

        int result = JOptionPane.showConfirmDialog(fr, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //get the values of combobox once OK is pressed
            LIM = limited.getSelectedItem().toString();
            CAT = comboBoxCategory.getSelectedItem().toString();
            // Get the inputs from the person using the app --> information for one client
            String A = amount.getText();
            DES = description.getText();
            String N = name.getText();

            boolean okToPost = true; //boolean that allows to ensure that all the values are valid before sending them to the db

            //check that current stock is an int, if not give an error
            Integer CS=0;
            try{
                CS = Integer.parseInt(qty.getText());
            }catch (Exception e){
                //System.out.println("Not an current stock integer ");
                okToPost = false; //set to false to signal an issue

                //construct a dummy frame using the class! since it is a redundant operation
                dummyFrame df = new dummyFrame();
                JFrame dummyF = df.dummyFrameConstruction();

                JPanel error = new JPanel();
                error.setVisible(true);
                JLabel errorMsg = new JLabel("Error, current stock is not an integer ");
                error.add(errorMsg);
                int error1 = JOptionPane.showConfirmDialog(dummyF, error,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(error1==0){//only enter the loop when ok pressed
                    df.dummyFrameDispose(dummyF);
                }

            }

            //check that sales price  is a double, if not give an error
            Double SP =0.0;
            try{
                SP = Double.parseDouble(sprice.getText());
            }catch (Exception e){
                //System.out.println("Not a double sales price");
                okToPost = false; //set to false to signal an issue

                //construct a dummy frame using the class! since it is a redundant operation
                dummyFrame df = new dummyFrame();
                JFrame dummyF = df.dummyFrameConstruction();

                JPanel error = new JPanel();
                error.setVisible(true);
                JLabel errorMsg = new JLabel("Error, sales price is not a double ");
                error.add(errorMsg);
                int error2 = JOptionPane.showConfirmDialog(dummyF, error,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(error2==0){//only enter the loop when ok pressed
                   df.dummyFrameDispose(dummyF);
                }

            }

            //check that  purchase price is a double, if not give an error
            Double PP=0.0;
            try{
                PP = Double.parseDouble(pprice.getText());
            }catch (Exception e){
                //System.out.println("Not a double purchase price");
                okToPost = false; //set to false to signal an issue

                //construct a dummy frame using the class! since it is a redundant operation
                dummyFrame df = new dummyFrame();
                JFrame dummyF = df.dummyFrameConstruction();

                JPanel error = new JPanel();
                error.setVisible(true);
                JLabel errorMsg = new JLabel("Error, purchase price is not a double ");
                error.add(errorMsg);
                int error3 = JOptionPane.showConfirmDialog(dummyF, error,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(error3==0){//only enter the loop when ok pressed
                    df.dummyFrameDispose(dummyF);
                }

            }

            //check that full stock is an int, if not give an error
            Integer FS=0;
            try{
                FS = Integer.parseInt(fullstock.getText());
            }catch (Exception e){
                //System.out.println("Not an integer fullstock");
                okToPost = false; //set to false to signal an issue

                //construct a dummy frame using the class! since it is a redundant operation
                dummyFrame df = new dummyFrame();
                JFrame dummyF = df.dummyFrameConstruction();

                JPanel error = new JPanel();
                error.setVisible(true);
                JLabel errorMsg = new JLabel("Error, full stock is not an integer ");
                error.add(errorMsg);
                int error4 = JOptionPane.showConfirmDialog(dummyF, error,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(error4==0){//only enter the loop when ok pressed
                    df.dummyFrameDispose(dummyF);
                }

            }


            boolean LIM2=true;
            if(LIM.equalsIgnoreCase("yes")){
                LIM2 = true;
            }else if(LIM.equalsIgnoreCase("no")){
                LIM2=false;
            }


            if(okToPost) {//if there was an issue in the format of the input, it will not enter the loop
                // Include this new product in the DB on Heroku (see ProjectServlet for more details)

                db_handling.checkForMed CFM = new checkForMed();
                boolean notAlreadyIn =  CFM.isTheMedicineIn(N, A, sprice.getText(),pprice.getText(),DES,CAT);
                if(!notAlreadyIn) {
                    System.out.println("It is already in the db");
                }else{
                    makePostRequest(N, A, SP, PP, FS, LIM2, DES, CAT, CS); //check that it does not already exist
                    fr.dispose(); //discard of the dummy jframe
                }

            }

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


package stock_handling;

import GUI.SortedComboBoxModel;
import db_handling.GetDB_medicine;
import GUI.dummyFrame;
import db_handling.checkForMed;
import GUI.checkTheInput;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Vector;


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

        // LIMITATION
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
        //sort the list
        str1.sort(String::compareToIgnoreCase);
        //remove duplicates from the list
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        set.addAll(str1);
        str1.clear();
        str1.addAll(set);
        //create the combo box
        String[] array = str1.toArray(new String[str1.size()]);
        JComboBox<String> comboBoxCategory = new JComboBox<>(array);
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
            checkTheInput check = new checkTheInput();//use the object created for checking
            boolean ok = false;
            Integer CS=0;
            ok = check.isAnInt(qty.getText(), "Current Stock");
            if(!ok) okToPost = false;
            else CS = Integer.parseInt(qty.getText());

            //check that sales price  is a double, if not give an error
            boolean ok1 = false;
            Double SP=0.0;
            ok1 = check.isADouble(sprice.getText(), "Sales Price");
            if(!ok1) okToPost = false;
            else SP = Double.parseDouble(sprice.getText());

            //check that  purchase price is a double, if not give an error
            boolean ok2 = false;
            Double PP=0.0;
            ok2 = check.isADouble(pprice.getText(), "Purchase Price");
            if(!ok2) okToPost = false;
            else SP = Double.parseDouble(pprice.getText());

            //check that full stock is an int, if not give an error
            boolean ok3 = false;
            Integer FS=0;
            ok3 = check.isAnInt(fullstock.getText(), "Full Stock");
            if(!ok3) okToPost = false;
            else FS = Integer.parseInt(fullstock.getText());

            boolean LIM2=true;
            if(LIM.equalsIgnoreCase("yes")){
                LIM2 = true;
            }else if(LIM.equalsIgnoreCase("no")){
                LIM2=false;
            }


            if(okToPost) {//if there was an issue in the format of the input, it will not enter the loop
                // Include this new product in the DB on Heroku (see ProjectServlet for more details) provided it is not already in

                db_handling.checkForMed CFM = new checkForMed();
                Vector<Integer> id;
                boolean notAlreadyIn = false;
                id =  CFM.isTheMedicineIn(N, A, sprice.getText(),pprice.getText(),DES,CAT);
                if (id.size()==0) {
                    notAlreadyIn = true;
                }
                if(!notAlreadyIn) {
                    //construct a dummy frame using the class! since it is a redundant operation
                    dummyFrame df = new dummyFrame();
                    JFrame frmOpt = df.dummyFrameConstruction();

                    JPanel error = new JPanel();
                    error.setVisible(true);
                    JLabel errorMsg = new JLabel("Error, this medicine is already in the database, you can try to update it instead");
                    error.add(errorMsg);
                    int result3 = JOptionPane.showConfirmDialog(frmOpt, error,
                            "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result3==0) frmOpt.dispose(); //when ok or the cross is pressed, discard the frame

                }else{

                    makePostRequest(N, A, SP, PP, FS, LIM2, DES, CAT, CS); //check that it does not already exist

                    //construct a dummy frame using the class! since it is a redundant operation
                    dummyFrame df = new dummyFrame();
                    JFrame frmOpt = df.dummyFrameConstruction();

                    JPanel success = new JPanel();
                    success.setVisible(true);
                    JLabel successMSG = new JLabel("The new medicine has been added to the database");
                    success.add(successMSG);
                    int result3 = JOptionPane.showConfirmDialog(frmOpt, success,
                            "Success", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if(result3==0) frmOpt.dispose(); //when ok or the cross is pressed, discard the frame
                    fr.dispose(); //discard of the dummy jframe
                }

            }

        }
        else {
            fr.dispose(); //discard of the dummy jframe
        }
    }

    // Method making a POST request to the servlet to add a new medicine to the DB - check that POST is the correct method (could be PUT)
    public static void makePostRequest(String N, String A, Double SP, Double PP, Integer FS, Boolean LIM, String DES, String CAT, Integer CS) {
        try {
            // This is the SQL query included in the body of the POST request = instructions
            String message = "INSERT INTO products (brand,amount,\"sprice \",pprice,\"fullstock \",\"limitation \",\"description \",\"category \",currentstock) values( '"+N+"','"+A+"',"+SP+","+PP+","+FS+",'"+LIM+"','"+DES+"','"+CAT+"',"+CS+");";
            byte[] body = message.getBytes(StandardCharsets.UTF_8);

            // The URL maps to the servlet (check that this is the correct way to say it)
            URL myURL = new URL("https://projectservlet.herokuapp.com/access");
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


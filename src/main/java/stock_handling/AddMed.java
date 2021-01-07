package stock_handling;

import GUI.SortedComboBoxModel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class AddMed {

    public AddMed() {
        JFrame fr = new JFrame();  //dummy JFrame to ensure that the window popping up is on top of anything else
        fr.setVisible(false);
        fr.setLocation(100,100);
        fr.setAlwaysOnTop(true);

        JTextField amount = new JTextField(13);
        JTextField name = new JTextField(13);
        JTextField brand = new JTextField(13);
        JTextField qty = new JTextField(13);
        JTextField sprice = new JTextField(3);
        JTextField pprice = new JTextField(6);
        JTextField fullstock = new JTextField(6);
        String limitation;
        String CAT;

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(9, 2));
        myPanel.add(new JLabel("Medicine name/description:"));
        myPanel.add(name);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Medicine brand:"));
        myPanel.add(brand);
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

        limitation = limited.getSelectedItem().toString(); // this does not work --> always returns 'Yes' even though 'No' is selected
        System.out.println(limitation);

        /* No need for that as name is the same as description
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Description "));

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        // WE SHOULD BE ABLE TO SELECT THE CHOICE 2 FROM THE DB ONLINE!

        //
        String[] choices2 = {"Relief","OTHER","Tabs","Antiseptic","Hand Sanitizer","Plasters","Liquid","First Defense","Night Nurse","Max","Standard","Day and Night","Mucus relief","4 flu","Vaporub","Moisturising cream","Exfoliating cleanser","Skin cream","Skin relief cream","Face Scrub","Psiorasis cream","Repair and Restore","Eczema cream","Meltlets","Express","Max Strengh","Headache","Extra","Triple action","Original","Soluble","Blackcurrant","Lemon","Chewable","Advance"};
        //this class of combo box makes everything sorted alphabetically.
        GUI.SortedComboBoxModel<String> model = new GUI.SortedComboBoxModel<String>(choices2);
        JComboBox<String> comboBox = new JComboBox<String>( model );
        comboBox.setVisible(true);
        myPanel.add(comboBox);
         */

        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Category "));

        /*

        HERE WE WILL TAKE THE DB FROM HEROKU, USE ALL THE DESCRIPTION TO PUT THEM IN AN ARRAY OF STRING.
        THIS WILL THEN BE FED INTO A SORTING ALGORITHM AND USED FOR THE DROP DOWN MENU
        RIGHT NOW I JUST TYPED THE NAMES MYSELF

         */

        // Need to extract a string from this
        String[] choices3 = {"Allergy","OTHER","First Aid","Cold and flu","Skincare","Headache and pain relief","Digestion"};
        SortedComboBoxModel<String> model2 = new SortedComboBoxModel<String>(choices3);
        JComboBox<String> comboBox2 = new JComboBox<String>(model2);
        comboBox2.setVisible(true);
        myPanel.add(comboBox2);

        CAT = comboBox2.getSelectedItem().toString(); // this does not work --> always returns 'Allergy' even though another one is chosen
        System.out.println(CAT);

        int result = JOptionPane.showConfirmDialog(fr, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // Get the inputs from the person using the app --> information for one client
            String B = brand.getText();
            String A = amount.getText();
            String CS = qty.getText();
            String SP = sprice.getText();
            String PP = pprice.getText();
            String FS = fullstock.getText();
            String N = name.getText();
            String LIM;

            if (limitation == "Yes")
                LIM = "TRUE";
            else
                LIM = "FALSE";

            // Include this new product in the DB on Heroku (see ProjectServlet for more details)
            makePostRequest(B, A, SP, PP, FS, LIM, N, CAT, CS); // make sure that the client is not added if he/she is already in the DB - need to find a solution for that

            fr.dispose(); //discard of the dummy jframe

            // for now -> print text but later use this loop to get data
            /*  System.out.println("Medicine brand: " + brand.getText());
            System.out.println("Medicine name: " + name.getText());
            System.out.println("Quantity: " + name.getText());
            System.out.println("Sale price: " + sprice.getText());
            System.out.println("Purchase price : " + pprice.getText());
            System.out.println("Full stock is : " + fullstock.getText());
            System.out.println("Limitation : " + limited.getSelectedItem());
            System.out.println("Description : " + comboBox.getSelectedItem());
            System.out.println("Category : " + comboBox2.getSelectedItem());*/

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

    public static void makePostRequest(String B, String A, String SP, String PP, String FS, String LIM, String N, String CAT, String CS) {
        try {
            // Convert data types to String

            // This is the SQL query included in the body of the POST request = instructions
            String message = "INSERT INTO products (brand,amount,\"sprice \",pprice,\"fullstock \",\"limitation \",\"description \",\"category \",currentstock) values( '"+B+"','"+A+"',"+SP+","+PP+","+FS+","+LIM+",'"+N+"','"+CAT+"',"+CS+");";
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


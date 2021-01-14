package stock_handling;

import GUI.dummyFrame;
import db_handling.GetDB_medicine;
import db_handling.valueHolder;
import GUI.SortedComboBoxModel;
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
import java.util.LinkedHashSet;
import java.util.Vector;

public class UpdateStock {
    GetDB_medicine info = new GetDB_medicine();

    //constructor
    public  UpdateStock() {
        //create a dummy jframe in order to set the option pane on top
        //construct a dummy frame using the class! since it is a redundant operation
        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        JPanel myPanel = new JPanel();
        myPanel.setLocation(200,100);
        myPanel.setLayout(new GridLayout(8, 2));

        // MED NAME
        myPanel.add(new JLabel("Medicine Name:"));
        ArrayList<String> str1 = info.getBrand(); //GET FROM DB
        int size = str1.size();
        //sort the list
        str1.sort(String::compareToIgnoreCase);
        //remove duplicates from the list
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        set.addAll(str1);
        str1.clear();
        str1.addAll(set);
        //create the combo box
        String[] array = str1.toArray(new String[str1.size()]);
        JComboBox<String> comboBox = new JComboBox<>(array);
        comboBox.setVisible(true);
        myPanel.add(comboBox);

        myPanel.add(new JLabel("Amount per box/bottle:"));
        ArrayList<String> str2 = info.getAmount(); //GET FROM DB
        //sort the list
        str2.sort(String::compareToIgnoreCase);
        //remove duplicates from the list
        LinkedHashSet<String> set2 = new LinkedHashSet<String>();
        set2.addAll(str2);
        str2.clear();
        str2.addAll(set2);
        //create the combo box
        String[] array2 = str2.toArray(new String[str2.size()]);
        JComboBox<String> comboBox2 = new JComboBox<>(array2);
        comboBox2.setVisible(true);
        myPanel.add(comboBox2);

        myPanel.add(new JLabel("Quantity to add or remove:"));
        //can take in negative numbers
        JTextField qty = new JTextField(13);
        myPanel.add(qty);

        myPanel.add(new JLabel("Sale price :"));
        ArrayList<String> str3 = info.getSPrice(); //GET FROM DB
        //remove duplicates from the list
        LinkedHashSet<String> set3 = new LinkedHashSet<String>();
        set3.addAll(str3);
        str3.clear();
        str3.addAll(set3);
        String[] choices3 = str3.toArray(new String[str3.size()]);
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model3 = new SortedComboBoxModel<String>(choices3);
        JComboBox<String> comboBox3 = new JComboBox<String>( model3 );
        comboBox3.setVisible(true);
        myPanel.add(comboBox3);


        myPanel.add(new JLabel("Purchase price:"));
        ArrayList<String> str4 = info.getPPrice(); //GET FROM DB
        //remove duplicates from the list
        LinkedHashSet<String> set4 = new LinkedHashSet<String>();
        set4.addAll(str4);
        str4.clear();
        str4.addAll(set4);
        String[] choices4 = str4.toArray(new String[str4.size()]);
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model4 = new SortedComboBoxModel<String>(choices4);
        JComboBox<String> comboBox4 = new JComboBox<String>( model4 );
        comboBox4.setVisible(true);
        myPanel.add(comboBox4);


        myPanel.add(new JLabel("Description:"));
        ArrayList<String> str5 = info.getDescription(); //GET FROM DB
        //sort the list
        str5.sort(String::compareToIgnoreCase);
        //remove duplicates from the list
        LinkedHashSet<String> set5 = new LinkedHashSet<String>();
        set5.addAll(str5);
        str5.clear();
        str5.addAll(set5);
        //create the combo box
        String[] array5 = str5.toArray(new String[str5.size()]);
        JComboBox<String> comboBox5 = new JComboBox<>(array5);
        comboBox5.setVisible(true);
        myPanel.add(comboBox5);


        myPanel.add(new JLabel("Category:"));
        ArrayList<String> str6 = info.getCategory(); //GET FROM DB
        //sort the list
        str6.sort(String::compareToIgnoreCase);
        //remove duplicates from the list
        LinkedHashSet<String> set6 = new LinkedHashSet<String>();
        set6.addAll(str6);
        str6.clear();
        str6.addAll(set6);
        //create the combo box
        String[] array6 = str6.toArray(new String[str6.size()]);
        JComboBox<String> comboBox6 = new JComboBox<>(array6);
        comboBox6.setVisible(true);
        myPanel.add(comboBox6);

        //Branch name
        myPanel.add(new JLabel("Branch "));
        String[] arrayBranches = {"East End", "Green Park", "Paddington"};
        JComboBox<String> comboBoxBranch = new JComboBox<>(arrayBranches);
        comboBoxBranch.setVisible(true);
        myPanel.add(comboBoxBranch);

        // no icon for now
        int result2 = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Update Stock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


        //check if the quantities make sense when ok is pressed
        if(result2==JOptionPane.OK_OPTION){
            String selectedName = comboBox.getSelectedItem().toString();
            String selectedAmount = comboBox2.getSelectedItem().toString();
            String quantityAddRemove = qty.getText();
            String selectedSalesP = comboBox3.getSelectedItem().toString();
            String selectedPurchP = comboBox4.getSelectedItem().toString();
            String selectedDescription = comboBox5.getSelectedItem().toString();
            String selectedCategory = comboBox6.getSelectedItem().toString();

           // System.out.println(selectedName + " " + selectedAmount + " " + quantityAddRemove + " " + selectedSalesP + " " + selectedPurchP + " " + selectedDescription + " " + selectedCategory);
            boolean validEntry = true; //assume the entries are valid
            checkForMed CFM = new checkForMed();
            //false if the medicine is not in
            Vector<Integer> id;
            id = CFM.isTheMedicineIn(selectedName,selectedAmount,selectedSalesP,selectedPurchP,selectedDescription,selectedCategory);
            if(id.size()==0) validEntry = false; // if no medicine has been found having the same values,
            //// give an error. you cannot update a stock of smt that is not in the db

            //NOW CHOOSE WETHER OR NOT THE INPUT IS VALID AND YOU SHOULD UPDATE THE DB OR NOT
            if(validEntry==false){
                JPanel error = new JPanel();
                error.setVisible(true);
                JLabel errorMsg = new JLabel("Error, this medicine does not exist in the current stock, add it to the stock and try to update it afterwards");
                error.add(errorMsg);
                // dialog box - for now no icon (Plain message)
                int result3 = JOptionPane.showConfirmDialog(null, error,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }

            if(validEntry==true ){

                //now check the input makes sens with respoect to stock available
                ArrayList<String> strAmount = info.getCurrentStock(); //GET FROM DB
                System.out.println("The current stock of this medicine is: "+ strAmount.get(id.get(0)-1));
                //check that the number in the database is superior than the quantity to remove if it is a removal
                boolean finalstep = true;
                int qtyAddRem = 0;
                try{
                    qtyAddRem = Integer.parseInt(quantityAddRemove); //this will give an error if the string has smt else than numbers
                    //this is good as even 4.3 gives an error, and we can only work with ints
                    if( ( qtyAddRem + Integer.parseInt(strAmount.get(id.get(0)-1)) )<0){

                        //if the quantity to add/remove + what is in stock is negative, you have a problem
                        JPanel error = new JPanel();
                        error.setVisible(true);
                        JLabel errorMsg = new JLabel("Error, you wish to remove more medicine than you have in stock");
                        error.add(errorMsg);
                        int result3 = JOptionPane.showConfirmDialog(null, error,
                                "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                        finalstep=false;
                    }
                }catch(Exception e){
                    JPanel error = new JPanel();
                    error.setVisible(true);
                    JLabel errorMsg = new JLabel("Error, you have to enter a valid number for the quantity");
                    error.add(errorMsg);
                    int result3 = JOptionPane.showConfirmDialog(null, error,
                            "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                    finalstep=false;
                }

                if(finalstep) {
                    ArrayList<String> strID = info.getID(); //GET FROM DB
                    Integer id1 = id.get(0)-1;
                    Integer idToSendToDB = Integer.parseInt(strID.get(id1));
                    System.out.println("The id is: " + idToSendToDB);
                    Integer updatedAmount = Integer.parseInt(strAmount.get(id.get(0)-1))+qtyAddRem;
                    UpdateRequest(idToSendToDB,updatedAmount);

                    JPanel success = new JPanel();
                    success.setVisible(true);
                    //System.out.println("Quantity to add/rem is: "+qtyAddRem);
                    JLabel successMsg = new JLabel("Success, needs to be updated: " + id.get(0));
                    success.add(successMsg);
                    // dialog box - for now no icon (Plain message)
                    int result3 = JOptionPane.showConfirmDialog(null, success,
                            "Success", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                }
            }
        }
    }

    public static void UpdateRequest(Integer idNUM, Integer CS) {
        try {
            // This is the SQL query included in the body of the POST request = instructions
            String message = "UPDATE products SET currentstock = "+CS+" where id = "+idNUM+";";
           // "INSERT INTO products (brand,amount,\"sprice \",pprice,\"fullstock \",\"limitation \",\"description \",\"category \",currentstock) values( '"+N+"','"+A+"',"+SP+","+PP+","+FS+",'"+LIM+"','"+DES+"','"+CAT+"',"+CS+");";
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


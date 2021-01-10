package stock_handling;

import GUI.dummyFrame;
import db_handling.GetDB_medicine;
import db_handling.valueHolder;
import GUI.SortedComboBoxModel;
import db_handling.checkForMed;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
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
        String[] choices = new String[size];
        for (int i = 0; i < size; i++){
             choices[i] = str1.get(i);
        }
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
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model6 = new SortedComboBoxModel<String>(choices6);
        JComboBox<String> comboBox6 = new JComboBox<String>( model6 );
        comboBox6.setVisible(true);
        myPanel.add(comboBox6);

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
                    JPanel success = new JPanel();
                    success.setVisible(true);
                    //System.out.println("Quantity to add/rem is: "+qtyAddRem);
                    JLabel successMsg = new JLabel("Success, needs to be updated: " + id.get(0));
                    success.add(successMsg);
                    // dialog box - for now no icon (Plain message)
                    int result3 = JOptionPane.showConfirmDialog(null, success,
                            "Success", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                    //MODIFY THE DB
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    //



                }
            }





        }
    }
}


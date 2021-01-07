package stock_handling;

import db_handling.GetDB_medicine;
import db_handling.valueHolder;
import GUI.SortedComboBoxModel;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class UpdateStock {
    GetDB_medicine info = new GetDB_medicine();

    //constructor
    public  UpdateStock() {

        //create a dummy jframe in order to set the option pane on top
        JFrame frmOpt;
        frmOpt = new JFrame();
        frmOpt.setVisible(false);
        frmOpt.setLocation(100, 100);
        frmOpt.setAlwaysOnTop(true);

        String nameSelected = " ";
        JPanel myPanel = new JPanel();
        myPanel.setLocation(200,100);
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
       //  String brandNames = GetBrandFromDB();
        // System.out.println(brandNames);
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model = new SortedComboBoxModel<String>(choices);
        JComboBox<String> comboBox = new JComboBox<String>( model );
        comboBox.setVisible(true);
        myPanel.add(comboBox);

        //use an action listener to only record the value when the user actively changes it and not get the default value
        //useful in the next step of the code to have the actual name of the medicine selected
       /* valueHolder VH = new valueHolder();

        comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VH.setStr(comboBox.getSelectedItem().toString());
                }
        });

        if(VH.getStrVH()!= null) {
            nameSelected = VH.getStrVH();
            System.out.println("the selected name is: " + VH.getStrVH());

        }*/




        myPanel.add(new JLabel("Amount per box/bottle:"));

        //here you want only the amount corresponding to the medicine name selected above
        //use the name of the selected product to find the corresponding index
        //there might be more than 1 index because some medicine comes in a different format, ie 16 caps or 10 sachets



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

            //get indexes of name
            Vector<Integer> id = new Vector<>();
            for(int i=0; i<str1.size(); i++){
                if( selectedName.equals(str1.get(i)) ) { //use the .equals operator as it compares the content of the string and not if they are the same object.
                    id.add(i+1); //add one because index starts at 0 and id at 1
                }
            }

            //verify those indexes match between brand name and amount
            Vector<Integer> temp = new Vector<>();
            for(int i=0; i<id.size();i++){
                int index = id.get(i);
                if(selectedAmount.equals(str2.get(index-1))) {
                    //refine the index vector with only the medicine that we care about
                    temp.add(index);
                    //System.out.println(index);
                }
            }
            id.clear();
            for(int i=0; i<temp.size();i++){
                id.add(temp.get(i)); //update the id vector
               // System.out.println(id.get(i));
            }
            temp.clear();
            if(id.size()==0) validEntry =false; //if the id is 0, there was no match between brand name and amount and thus this is not valid



            //verify brand and sprice match
            for(int i=0; i<id.size();i++){
                int index = id.get(i);
                if(selectedSalesP.equals(str3.get(index-1))) {
                    //refine the index vector with only the medicine that we care about
                    temp.add(index);
                    //System.out.println(index);
                }
            }
            id.clear();
            for(int i=0; i<temp.size();i++){
                id.add(temp.get(i)); //update the id vector
               // System.out.println(id.get(i));
            }
            temp.clear();
            if(id.size()==0) validEntry = false;




            //verify brand and purchprice match
            for(int i=0; i<id.size();i++){
                int index = id.get(i);
                if(selectedPurchP.equals(str4.get(index-1))) {
                    //refine the index vector with only the medicine that we care about
                    temp.add(index);
                }
            }
            id.clear();
            for(int i=0; i<temp.size();i++){
                id.add(temp.get(i)); //update the id vector
            }
            temp.clear();
            if(id.size()==0) validEntry = false;



            //verify brand and description match
            for(int i=0; i<id.size();i++){
                int index = id.get(i);
                if(selectedDescription.equals(str5.get(index-1))) {
                    //refine the index vector with only the medicine that we care about
                    temp.add(index);
                }
            }
            id.clear();
            for(int i=0; i<temp.size();i++){
                id.add(temp.get(i)); //update the id vector
            }
            temp.clear();
            if(id.size()==0) validEntry = false;


            //verify brand and category match
            for(int i=0; i<id.size();i++){
                int index = id.get(i);
                if(selectedCategory.equals(str6.get(index-1))) {
                    //refine the index vector with only the medicine that we care about
                    temp.add(index);
                }
            }
            id.clear();
            for(int i=0; i<temp.size();i++){
                id.add(temp.get(i)); //update the id vector
            }
            temp.clear();
            if(id.size()==0) validEntry = false;




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

            if(validEntry!=false){
                JPanel success = new JPanel();
                success.setVisible(true);
                JLabel errorMsg = new JLabel("Succes, needs to be updated: "+id.get(0));
                success.add(errorMsg);
                // dialog box - for now no icon (Plain message)
                int result3 = JOptionPane.showConfirmDialog(null, success,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }





        }
    }
}


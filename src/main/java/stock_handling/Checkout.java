package stock_handling;

import GUI.SortedComboBoxModel;
import GUI.dummyFrame;
import Requests.Post;
import db_handling.GetDB_clients;
import db_handling.GetDB_medicine;
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

/* This class implements the checkout feature that the pharmacist uses to register new purchases */

public class Checkout {

    GetDB_medicine info = new GetDB_medicine(1);
    GetDB_medicine info2 = new GetDB_medicine(2);
    GetDB_medicine info3 = new GetDB_medicine(3);
    String Branch = "";

    public Checkout(String branch) {

        Branch = branch;

        //construct a dummy frame using the class! since it is a redundant operation
        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(9, 1));

        //relate the different branches to the different databases tables
        Integer branchNB = 0;
        if (Branch.equalsIgnoreCase("Paddington")) branchNB = 2;
        if (Branch.equalsIgnoreCase("Green Park")) branchNB = 1;
        if (Branch.equalsIgnoreCase("East End")) branchNB = 3;

        //sizes of the different DB from different branches
        int size1 = info.getBrand().size();
        int size2 = info2.getBrand().size();
        int size3 = info3.getBrand().size();

        //name has all the names of all the medicines of the store branch we chose
        Vector<String> name = new Vector<>();
        if (branchNB == 1) {
            for (int i = 0; i < size1; i++) {
                name.addElement(info.getBrand().get(i));
            }
        } else if (branchNB == 2) {
            for (int i = 0; i < size2; i++) {
                name.addElement(info2.getBrand().get(i));
            }
        } else if (branchNB == 3) {
            for (int i = 0; i < size3; i++) {
                name.addElement(info3.getBrand().get(i));
            }
        }

        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        name.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        set.addAll(name);
        name.clear();
        name.addAll(set);
        //create the combo box
        name.add(0, "Medicine name/brand");
        JComboBox<String> names = new JComboBox<String>(name);
        names.setVisible(true);
        myPanel.add(names);

        Vector<String> amount = new Vector<>();
        if (branchNB == 1) {
            for (int i = 0; i < size1; i++) {
                amount.addElement(info.getAmount().get(i));
            }
        } else if (branchNB == 2) {
            for (int i = 0; i < size2; i++) {
                amount.addElement(info2.getAmount().get(i));
            }
        } else if (branchNB == 3) {
            for (int i = 0; i < size3; i++) {
                amount.addElement(info3.getAmount().get(i));
            }
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        amount.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> amountSet = new LinkedHashSet<String>();
        amountSet.addAll(amount);
        amount.clear();
        amount.addAll(amountSet);
        amount.add(0, "Amount per box");
        //create the combo box
        JComboBox<String> amountCB = new JComboBox<String>(amount);
        amountCB.setVisible(true);
        myPanel.add(amountCB);

        Vector<String> description = new Vector<>();
        if (branchNB == 1) {
            for (int i = 0; i < size1; i++) {
                description.addElement(info.getDescription().get(i));
            }
        } else if (branchNB == 2) {
            for (int i = 0; i < size2; i++) {
                description.addElement(info2.getDescription().get(i));
            }
        } else if (branchNB == 3) {
            for (int i = 0; i < size3; i++) {
                description.addElement(info3.getDescription().get(i));
            }
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        description.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> descriptionSet = new LinkedHashSet<String>();
        descriptionSet.addAll(description);
        description.clear();
        description.addAll(descriptionSet);
        description.add(0, "Description");
        //create the combo box
        JComboBox<String> descriptionCB = new JComboBox<String>(description);
        descriptionCB.setVisible(true);
        myPanel.add(descriptionCB);

        Vector<String> category = new Vector<>();
        if (branchNB == 1) {
            for (int i = 0; i < size1; i++) {
                category.addElement(info.getCategory().get(i));
            }
        } else if (branchNB == 2) {
            for (int i = 0; i < size2; i++) {
                category.addElement(info2.getCategory().get(i));
            }
        } else if (branchNB == 3) {
            for (int i = 0; i < size3; i++) {
                category.addElement(info3.getCategory().get(i));
            }
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        category.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> categorySet = new LinkedHashSet<String>();
        categorySet.addAll(category);
        category.clear();
        category.addAll(categorySet);
        category.add(0, "Category");
        //create the combo box
        JComboBox<String> categoryCB = new JComboBox<String>(category);
        categoryCB.setVisible(true);
        myPanel.add(categoryCB);

        ArrayList<String> str3 = new ArrayList<>();
        if (branchNB == 1) str3 = info.getSPrice(); //GET FROM DB
        else if (branchNB == 2) str3 = info2.getSPrice(); //GET FROM DB
        else if (branchNB == 3) str3 = info3.getSPrice(); //GET FROM DB
        str3.sort(String::compareToIgnoreCase);
        //remove duplicates from the list
        LinkedHashSet<String> set3 = new LinkedHashSet<String>();
        set3.addAll(str3);
        str3.clear();
        str3.addAll(set3);
        str3.add(0,"Sale price");
        String[] choices3 = str3.toArray(new String[str3.size()]);
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model3 = new SortedComboBoxModel<String>(choices3);
        JComboBox<String> comboBox3 = new JComboBox<String>(model3);
        comboBox3.setVisible(true);
        myPanel.add(comboBox3);

        ArrayList<String> str4 = new ArrayList<>();
        if (branchNB == 1) str4 = info.getPPrice(); //GET FROM DB
        else if (branchNB == 2) str4 = info2.getPPrice(); //GET FROM DB
        else if (branchNB == 3) str4 = info3.getPPrice(); //GET FROM DB
        //remove duplicates from the list
        LinkedHashSet<String> set4 = new LinkedHashSet<String>();
        set4.addAll(str4);
        str4.clear();
        str4.addAll(set4);
        str4.add(0,"Purchase price");
        String[] choices4 = str4.toArray(new String[str4.size()]);
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model4 = new SortedComboBoxModel<String>(choices4);
        JComboBox<String> comboBox4 = new JComboBox<String>(model4);
        comboBox4.setVisible(true);
        myPanel.add(comboBox4);

        //set the combo box
        //when using the servlet, we have to go through the db and names
        GetDB_clients clientDB = new GetDB_clients(); //get if from the db
        int sizeName = clientDB.getFirstName().size();
        Vector<String> fullName = new Vector<>();
        for (int i = 0; i < sizeName; i++) {
            fullName.add(clientDB.getFirstName().get(i) + " " + clientDB.getLastName().get(i));
        }
        fullName.add(0, "Select a client");
        final JComboBox<String> namesClient = new JComboBox<String>(fullName);
        namesClient.setVisible(true);
        myPanel.add(namesClient);

        //set the combo box - should we make sure that it only proposes available quantities?
        Vector<String> choices = new Vector<String>();
        choices.addElement("Select amount");
        choices.addElement("1");
        choices.addElement("2");
        choices.addElement("3");
        choices.addElement("4");
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setVisible(true);
        //problem with this is that the value is the initial one, ie always 1
        //System.out.println("the selected number of medication is: " + varName);
        myPanel.add(cb);

        frmOpt.add(myPanel);

        int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Please Enter the details of the medicine you wish to remove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        //check if the quantities make sense when ok is pressed
        if (result == JOptionPane.OK_OPTION) {
            String selectedName = names.getSelectedItem().toString();
            String selectedAmount = amountCB.getSelectedItem().toString();
            String selectedDescription = descriptionCB.getSelectedItem().toString();
            String selectedCategory = categoryCB.getSelectedItem().toString();
            String selectedClient = namesClient.getSelectedItem().toString();
            Integer qtySold = Integer.parseInt(cb.getSelectedItem().toString());
            String selectedSalesP = comboBox3.getSelectedItem().toString();
            String selectedPurchP = comboBox4.getSelectedItem().toString();

            boolean validEntry = true; //assume the entries are valid
            checkForMed CFM = new checkForMed();
            //false if the medicine is not in
            Vector<Integer> id;
            id = CFM.isTheMedicineIn(selectedName, selectedAmount, selectedSalesP, selectedPurchP, selectedDescription, selectedCategory, branchNB);
            if (id.size() == 0) validEntry = false; // if no medicine has been found having the same values,
            //// give an error. you cannot update a stock of smt that is not in the db

            if (validEntry == false) {
                JPanel error = new JPanel();
                error.setVisible(true);
                JLabel errorMsg = new JLabel("Error, this medicine does not exist in the current stock, add it to the stock and try to update it afterwards");
                error.add(errorMsg);
                // dialog box - for now no icon (Plain message)
                int result3 = JOptionPane.showConfirmDialog(null, error,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }

            if (validEntry == true) {

                //now check the input makes sens with respect to stock available
                ArrayList<String> strAmount = new ArrayList<>();
                if (branchNB == 1) strAmount = info.getCurrentStock(); //GET FROM DB
                if (branchNB == 2) strAmount = info2.getCurrentStock();
                if (branchNB == 3) strAmount = info3.getCurrentStock();

                //System.out.println("The current stock of this medicine is: " + strAmount.get(id.get(0) - 1));
                //check that the number in the database is superior than the quantity to remove if it is a removal
                boolean finalstep = true;

                try {
                    //this will give an error if the string has smt else than numbers
                    //this is good as even 4.3 gives an error, and we can only work with ints
                    if ((Integer.parseInt(strAmount.get(id.get(0) - 1)) - qtySold) < 0) {

                        //if the quantity to add/remove + what is in stock is negative, you have a problem
                        JPanel error = new JPanel();
                        error.setVisible(true);
                        JLabel errorMsg = new JLabel("Error, you wish to remove more medicine than you have in stock");
                        error.add(errorMsg);
                        int result3 = JOptionPane.showConfirmDialog(null, error,
                                "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                        finalstep = false;
                    }

                    //error if it is limited
                    Integer columId = id.get(0)-1;
                    //System.out.println("the col id is "+columId);
                    boolean lim = true;
                    String limit = "";
                    if (branchNB==1) limit = info.getLimit().get(columId).toString();
                    else if (branchNB==2) limit= info2.getLimit().get(columId).toString();
                    else if (branchNB==3) limit= info3.getLimit().get(columId).toString();
                    if(limit.equalsIgnoreCase("false")) lim = false;

                    if(lim==true && qtySold>1) {
                        JPanel error = new JPanel();
                        error.setVisible(true);
                        JLabel errorMsg = new JLabel("Error, the medicine is limited, you can only sell 1 at a time");
                        error.add(errorMsg);
                        int result3 = JOptionPane.showConfirmDialog(null, error,
                                "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                        finalstep=false;

                    }


                } catch (Exception e) {
                    JPanel error = new JPanel();
                    error.setVisible(true);
                    JLabel errorMsg = new JLabel("Error, you have to enter a valid number for the quantity");
                    error.add(errorMsg);
                    int result3 = JOptionPane.showConfirmDialog(null, error,
                            "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                    finalstep = false;
                }

                if (finalstep) {
                    ArrayList<String> strID = new ArrayList<>();
                    if (branchNB == 1) strID = info.getID(); //GET FROM DB
                    if (branchNB == 2) strID = info2.getID(); //GET FROM DB
                    if (branchNB == 3) strID = info3.getID(); //GET FROM DB

                    Integer id1 = id.get(0) - 1;
                    Integer idToSendToDB = Integer.parseInt(strID.get(id1));
                    //System.out.println("The id is: " + idToSendToDB);
                    Integer updatedAmount = Integer.parseInt(strAmount.get(id.get(0) - 1)) - qtySold;
                    String branchname = "";
                    if (branchNB == 1) branchname = "products";
                    if (branchNB == 2) branchname = "products2";
                    if (branchNB == 3) branchname = "products3";

                    UpdateRequest(idToSendToDB, updatedAmount, branchname);

                    JPanel success = new JPanel();
                    success.setVisible(true);
                    //System.out.println("Quantity to add/rem is: "+qtyAddRem);
                    JLabel successMsg = new JLabel("Success! You have sold " +qtySold +" "+ selectedName+ " to " +selectedClient);
                    success.add(successMsg);
                    // dialog box - for now no icon (Plain message)
                    int result3 = JOptionPane.showConfirmDialog(null, success,
                            "Success", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                }
            }
        }
    }

    public static void UpdateRequest(Integer idNUM, Integer CS, String branchName) {
        // This is the SQL query included in the body of the POST request = instructions
        String query = "UPDATE " + branchName + " SET currentstock = " + CS + " where id = " + idNUM + ";";
        new Post(query);
    }
}

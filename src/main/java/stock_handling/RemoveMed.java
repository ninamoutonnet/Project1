package stock_handling;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import GUI.dummyFrame;
import Requests.Post;
import db_handling.GetDB_medicine;
import db_handling.checkForMed;
import javax.swing.*;
import java.util.LinkedHashSet;

public class RemoveMed {

    GetDB_medicine info = new GetDB_medicine(1);
    GetDB_medicine info2 = new GetDB_medicine(2);
    GetDB_medicine info3 = new GetDB_medicine(3);
    String Branch = "";


    public RemoveMed(String branch){
        Branch = branch;
        //System.out.println("the branch is" + Branch);

        //construct a dummy frame using the class! since it is a redundant operation
        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(6,1));

        //relate the different branches to the different databases tables
        Integer branchNB =0;
        if(Branch.equalsIgnoreCase("Paddington")) branchNB =2;
        if(Branch.equalsIgnoreCase("Green Park")) branchNB =1;
        if(Branch.equalsIgnoreCase("East End")) branchNB =3;

        //sizes of the different DB from different branches
        int size1 = info.getBrand().size();
        int size2 = info2.getBrand().size();
        int size3 = info3.getBrand().size();

        //name has all the names of all the medicines of the store branch we chose
        Vector<String> name = new Vector<>();
        if(branchNB==1) {
            for (int i = 0; i < size1; i++) {
                name.addElement(info.getBrand().get(i));
            }
        }else if(branchNB==2){
            for (int i = 0; i < size2; i++) {
                name.addElement(info2.getBrand().get(i));
            }
        }else if(branchNB==3){
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
        name.add(0, "Name/brand");
        JComboBox<String> names = new JComboBox<String>(name);
        names.setVisible(true);
        myPanel.add(names);



        Vector<String> amount = new Vector<>();
        if(branchNB==1) {
            for (int i = 0; i < size1; i++) {
                amount.addElement(info.getAmount().get(i));
            }
        }else if(branchNB==2){
            for (int i = 0; i < size2; i++) {
                amount.addElement(info2.getAmount().get(i));
            }
        }else if(branchNB==3){
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



        Vector<String> sprice = new Vector<>();
        if(branchNB==1) {
            for (int i = 0; i < size1; i++) {
                sprice.addElement(info.getSPrice().get(i));
            }
        }else if(branchNB==2){
            for (int i = 0; i < size2; i++) {
                sprice.addElement(info2.getSPrice().get(i));
            }
        }else if(branchNB==3){
            for (int i = 0; i < size3; i++) {
                sprice.addElement(info3.getSPrice().get(i));
            }
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        sprice.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> spSet = new LinkedHashSet<String>();
        spSet.addAll(sprice);
        sprice.clear();
        sprice.addAll(spSet);
        sprice.add(0, "Sale price");
        //create the combo box
        JComboBox<String> spriceCB = new JComboBox<String>(sprice);
        spriceCB.setVisible(true);
        myPanel.add(spriceCB);




        Vector<String> pprice = new Vector<>();
        if(branchNB==1) {
            for (int i = 0; i < size1; i++) {
                pprice.addElement(info.getPPrice().get(i));
            }
        }else if(branchNB==2){
            for (int i = 0; i < size2; i++) {
                pprice.addElement(info2.getPPrice().get(i));
            }
        }else if(branchNB==3){
            for (int i = 0; i < size3; i++) {
                pprice.addElement(info3.getPPrice().get(i));
            }
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        pprice.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> ppSet = new LinkedHashSet<String>();
        ppSet.addAll(pprice);
        pprice.clear();
        pprice.addAll(ppSet);
        pprice.add(0, "Purchase price");
        //create the combo box
        JComboBox<String> ppriceCB = new JComboBox<String>(pprice);
        ppriceCB.setVisible(true);
        myPanel.add(ppriceCB);




        Vector<String> description = new Vector<>();
        if(branchNB==1) {
            for (int i = 0; i < size1; i++) {
                description.addElement(info.getDescription().get(i));
            }
        }else if(branchNB==2){
            for (int i = 0; i < size2; i++) {
                description.addElement(info2.getDescription().get(i));
            }
        }else if(branchNB==3){
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
        if(branchNB==1) {
            for (int i = 0; i < size1; i++) {
                category.addElement(info.getCategory().get(i));
            }
        }else if(branchNB==2){
            for (int i = 0; i < size2; i++) {
                category.addElement(info2.getCategory().get(i));
            }
        }else if(branchNB==3){
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



        frmOpt.add(myPanel);

        int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Please Enter the details of the medicine you wish to remove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {


            checkForMed idIfMedIsInDB = new checkForMed();
            Vector<Integer> id = idIfMedIsInDB.isTheMedicineIn(names.getSelectedItem().toString(), amountCB.getSelectedItem().toString(), spriceCB.getSelectedItem().toString(), ppriceCB.getSelectedItem().toString(), descriptionCB.getSelectedItem().toString(), categoryCB.getSelectedItem().toString(), branchNB);

            if(id.size()==0){
                //if the medicine to  remove is not found in the db
                dummyFrame df1 = new dummyFrame();
                JFrame frmOpt1 = df1.dummyFrameConstruction();

                JPanel error = new JPanel();
                error.setVisible(true);
                JLabel errorMsg = new JLabel("Error, you wish to remove a medicine that is not in the database ");
                error.add(errorMsg);
                int result3 = JOptionPane.showConfirmDialog(frmOpt1, error,
                        "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                if(result3==0) frmOpt1.dispose();

            }else{
                Vector<Integer> ids = new Vector<>();
                if(branchNB==1) {
                    for (int i = 0; i < size1; i++){
                        ids.addElement(Integer.parseInt(info.getID().get(i)));
                    }
                }
                else if(branchNB==2) {
                    for (int i = 0; i < size2; i++){
                        ids.addElement(Integer.parseInt(info2.getID().get(i)));
                    }
                }
                else if(branchNB==3) {
                    for (int i = 0; i < size3; i++){
                        ids.addElement(Integer.parseInt(info3.getID().get(i)));
                    }
                }

                //System.out.println("what fucks up: "+ (id.get(0)-1));

               if(branchNB==1) Delete(ids.get(id.get(0)-1), "products"); //delete query for the sql db
               else if(branchNB==2) Delete(ids.get(id.get(0)-1), "products2");
               else if(branchNB==3) Delete(ids.get(id.get(0)-1), "products3");


               // Delete(ids.get(id.get(0)-1)); //delete query for the sql db

                dummyFrame df2 = new dummyFrame();
                JFrame frmOpt2 = df2.dummyFrameConstruction();

                JPanel success = new JPanel();
                success.setVisible(true);
                JLabel errorMsg = new JLabel("The medicine has been successfully removed from the database");
                success.add(errorMsg);
                int result3 = JOptionPane.showConfirmDialog(frmOpt2, success,
                        "Success", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(result3==0) frmOpt2.dispose();
            }

            frmOpt.dispose(); //discard of the dummy jframe
        }else{
            frmOpt.dispose(); //discard of the dummy jframe
        }
    }

    public static void Delete(Integer idNUM, String prodDB) {
        // This is the SQL query included in the body of the POST request = instructions
        String query = "DELETE from "+prodDB+" where id = "+idNUM+";";
        new Post(query);
    }
}

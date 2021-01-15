package stock_handling;

import db_handling.GetDB_medicine;
import GUI.dummyFrame;
import db_handling.checkForMed;
import GUI.checkTheInput;
import Requests.Post;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Vector;

/* This class makes sure user can input new medicine to add to list of drugs sold & adds that new medicine to the DB if it does not exist already */

public class AddMed {

    //get all the information about the medicine in each branch
    GetDB_medicine info1 = new GetDB_medicine(1);
    GetDB_medicine info2 = new GetDB_medicine(2);
    GetDB_medicine info3 = new GetDB_medicine(3);


    // Method for the UI
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
        String Branch = "";

        // Information that the user will need to input to add a new medicine
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

        // CATEGORY
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Category "));
        //from the 3 different tables, add all of the categories of each one
        //str1 will have duplicates at first but those will be eliminated when str1 is converted to a hashset
        //you have to ensure that the category drop down box has all the possible medicine category regardless of the branch you select in the next drop down menu
        ArrayList<String> str1 = info1.getCategory();
        str1.addAll(info2.getCategory());
        str1.addAll(info3.getCategory());
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

        // Branch name
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Branch "));
        String[] arrayBranches = {"East End", "Green Park", "Paddington"};
        JComboBox<String> comboBoxBranch = new JComboBox<>(arrayBranches);
        comboBoxBranch.setVisible(true);
        myPanel.add(comboBoxBranch);


        //create the option pane window with all the different drop down menus
        int result = JOptionPane.showConfirmDialog(fr, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //get the values of combobox once OK is pressed
            // Get the inputs from the person using the app --> information for one client
            Branch = comboBoxBranch.getSelectedItem().toString();
            LIM = limited.getSelectedItem().toString();
            CAT = comboBoxCategory.getSelectedItem().toString();
            String A = amount.getText();
            DES = description.getText();
            String N = name.getText();

            boolean okToPost = true; //boolean that allows to ensure that all the values are valid before sending them to the db

            //check that current stock is an int, if not give an error
            checkTheInput check = new checkTheInput();//use the object created for checking
            boolean ok = false;
            Integer CS=0;
            ok = check.isAnInt(qty.getText(), "Current Stock");
            //ok will return true if the content of qty text box is an int
            if(!ok) okToPost = false; //thus is it is not an int, it will signal it by changing the value of okToPost to false
            // if it is indeed an int, store its value in CS
            else CS = Integer.parseInt(qty.getText());

            //check that sales price  is a double, if not give an error
            //same logic as above
            boolean ok1 = false;
            Double SP=0.0;
            ok1 = check.isADouble(sprice.getText(), "Sales Price");
            if(!ok1) okToPost = false;
            else SP = Double.parseDouble(sprice.getText());

            //check that  purchase price is a double, if not give an error
            //same logic as above
            boolean ok2 = false;
            Double PP=0.0;
            ok2 = check.isADouble(pprice.getText(), "Purchase Price");
            if(!ok2) okToPost = false;
            else PP = Double.parseDouble(pprice.getText());

            //check that full stock is an int, if not give an error
            //same logic as above
            boolean ok3 = false;
            Integer FS=0;
            ok3 = check.isAnInt(fullstock.getText(), "Full Stock");
            if(!ok3) okToPost = false;
            else FS = Integer.parseInt(fullstock.getText());

            //check that brand name is not empty, if not give an error
            if (name.getText().isEmpty()==true ) okToPost =false;

            //check that brand description is not empty, if not give an error
            if (description.getText().isEmpty()==true ) okToPost =false;

            //check that brand amount/box is not empty, if not give an error
            if (amount.getText().isEmpty()==true ) okToPost =false;


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
                boolean notAlreadyIn = false; //assume that the medicine to add is not present in the database of the selected branch

                //relate the different branches to the different databases tables
                Integer branchNB =0;
                if(Branch.equalsIgnoreCase("Paddington")) branchNB =2;
                if(Branch.equalsIgnoreCase("Green Park")) branchNB =1;
                if(Branch.equalsIgnoreCase("East End")) branchNB =3;

                //id will store the index of the medicine that corrresponds to the input
                //if the medicine does not exist in that branch, id will be empty and thus you will be allowed to add it
                //if id is not empty, it means that the medicine was found in the database of the specified branch, you will not be able to add it
                id =  CFM.isTheMedicineIn(N, A, sprice.getText(),pprice.getText(),DES,CAT,branchNB); //returns the id of the medicine in the database

                if (id.size()==0) {
                    notAlreadyIn = true; //tells you that the db does not contain the medicine
                }
                if(!notAlreadyIn) {
                    //only enter this loop if the medicine is already in the db
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

                    // add the medicine to the db, the branchNB specify to which db you add the medicine
                    if(branchNB==1) makePostRequest(N, A, SP, PP, FS, LIM2, DES, CAT, CS, "products"); //check that it does not already exist
                    else if(branchNB==2) makePostRequest(N, A, SP, PP, FS, LIM2, DES, CAT, CS,"products2");
                    else if(branchNB==3) makePostRequest(N, A, SP, PP, FS, LIM2, DES, CAT, CS, "products3");

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
            else{
                //only enters this loop if there was an issue in the entered values.
                if(name.getText().isEmpty()==true){
                    //if the medicine to  remove is not found in the db
                    dummyFrame df1 = new dummyFrame();
                    JFrame frmOpt1 = df1.dummyFrameConstruction();

                    JPanel error = new JPanel();
                    error.setVisible(true);
                    JLabel errorMsg = new JLabel("Error, you must enter a value for the name of the medicine ");
                    error.add(errorMsg);
                    int result3 = JOptionPane.showConfirmDialog(frmOpt1, error,
                            "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if(result3==0) frmOpt1.dispose();
                }
                if(amount.getText().isEmpty()==true){
                    //if the medicine to  remove is not found in the db
                    dummyFrame df1 = new dummyFrame();
                    JFrame frmOpt1 = df1.dummyFrameConstruction();

                    JPanel error = new JPanel();
                    error.setVisible(true);
                    JLabel errorMsg = new JLabel("Error, you must enter a value for the amount per box of the medicine ");
                    error.add(errorMsg);
                    int result3 = JOptionPane.showConfirmDialog(frmOpt1, error,
                            "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if(result3==0) frmOpt1.dispose();

                }
                if(description.getText().isEmpty()==true){
                    //if the medicine to  remove is not found in the db
                    dummyFrame df1 = new dummyFrame();
                    JFrame frmOpt1 = df1.dummyFrameConstruction();

                    JPanel error = new JPanel();
                    error.setVisible(true);
                    JLabel errorMsg = new JLabel("Error, you must enter a value for the description of the medicine ");
                    error.add(errorMsg);
                    int result3 = JOptionPane.showConfirmDialog(frmOpt1, error,
                            "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if(result3==0) frmOpt1.dispose();
                }
            }
        }
        else {
            fr.dispose(); //discard of the dummy jframe
        }
    }

    // Method making a POST request to the servlet to add a new medicine to the DB
    public static void makePostRequest(String N, String A, Double SP, Double PP, Integer FS, Boolean LIM, String DES, String CAT, Integer CS, String prodDB) {
        // This is the SQL query included in the body of the POST request = instructions
        String query = "INSERT INTO "+prodDB+" (brand,amount,\"sprice \",pprice,\"fullstock \",\"limitation \",\"description \",\"category \",currentstock) values( '"+N+"','"+A+"',"+SP+","+PP+","+FS+","+LIM+",'"+DES+"','"+CAT+"',"+CS+");";
        new Post(query);
    }
}


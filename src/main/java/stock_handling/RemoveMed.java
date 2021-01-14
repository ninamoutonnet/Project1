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
import db_handling.GetDB_medicine;
import db_handling.checkForMed;
import javax.swing.*;
import java.util.LinkedHashSet;

public class RemoveMed {

    GetDB_medicine info = new GetDB_medicine(1);

    public RemoveMed(){
        //construct a dummy frame using the class! since it is a redundant operation
        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(7,1));


        Vector<String> name = new Vector<>();
        int size = info.getBrand().size();
        for (int i = 0; i < size; i++){
            name.addElement(info.getBrand().get(i));
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
        for (int i = 0; i < size; i++){
            amount.addElement(info.getAmount().get(i));
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
        for (int i = 0; i < size; i++){
            sprice.addElement(info.getSPrice().get(i));
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
        for (int i = 0; i < size; i++){
            pprice.addElement(info.getPPrice().get(i));
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
        for (int i = 0; i < size; i++){
            description.addElement(info.getDescription().get(i));
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
        for (int i = 0; i < size; i++){
            category.addElement(info.getCategory().get(i));
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

        //Branch name
        String[] arrayBranches = {"Branch","East End", "Green Park", "Paddington"};
        JComboBox<String> comboBoxBranch = new JComboBox<>(arrayBranches);
        comboBoxBranch.setVisible(true);
        myPanel.add(comboBoxBranch);

        frmOpt.add(myPanel);

        int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Please Enter the details of the medicine you wish to remove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            checkForMed idIfMedIsInDB = new checkForMed();
            Vector<Integer> id = idIfMedIsInDB.isTheMedicineIn(names.getSelectedItem().toString(), amountCB.getSelectedItem().toString(), spriceCB.getSelectedItem().toString(), ppriceCB.getSelectedItem().toString(), descriptionCB.getSelectedItem().toString(), categoryCB.getSelectedItem().toString());
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
                for (int i = 0; i < size; i++){
                    ids.addElement(Integer.parseInt(info.getID().get(i)));
                }
                Delete(ids.get(id.get(0)-1)); //delete query for the sql db

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

    public static void Delete(Integer idNUM) {
        try {
            // This is the SQL query included in the body of the POST request = instructions
            String message = "DELETE from products where id = "+idNUM+";";
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

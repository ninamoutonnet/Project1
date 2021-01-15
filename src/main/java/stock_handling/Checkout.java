package stock_handling;

import GUI.dummyFrame;
import Requests.Post;
import db_handling.GetDB_medicine;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Vector;

public class Checkout {

    private String amt;

    public Checkout(String cat, String brand, String amount, String customer, String branch) {

        int branchNB = 0;

        if(branch.equalsIgnoreCase("Paddington")) branchNB =2;
        if(branch.equalsIgnoreCase("Green Park")) branchNB =1;
        if(branch.equalsIgnoreCase("East End")) branchNB =3;

        GetDB_medicine info = new GetDB_medicine(branchNB);

        //System.out.println("the branch is " + branchNB);

        boolean found = false;
        boolean match = false;
        int IDCheckout = 0;

        for (int i = 0; i < info.getBrand().size(); i++) {
            // get the ID of selected med
            if ((brand.equals(info.getBrand().get(i))) && (!found)) {
                // compare to see if the correct category was selected
                found = true;
                int ID = Integer.parseInt(info.getID().get(i));
                if (cat.equals(info.getCategory().get(ID))) {
                    match = true;
                    IDCheckout = Integer.parseInt(info.getID().get(i));
                    amt = amount;
                }
            }
        }

        if (!match) {
            JOptionPane.showMessageDialog(null, "Checkout unsuccessful, please try again", " " , JOptionPane.PLAIN_MESSAGE);
            //System.out.println("not a match");
        }
        else {
            DBout(IDCheckout, amt);
            JOptionPane.showMessageDialog(null, "Checkout successful", " " , JOptionPane.PLAIN_MESSAGE);
            //System.out.println("The ID is " + IDCheckout + " The amount is " + amt);
        }

    }


    public static void DBout (int idNUM, String amount) {
        try {
            //System.out.println("The ID is " + idNUM + " The amount is " + amount);
            // This is the SQL query included in the body of the POST request = instructions
            String query = "UPDATE products SET currentstock = currenstock - "+amount+" where id = "+idNUM+";";
            new Post(query);
        }
        catch(Exception e) {
            System.out.println("Something went wrong");
        }
    }
}

package stock_handling;

import GUI.dummyFrame;
import db_handling.GetDB_medicine;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class Checkout {

    private String amt;

    public Checkout(String cat, String brand, String amount, String customer, int branch) {

        GetDB_medicine info = new GetDB_medicine(branch);

        boolean found = false;
        boolean match = false;
        int IDCheckout = 0;

        for (int i = 0; i < info.getBrand().size(); i++) {
            // get the ID of selected med
            if ((brand.equals(info.getBrand().get(i))) && (!found)) {
                //System.out.println("in the loop");
                // compare to see if the correct category was selected
                int ID = Integer.parseInt(info.getID().get(i));
                found = true;
                if (cat.equals(info.getCategory().get(ID))) {
                    match = true;
                    IDCheckout = Integer.parseInt(info.getID().get(i));
                    amt = amount;
                }
            }
        }

        if (!match) {
            /*dummyFrame df2 = new dummyFrame();
            JFrame f1 = df2.dummyFrameConstruction();
            JPanel fail = new JPanel();
            fail.setVisible(true);
            JLabel errorMsg = new JLabel("Checkout unsuccessful, please try again");
            fail.add(errorMsg);*/
            System.out.println("not a match bitch");
        }
        else {
            DBout(IDCheckout, amt);
            System.out.println("The ID is " + IDCheckout + " The amount is " + amt);
        }

    }

    public static void DBout (int idNUM, String amount) {
        try {
            System.out.println("The ID is " + idNUM + " The amount is " + amount);
            // This is the SQL query included in the body of the POST request = instructions
            String message = "UPDATE products SET currentstock = currenstock - "+amount+" where id = "+idNUM+";";
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

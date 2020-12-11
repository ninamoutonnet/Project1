import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JOptionPaneAddClient {
    public JOptionPaneAddClient() throws SQLException {
        JTextField famname = new JTextField(5);
        JTextField givname = new JTextField(5);
        JTextField cardNb = new JTextField(5);
        JTextField CCV = new JTextField(5);
        JTextField expDate = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Family name:"));
        myPanel.add(famname);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Given name:"));
        myPanel.add(givname);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Card Number:"));
        myPanel.add(cardNb);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("CCV :"));
        myPanel.add(CCV);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Exp date:"));
        myPanel.add(expDate);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter the clients' details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            //COMMUNICATION WITH THE DB ON THE COMPUTER
            String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
            try {
                // Registers the driver Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
            }

            Connection conn= DriverManager.getConnection(dbUrl, "postgres", "jivajiva2");

            try {
                Statement s=conn.createStatement();
                String FN = famname.getText();
                String GN = givname.getText();
                String CN = cardNb.getText();
                String Ccv = CCV.getText();
                String ED = expDate.getText();

                String sqlStr = "INSERT INTO clients (familyname,givenname,cardnumber,ccv,expdate) values( '"+famname.getText()+"','"+givname.getText()+"','"+cardNb.getText()+"','"+CCV.getText()+"','"+expDate.getText()+"');";
                s.execute(sqlStr);

                s.close();
                conn.close();
            }
            catch (Exception e){
                System.out.println("Problem with inserting SQL into the db");
            }


            System.out.println("family name: " + famname.getText());
            System.out.println("Given name: " + givname.getText());
            System.out.println("Card Number: " + cardNb.getText());
            System.out.println("CCV: " + CCV.getText());
            System.out.println("Exp date: " + expDate.getText());
        }
    }
}

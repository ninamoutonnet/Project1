import javax.swing.*;
import java.awt.*;

public class JOptionPaneAddClient {
    public JOptionPaneAddClient() {
        JTextField name = new JTextField(10);
        JTextField cardNb = new JTextField(13);
        JTextField CCV = new JTextField(3);
        JTextField expDate = new JTextField(6);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(4, 2));
        myPanel.add(new JLabel("Name:"));
        myPanel.add(name);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Card Number:"));
        myPanel.add(cardNb);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("CCV :"));
        myPanel.add(CCV);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Exp date:"));
        myPanel.add(expDate);

        // dialog box - for now no icon (Plain message)
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please enter the client's details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Name: " + name.getText());
            System.out.println("Card Number: " + cardNb.getText());
            System.out.println("CCV: " + CCV.getText());
            System.out.println("Exp date: " + expDate.getText());
        }
    }
}

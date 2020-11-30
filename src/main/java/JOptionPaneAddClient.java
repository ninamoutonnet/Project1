import javax.swing.*;

public class JOptionPaneAddClient {
    public JOptionPaneAddClient() {
        JTextField name = new JTextField(5);
        JTextField cardNb = new JTextField(5);
        JTextField CCV = new JTextField(5);
        JTextField expDate = new JTextField(5);

        JPanel myPanel = new JPanel();
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

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter the clients' details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Name: " + name.getText());
            System.out.println("Card Number: " + cardNb.getText());
            System.out.println("CCV: " + CCV.getText());
            System.out.println("Exp date: " + expDate.getText());
        }
    }
}

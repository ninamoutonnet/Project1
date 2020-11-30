import javax.swing.*;

public class AddMed {
    public AddMed(){
        JFrame frmOpt = new JFrame();  //dummy JFrame to ensure that the window popping up is on top of anything else
        frmOpt.setVisible(true);
        frmOpt.setLocation(100, 100);
        frmOpt.setAlwaysOnTop(true);

        JTextField name = new JTextField(5);
        JTextField price = new JTextField(5);
        JTextField quantity = new JTextField(5);


        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Name:"));
        myPanel.add(name);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Price:"));
        myPanel.add(price);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Quantity :"));
        myPanel.add(quantity);

        int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Name: " + name.getText());
            System.out.println("Price: " + price.getText());
            System.out.println("Quantity: " + quantity.getText());
        }
        frmOpt.dispose(); //discard of the dummy jframe
    }
}


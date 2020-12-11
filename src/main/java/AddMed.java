import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddMed {

    public AddMed() {
        JFrame fr = new JFrame("Add a new medicine");  //dummy JFrame to ensure that the window popping up is on top of anything else
        fr.setVisible(true);
        fr.setLocation(100,100);
        fr.setAlwaysOnTop(true);

        JTextField brand = new JTextField(10);
        JTextField name = new JTextField(13);
        JTextField qty = new JTextField(13);
        JTextField sprice = new JTextField(3);
        JTextField pprice = new JTextField(6);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(new JLabel("Medicine Brand:"));
        myPanel.add(brand);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Medicine Name:"));
        myPanel.add(name);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Quantity:"));
        myPanel.add(qty);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Sale price :"));
        myPanel.add(sprice);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Purchase price:"));
        myPanel.add(pprice);

        int result = JOptionPane.showConfirmDialog(fr, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // for now -> print text but later us this loop to get data
            System.out.println("Medicine brand: " + brand.getText());
            System.out.println("Medicine name: " + name.getText());
            System.out.println("Quantity: " + name.getText());
            System.out.println("Sale price: " + sprice.getText());
            System.out.println("Purchase price : " + pprice.getText());

        }
        fr.dispose(); //discard of the dummy jframe
    }
}


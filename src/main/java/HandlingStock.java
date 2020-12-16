import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandlingStock {

    public HandlingStock() {

        JButton View_stock = new JButton("View Stock");
        JButton add_remove_med = new JButton("Add or remove a medicine");
        JButton updateStock = new JButton("Update Stock");

        JPanel HSpanel = new JPanel();
        HSpanel.setVisible(true);
        HSpanel.setLayout(new GridLayout(3, 1));

        // add VIEW STOCK
        HSpanel.add(View_stock);
        HSpanel.add(Box.createHorizontalStrut(15)); // a spacer

        //ADD REMOVE MED
        HSpanel.add(add_remove_med);
        add_remove_med.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPaneMultipleInputAddRemove newMed = new JOptionPaneMultipleInputAddRemove();
            }
        });

        //UPDATE STOCK
        HSpanel.add(updateStock);

        //fr.add(HSpanel);
        int result = JOptionPane.showConfirmDialog(null, HSpanel,
                "Please enter the client's details", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

    }
}





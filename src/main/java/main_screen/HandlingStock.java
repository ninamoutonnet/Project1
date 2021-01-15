package main_screen;

import GUI.dummyFrame;
import stock_handling.JOptionPaneMultipleInputAddRemove;
import stock_handling.RemoveMed;
import stock_handling.UpdateStock;
import stock_handling.ViewStock;
//import stock_handling.ViewStock;

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
        // to update the stock correctly each time -> need info about medicine:
        // original quantity when medicine is added
        // each time there is a purchase -> call a function that returns what & how many have been bought
       View_stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStock VS = new ViewStock();
            }
        });

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
        updateStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dummyFrame df = new dummyFrame();
                JFrame frmOpt = df.dummyFrameConstruction();
                frmOpt.setVisible(false);

                JPanel myPanel = new JPanel();
                String[] arrayBranches = {"East End", "Green Park", "Paddington"};
                JComboBox<String> comboBoxBranch = new JComboBox<>(arrayBranches);
                comboBoxBranch.setVisible(true);
                myPanel.add(comboBoxBranch);

                int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,"Select the branch",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                if(result==0) {
                    String branch = comboBoxBranch.getSelectedItem().toString();
                    UpdateStock US = new UpdateStock(branch);

                }
            }
        });

        //fr.add(HSpanel);
        int result = JOptionPane.showConfirmDialog(null, HSpanel,
                "Please select the appropriate box", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

    }


}





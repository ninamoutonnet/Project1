package stock_handling;

import GUI.dummyFrame;
import stock_handling.AddMed;
import stock_handling.RemoveMed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOptionPaneMultipleInputAddRemove {

    //constructor
    public  JOptionPaneMultipleInputAddRemove() {

        //construct a dummy frame using the class! since it is a redundant operation
        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        JButton adding = new JButton("Add");
        JButton remove = new JButton("Remove");

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(3, 2));
        myPanel.setLocation(100,100);
        myPanel.add(adding);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(remove);


        adding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //perform this action if the add button is pressed
                AddMed newMed = new AddMed();
            }
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveMed remMed = new RemoveMed();
            }
        });

        // no icon for now
        int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,
              "Add or Remove a medicine", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    }
}


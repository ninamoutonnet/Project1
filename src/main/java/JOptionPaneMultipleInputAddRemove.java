import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOptionPaneMultipleInputAddRemove {

    //constructor
    public  JOptionPaneMultipleInputAddRemove() {
        JButton adding = new JButton("Add");
        JButton remove = new JButton("Remove");

        JPanel myPanel = new JPanel();
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


        int result = JOptionPane.showConfirmDialog(null, myPanel,
              "Add or Remove a medicine", JOptionPane.OK_CANCEL_OPTION);

    }
}


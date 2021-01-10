package stock_handling;

import java.util.*;

import GUI.dummyFrame;
import db_handling.GetDB_medicine;
import javax.swing.*;
import java.util.LinkedHashSet;

public class RemoveMed {

    GetDB_medicine info = new GetDB_medicine();

    public RemoveMed(){
        //construct a dummy frame using the class! since it is a redundant operation
        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();


        Vector<String> name = new Vector<>();
        int size = info.getBrand().size();
        for (int i = 0; i < size; i++){
            name.addElement(info.getBrand().get(i));
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        name.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        set.addAll(name);
        name.clear();
        name.addAll(set);

        final JComboBox<String> names = new JComboBox<String>(name);
        names.setVisible(true);
        JPanel myPanel = new JPanel();
        myPanel.add(names);

        int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println(names.getSelectedItem().toString() + " was removed");
            frmOpt.dispose(); //discard of the dummy jframe
        }else{
            frmOpt.dispose(); //discard of the dummy jframe
        }

    }
}

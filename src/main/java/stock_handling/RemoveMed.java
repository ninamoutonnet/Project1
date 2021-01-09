package stock_handling;

import GUI.dummyFrame;
import db_handling.GetDB_medicine;

import javax.swing.*;
import java.util.Vector;

public class RemoveMed {

    GetDB_medicine info = new GetDB_medicine();

    public RemoveMed(){
        //construct a dummy frame using the class! since it is a redundant operation
        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        //set the combo box
        //when using the servlet, we have to go through the db and names

        Vector<String> name = new Vector<>();
        int size = info.getBrand().size();
        for (int i = 0; i < size; i++){
            name.addElement(info.getBrand().get(i));
        }
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

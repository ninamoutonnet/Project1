import javax.swing.*;
import java.util.Vector;

public class RemoveMed {
    public RemoveMed(){
        JFrame frmOpt = new JFrame();  //dummy JFrame to ensure that the window popping up is on top of anything else
        frmOpt.setVisible(true);
        frmOpt.setLocation(100, 100);
        frmOpt.setAlwaysOnTop(true);

        //set the combo box
        //when using the servlet, we have to go through the db and names
        Vector<String> name = new Vector<String>();
        name.addElement("Paracetamol");
        name.addElement("Spasphon");
        name.addElement("Doliprane");
        name.addElement("aspirin");
        final JComboBox<String> names = new JComboBox<String>(name);
        names.setVisible(true);
        JPanel myPanel = new JPanel();
        myPanel.add(names);

        int result = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Medicine seletcioned: " + names.getSelectedItem().toString());
        }
        frmOpt.dispose(); //discard of the dummy jframe
    }
}

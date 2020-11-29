import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UI extends JPanel{

    JPanel mainPanel = new JPanel();
    JButton View_stock;
    JButton addClient;
    JButton updateStock;
    JButton add_remove_med;


    public UI(){

        mainPanel.setLayout(new GridLayout(2, 4));
        mainPanel.setBackground(Color.pink);


        View_stock = new JButton("View Stock");
        mainPanel.add(View_stock);

        addClient = new JButton("Add Client");
        mainPanel.add(addClient);

        //SpringForm
        String[] labels = {"Name: ", "Fax: ", "Email: ", "Address: "};
        int numPairs = labels.length;
        addClient.addActionListener(new ActionListener(){ //code called when button is called
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showInputDialog("Add new client:");
            }
        });

        updateStock = new JButton("Update Stock");
        mainPanel.add(updateStock);

        add_remove_med = new JButton("Add or remove a medicine");
        mainPanel.add(add_remove_med);

        //use this combo box to see the list of possible medicine to select,
        //ideally it would go through the heroku db and select all the names of the available medicine
        Vector<String> medname = new Vector<String>();
        medname.addElement("paracetamol");
        medname.addElement("doliprame");
        medname.addElement("spasphon");
        medname.addElement("smecta");
        final JComboBox<String> med = new JComboBox<String>(medname);
        med.setVisible(true);
        mainPanel.add(med);

        //set the combo box

        Vector<String> choices = new Vector<String>();
        choices.addElement("1");
        choices.addElement("2");
        choices.addElement("3");
        choices.addElement("4");
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setVisible(true);
        mainPanel.add(cb);


        //set the combo box
        //when using the servlet, we have to go through the db and names
        Vector<String> name = new Vector<String>();
        name.addElement("Jon Smith");
        name.addElement("Louise Baron");
        name.addElement("Nina Moutonnet");
        name.addElement("Irene Mangialomini");
        final JComboBox<String> names = new JComboBox<String>(name);
        names.setVisible(true);
        mainPanel.add(names);

    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;



public class UI extends JPanel{

    JPanel mainPanel = new JPanel();
    JButton View_stock;
    JButton addClient;
    JButton updateStock;
    JButton add_remove_med;



    public UI(){


        mainPanel.setLayout(new GridLayout(2, 4));
        Color mycol = new Color(180, 200, 100); // Color
        mainPanel.setBackground(mycol);

        View_stock = new JButton("View Stock");
        mainPanel.add(View_stock);


        add_remove_med = new JButton("Add or remove a medicine");
        mainPanel.add(add_remove_med);
        add_remove_med.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPaneMultipleInputAddRemove newMed = new JOptionPaneMultipleInputAddRemove();
            }
        });


        addClient = new JButton("Add Client");
        mainPanel.add(addClient);
        final JLabel label = new JLabel();
        final JLabel label2 = new JLabel();
        addClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPaneAddClient newClient = new JOptionPaneAddClient();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        updateStock = new JButton("Update Stock");
        mainPanel.add(updateStock);


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
        String varName = (String)cb.getSelectedItem();
        //problem with this is that the value is the initial one, ie always 1
        System.out.println("the selected number of medication is: " + varName);
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



        //set the date
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        mainPanel.add(textField);

    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}

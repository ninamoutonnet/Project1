package main_screen;

import GUI.GradientPanel;
import db_handling.GetDB_clients;
import db_handling.GetDB_medicine;
import stock_handling.Checkout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Vector;

public class UI extends JPanel{

    GradientPanel mainPanel = new GradientPanel();
    //JButton View_stock;
    JButton addClient;
    //JButton updateStock;
    //JButton add_remove_med;
    JButton HandleS;

    public UI(){ //constructor

        mainPanel.setLayout(new GridLayout(4, 4));

        GetDB_medicine DB = new GetDB_medicine(1);
        GetDB_clients clientDB = new GetDB_clients();

        //fill in the top of the grid layout with empty jpanels, will fit the logo in there

        JPanel border1 = new JPanel();
        border1.setOpaque(false); // make the panel transparent
        mainPanel.add(border1);
        JPanel border2 = new JPanel();
        border2.setOpaque(false);
        mainPanel.add(border2);
        JPanel border3 = new JPanel();
        border3.setOpaque(false);
        mainPanel.add(border3);


        Vector<String> branch = new Vector<String>();
        // add a condition for the prices if one of these is selected
        branch.addElement("Green Park");
        branch.addElement("Paddington");
        branch.addElement("East End");
        final JComboBox<String> branches = new JComboBox<String>(branch);
        branches.setVisible(true);
        mainPanel.add(branches);


        Vector<String> cat = new Vector<String>();
        // getting info from the DB & storing it into vector
        int size = DB.getCategory().size();
        for(int i = 0; i< size; i++){
            cat.addElement(DB.getCategory().get(i));
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        cat.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        set.addAll(cat);
        cat.clear();
        cat.addAll(set);
        cat.add(0,"Select a medicine category"); //ad it after sorting alphabetically!
        final JComboBox<String> categories = new JComboBox<String>(cat);
        categories.setVisible(true);

        //problem with this is that the value is the initial one, ie always 1
       // System.out.println("the selected type of medication is: " + var);
        mainPanel.add(categories);

        //use this combo box to see the list of possible medicine to select,
        //ideally it would go through the heroku db and select all the names of the available medicine --> will do that soon

        Vector<String> medname = new Vector<String>();
        //medname.addElement("Select a medicine brand");
        for(int i = 0; i< size; i++){
            medname.addElement(DB.getBrand().get(i));
        }
        //sorts the array list and then converting it to a linkedhashset removes the duplicates
        medname.sort(String::compareToIgnoreCase);
        LinkedHashSet<String> set2 = new LinkedHashSet<String>();
        set2.addAll(medname);
        medname.clear();
        medname.addAll(set2);
        medname.add(0,"Select a medicine brand"); //ad it after sorting alphabetically!
        final JComboBox<String> med = new JComboBox<String>(medname);
        med.setVisible(true);
        mainPanel.add(med);


        //set the combo box - should we make sure that it only proposes available quantities?
        Vector<String> choices = new Vector<String>();
        choices.addElement("Select amount");
        choices.addElement("1");
        choices.addElement("2");
        choices.addElement("3");
        choices.addElement("4");
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setVisible(true);
        //problem with this is that the value is the initial one, ie always 1
        //System.out.println("the selected number of medication is: " + varName);
        mainPanel.add(cb);

        //set the combo box
        //when using the servlet, we have to go through the db and names
        int sizeName = clientDB.getFirstName().size();
        Vector<String> fullName = new Vector<>();
        for(int i=0; i<sizeName; i++){
            fullName.add(clientDB.getFirstName().get(i) +" "+ clientDB.getLastName().get(i));
        }
        fullName.add(0, "Select a customer");
        final JComboBox<String> names = new JComboBox<String>(fullName);
        names.setVisible(true);
        mainPanel.add(names);

        //set the date
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        // mainPanel.add(textField); //not adding it for now


        //Handling the stock
        HandleS = new JButton("Stock Handling");
        mainPanel.add(HandleS);
        HandleS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HandlingStock hs = new HandlingStock();
            }
        });

        // add a client
        addClient = new JButton("Add Client");
        mainPanel.add(addClient);
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


        //include bottom panel with nothing!
        JPanel border5 = new JPanel();
        border5.setOpaque(false); // make the panel transparent
        mainPanel.add(border5);

        //
        JButton checkout = new JButton("Checkout");
        Color color6 = new Color(102,204,0);
        checkout.setBackground(color6);
        checkout.setOpaque(true);
        checkout.setBorderPainted(true); // if set to false the whole button gets colored
        mainPanel.add(checkout);
        checkout.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Checkout successful", " " , JOptionPane.PLAIN_MESSAGE);
            String ChosenCat = (String)categories.getSelectedItem();
            String ChosenBrand = (String)med.getSelectedItem();
            String ChosenAmount = (String)cb.getSelectedItem();
            String ChosenClient = (String)names.getSelectedItem();
            //int Branch = (int)branches.getSelectedItem();
            Checkout co = new Checkout(ChosenCat, ChosenBrand, ChosenAmount,ChosenClient, 1 );
            System.out.println("category: " + ChosenCat + " Brand " + ChosenBrand + " Amount " + ChosenAmount);
        }
        });


        JPanel border6 = new JPanel();
        border6.setOpaque(false);
        mainPanel.add(border6);
        JPanel border7 = new JPanel();
        border7.setOpaque(false);
        mainPanel.add(border7);
        JPanel border8 = new JPanel();
        border8.setOpaque(false);
        mainPanel.add(border8);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}


package main_screen;

import GUI.GradientPanel;
import GUI.LogoPanel;
import GUI.dummyFrame;
import db_handling.GetDB_clients;
import db_handling.GetDB_medicine;
import stock_handling.Checkout;
import stock_handling.RemoveMed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Vector;

public class UI extends JPanel{

    GradientPanel mainPanel = new GradientPanel();
    private JPanel logoPanel;
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
        String path = "logophab.png";
        logoPanel=new LogoPanel(path);
        logoPanel.setOpaque(false);
        mainPanel.add(logoPanel);
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

        JPanel bordera = new JPanel();
        bordera.setOpaque(false); // make the panel transparent
        mainPanel.add(bordera);
        JPanel borderb = new JPanel();
        borderb.setOpaque(false);
        mainPanel.add(borderb);
        JPanel borderc = new JPanel();
        borderc.setOpaque(false);
        mainPanel.add(borderc);
        JPanel borderd = new JPanel();
        borderd.setOpaque(false);
        mainPanel.add(borderd);

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

        // register a new purhase
        JButton checkout = new JButton("Make a purchase");
        Color color6 = new Color(102,204,0);
        checkout.setBackground(color6);
        checkout.setOpaque(true);
        checkout.setBorderPainted(true); // if set to false the whole button gets colored
        mainPanel.add(checkout);

        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String branch = branches.getSelectedItem().toString(); //get the branch selected
                Checkout ch = new Checkout(branch); // pas in the branch name as argument

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

        //from https://www.flaticon.com/packs/medicaments-14?word=pharmacy
        String path2 = "nobackground_image.png";
        logoPanel=new LogoPanel(path2);
        logoPanel.setOpaque(false);
        mainPanel.add(logoPanel);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}

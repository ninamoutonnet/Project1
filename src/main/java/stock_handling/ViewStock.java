package stock_handling;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GUI.dummyFrame;
import db_handling.GetDB_medicine;

import javax.swing.*;

// show the current stock of each medicine to the pharmacist in form of progress bars

public class ViewStock {

    public ViewStock() {

        Integer branchNB =0;

        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        JPanel myPanel = new JPanel();
        //JScrollPane scroll = new JScrollPane(myPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myPanel.setLocation(5,2);
        myPanel.setLayout(new GridLayout(200, 150));

        // which branch
        JPanel aPanel = new JPanel();
        String[] arrayBranches = {"East End", "Green Park", "Paddington"};
        JComboBox<String> comboBoxBranch = new JComboBox<>(arrayBranches);
        comboBoxBranch.setVisible(true);
        aPanel.add(comboBoxBranch);

        int result = JOptionPane.showConfirmDialog(frmOpt, aPanel,"Select the branch",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(result==0) {
            String branch = comboBoxBranch.getSelectedItem().toString();

            if(branch.equalsIgnoreCase("Paddington")) branchNB =2;
            if(branch.equalsIgnoreCase("Green Park")) branchNB =1;
            if(branch.equalsIgnoreCase("East End")) branchNB =3;
            //whichBranch = Integer.parseInt(branch);
            System.out.println("the branch is " + branchNB );
        }

        GetDB_medicine info = new GetDB_medicine(branchNB);

        int size = info.getCurrentStock().size();

        for (int i = 0; i < size; i++) {
            String current = info.getCurrentStock().get(i); // current value in stock
            String original = info.getFullStock().get(i); // number when full stock
            // converting the strings to numbers
            int number1 = Integer.parseInt(current);
            int number2 = Integer.parseInt(original);
            if(number2 != 0){
            double percent = number1*100/number2;
            //System.out.println("the percentage is " + percent);
            String s = info.getBrand().get(i);
            String s2 = info.getAmount().get(i);
                //System.out.println("the brand is " + s + " - " + s2);
                JPanel newPanel = new JPanel();
                myPanel.setLayout(new GridLayout(20, 20));
                newPanel.add(new JLabel(s + " - " + s2));
                JProgressBar p = new JProgressBar(0, 100);
                p.setStringPainted(true);
                p.setValue((int)percent);
                newPanel.add(p);
                myPanel.add(newPanel);
            }
        }

        JButton button1 = new JButton("Send restock request");
        myPanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //automatically send emails to the suppliers
                // the pharmacist should receive an email that he needs to approve
                dummyFrame df = new dummyFrame();
                JFrame f = df.dummyFrameConstruction();
                JPanel mp = new JPanel();
                JTextArea text = new JTextArea("Restock request sent: check inbox for confirmation");
                mp.add(text);
                mp.setLocation(5,2);
                mp.setLayout(new GridLayout(1, 2));
                int r1 = JOptionPane.showConfirmDialog(f, mp,
                        "Update Stock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        });

        int res = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Current stock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    }

}

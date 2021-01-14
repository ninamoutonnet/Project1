package stock_handling;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GUI.dummyFrame;
import db_handling.GetDB_medicine;

import javax.swing.*;


public class ViewStock {

    public ViewStock() {

        dummyFrame df = new dummyFrame();
        JFrame frmOpt = df.dummyFrameConstruction();

        JPanel myPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(myPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myPanel.setLocation(5,2);
        myPanel.setLayout(new GridLayout(200, 150));


        GetDB_medicine info = new GetDB_medicine();

        int size = info.getCurrentStock().size();

        for (int i = 0; i < size; i++) {
            String current = info.getCurrentStock().get(i); // current value in stock
            String original = info.getFullStock().get(i); // number when full stock
            // converting the strings to numbers
            int number1 = Integer.parseInt(current);
            int number2 = Integer.parseInt(original);
            if(number2 != 0){
            double percent = number1*100/number2;
            System.out.println("the percentage is " + percent);
            String s = info.getBrand().get(i);
            String s2 = info.getAmount().get(i);
                System.out.println("the brand is " + s + " - " + s2);
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


        JButton button1 = new JButton("Restock medicines running low");
        myPanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //automatically send emails to the suppliers
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

        int result2 = JOptionPane.showConfirmDialog(frmOpt, myPanel,
                "Update Stock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    }
}

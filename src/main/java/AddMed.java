import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddMed {

    public AddMed() {
        JFrame fr = new JFrame("Add a new medicine");  //dummy JFrame to ensure that the window popping up is on top of anything else
        fr.setVisible(true);
        fr.setLocation(100,100);
        fr.setAlwaysOnTop(true);

        JTextField brand = new JTextField(10);
        JTextField name = new JTextField(13);
        JTextField qty = new JTextField(13);
        JTextField sprice = new JTextField(3);
        JTextField pprice = new JTextField(6);
        JTextField fullstock = new JTextField(6);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(9, 2));
        myPanel.add(new JLabel("Medicine Brand:"));
        myPanel.add(brand);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Medicine Name:"));
        myPanel.add(name);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Quantity:"));
        myPanel.add(qty);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Sale price :"));
        myPanel.add(sprice);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Purchase price:"));
        myPanel.add(pprice);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Full stock quantity:"));
        myPanel.add(fullstock);

        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Is it a limited medication? "));
        String[] choices = {"Yes", "No"};
        final JComboBox<String> limited = new JComboBox<String>(choices);
        limited.setVisible(true);
        myPanel.add(limited);

        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Description "));
        String[] choices2 = {"Relief","OTHER" ,"Tabs","Antiseptic","Hand Sanitizer","Plasters","Liquid","First Defense", "Night Nurse","Max","Standard","Day and Night", "Mucus relief","4 flu","Vaporub","Moisturising cream","Exfoliating cleanser","Skin cream", "Skin relief cream","Face Scrub","Psiorasis cream","Repair and Restore","Eczema cream","Meltlets","Express","Max Strengh","Headache", "Extra", "Triple action", "Original","Soluble","Blackcurrant","Lemon","Chewable","Advance"};
        //this class of combo box makes everything sorted alphabetically.
        SortedComboBoxModel<String> model = new SortedComboBoxModel<String>(choices2);
        JComboBox<String> comboBox = new JComboBox<String>( model );
        comboBox.setVisible(true);
        myPanel.add(comboBox);

        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Category "));


        /*

        HERE WE WILL TAKE THE DB FROM HEROKU, USE ALL THE DESCRIPTION TO PUT THEM IN AN ARRAY OF STRING.
        THIS WILL THEN BE FED INTO A SORTING ALGORITHM AND USED FOR THE DROP DOWN MENU
        RIGHT NOW I JUST TYPED THE NAMES MYSELF

         */



        String[] choices3 = {"Allergy","OTHER" ,"First Aid","Cold and flu","Skincare","Headache and pain relief","Digestion"};
        SortedComboBoxModel<String> model2 = new SortedComboBoxModel<String>(choices3);
        JComboBox<String> comboBox2 = new JComboBox<String>( model2 );
        comboBox2.setVisible(true);
        myPanel.add(comboBox2);

        int result = JOptionPane.showConfirmDialog(fr, myPanel,
                "Please Enter the medicine details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // for now -> print text but later us this loop to get data
          /*  System.out.println("Medicine brand: " + brand.getText());
            System.out.println("Medicine name: " + name.getText());
            System.out.println("Quantity: " + name.getText());
            System.out.println("Sale price: " + sprice.getText());
            System.out.println("Purchase price : " + pprice.getText());
            System.out.println("Full stock is : " + fullstock.getText());
            System.out.println("Limitation : " + limited.getSelectedItem());
            System.out.println("Description : " + comboBox.getSelectedItem());
            System.out.println("Category : " + comboBox2.getSelectedItem());*/

            if(comboBox.getSelectedItem()=="OTHER") { //add a new element to the description

                JFrame addDescription = new JFrame("Add a new description");
                addDescription.setVisible(true);
                addDescription.setLocation(100,100);
                addDescription.setAlwaysOnTop(true);
                JPanel myPanel2 = new JPanel();
                //addDescription.add(myPanel2);
                myPanel2.add(new JLabel("Description: "));
                JTextField newDescription = new JTextField(6);
                myPanel2.add(newDescription);
                int result2 = JOptionPane.showConfirmDialog(addDescription, myPanel2,
                        "Please Enter the description ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                //here add the new description to the DB!!!

                //HERE WE ADD THE NEW DESCRIPTION TO THE DB!!!
                if(result2 ==JOptionPane.OK_OPTION){
                    addDescription.dispose();
                }

            }

        }
        fr.dispose(); //discard of the dummy jframe

    }
}


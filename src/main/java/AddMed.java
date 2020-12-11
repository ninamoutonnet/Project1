import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AddMed {
    JFrame frame;
    JPanel panel;
    JButton Add;
    JButton Cancel;

    public AddMed() {
        frame = new JFrame("Add a medicine");
        frame.setSize(300, 300);

        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new GridLayout(5, 5));

        Cancel = new JButton("Cancel");
        Add = new JButton("Add");
    }

    public void Show(){
        frame.setVisible(true);
    }

    public void PopulatePanel(){
        ArrayList<String> Info = new ArrayList<String>();
        ArrayList<TextField> entered = new ArrayList<TextField>();
        Info.add("Medicine Name:");
        Info.add("Product Code:");
        Info.add("Vendor:");
        Info.add("Price:");
        for(int i = 0; i<Info.size(); i++) {
            Label label = new Label(Info.get(i));
            panel.add(label);
            TextField textField = new TextField();
            entered.add(textField);
            panel.add(textField);
        }

        panel.add(Cancel);
        panel.add(Add);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                JOptionPane.showMessageDialog(frame,
                        "Medicine was successfully added",
                        "Message",
                        JOptionPane.PLAIN_MESSAGE);
                for(int i = 0; i<Info.size(); i++){
                    System.out.println((entered.get(i)).getText());
                }
            }
        });
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

}


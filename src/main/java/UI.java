import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
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

        GetDB DB = new GetDB();

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
        cat.addElement("Select a medicine category");
        /*cat.addElement("Cold and flu");
        cat.addElement("Skincare");
        cat.addElement("Headaches and pain relief");
        cat.addElement("Digestion");
        cat.addElement("Allergy");
        cat.addElement("First aid");*/
        // getting info from the DB & storing it into vector
        int size = DB.getCategory().size();
        for(int i = 0; i< size; i++){
            cat.addElement(DB.getCategory().get(i));
        }
        final JComboBox<String> categories = new JComboBox<String>(cat);
        categories.setVisible(true);
        String var = (String)categories.getSelectedItem();
        //problem with this is that the value is the initial one, ie always 1
       // System.out.println("the selected type of medication is: " + var);
        mainPanel.add(categories);

        //use this combo box to see the list of possible medicine to select,
        //ideally it would go through the heroku db and select all the names of the available medicine --> will do that soon

        Vector<String> medname = new Vector<String>();
        medname.addElement("Select a medicine brand");
        /*medname.addElement("Vicks Vaporub 100 g");
        medname.addElement("Vicks First Defence 15 ml");
        medname.addElement("GSK Night Nurse 160 ml");
        medname.addElement("Sudafed Day and Night 16 caps");*/
        for(int i = 0; i< size; i++){
            medname.addElement(DB.getBrand().get(i));
        }
        final JComboBox<String> med = new JComboBox<String>(medname);
        med.setVisible(true);
        mainPanel.add(med);

        //set the combo box
        Vector<String> choices = new Vector<String>();
        choices.addElement("Select amount");
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
        name.addElement("Select a customer");
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
            JOptionPane.showMessageDialog(null, "Checkout successful", " " , JOptionPane.PLAIN_MESSAGE);
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

    /*
    public static String[] makeGetRequest() {
        try {
            String message = "SELECT brand FROM products;";
            byte[] body = message.getBytes(StandardCharsets.UTF_8);

            URL myURL = new URL("https://projectservlet.herokuapp.com/DBaccess"); // is the request target?? - probably yes
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length",Integer.toString(body.length));
            conn.setDoOutput(true);

            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body, 0, body.length);
            }
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream(), "utf-8"));

            String inputLine;
            // Read the body of the response
            while ((inputLine = bufferedReader.readLine()) != null) {
                // add elements one by one to array of strings
                System.out.println(inputLine);
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
        }

        String[] stringarray = new String[3];

        return stringarray;
    }
     */
}


package GUI;

import javax.swing.*;


/*this class does the following*/
//creates an object that has a function isAnInt. it takes a string input and the description of what the input corresponds to.
//for instance, if we pass "43" as input it can be described as "Sale quantity".
//you then check that the input is an integer, if it is not you have a pop up message saying the input is not an int and you return a false.
//if it is an int, you return a true value.

//The function isADouble works similarly.

public class checkTheInput {

    public checkTheInput(){
        //constructor
    }

    public boolean isAnInt(String input, String description){
        boolean isAnInt = true;
        Integer CS=0;
        try{
            CS = Integer.parseInt(input);
        }catch (Exception e){
            isAnInt = false; //set to false to signal an issue
            //construct a dummy frame using the class! since it is a redundant operation
            dummyFrame df = new dummyFrame();
            JFrame dummyF = df.dummyFrameConstruction();

            JPanel error = new JPanel();
            error.setVisible(true);
            JLabel errorMsg = new JLabel("Error, ");
            JLabel errorMsg2 = new JLabel(description);
            JLabel errorMsg3 = new JLabel(" is not an integer");
            error.add(errorMsg);
            error.add(errorMsg2);
            error.add(errorMsg3);
            int error1 = JOptionPane.showConfirmDialog(dummyF, error,
                    "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(error1==0){//only enter the loop when ok pressed
                df.dummyFrameDispose(dummyF);
            }
        }
        return isAnInt;
    }

    public boolean isADouble(String input, String description){
        boolean isADouble = true;
        Double CS=0.0;
        try{
            CS = Double.parseDouble(input);
        }catch (Exception e){
            isADouble = false; //set to false to signal an issue
            //construct a dummy frame using the class! since it is a redundant operation
            dummyFrame df = new dummyFrame();
            JFrame dummyF = df.dummyFrameConstruction();

            JPanel error = new JPanel();
            error.setVisible(true);
            JLabel errorMsg = new JLabel("Error, ");
            JLabel errorMsg2 = new JLabel(description);
            JLabel errorMsg3 = new JLabel(" is not a double");
            error.add(errorMsg);
            error.add(errorMsg2);
            error.add(errorMsg3);
            int error1 = JOptionPane.showConfirmDialog(dummyF, error,
                    "Error", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(error1==0){//only enter the loop when ok pressed
                df.dummyFrameDispose(dummyF);
            }
        }
        return isADouble;
    }
}

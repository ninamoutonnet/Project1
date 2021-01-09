package GUI;

import javax.swing.*;

public class dummyFrame {

    public dummyFrame(){

    }

    public JFrame dummyFrameConstruction(){
        JFrame frmOpt;//dummy JFrame
        frmOpt = new JFrame();
        frmOpt.setVisible(false);
        frmOpt.setLocation(100, 100);
        frmOpt.setAlwaysOnTop(true);
        return frmOpt;
    }

    public void dummyFrameDispose(JFrame jf){
        jf.dispose();
    }
}


import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;

public class Case extends JButton  implements ActionListener {
    private boolean occupied = false;
    private static int size = 10;

    public Case() {
        addActionListener(this);
        setBackground(Color.white);
        //addKeyListener(this);
        setFocusable(false) ;
    }

    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
        if(occupied) {
            setBackground(Color.black);
        }else {
            setBackground(Color.white);
        }
    }


    public static int getS() {
        return size;
    }
    public static void setSize(int size) {
        Case.size = size;
    }

    public void actionPerformed(ActionEvent e)
    {
        setOccupied(!occupied);
    }

}

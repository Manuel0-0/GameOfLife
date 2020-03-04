
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class GameWindow extends JFrame implements KeyListener{
    private Grid g;
    private int delay = 1000;
    private int delayMod = 100;
    private int maxDelay = 3000;
    private int minDelay = 100;
    private Timer timer;

    public GameWindow(int l, int h) {
        g = new Grid(l,h);
        setContentPane(g);
        setVisible(true);
        setTitle("game of life");
        setSize(g.getPreferredSize());
        addKeyListener(this);
        requestFocusInWindow();

        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                g.step();
            }
        });
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER :
                if(timer.isRunning()){
                    timer.stop();
                }else {
                    timer.start();
                }
                break;
            case KeyEvent.VK_UP:
                if(delay > minDelay){
                    delay -= delayMod;
                    timer.setDelay(delay);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(delay < maxDelay){
                    delay += delayMod;
                    timer.setDelay(delay);
                }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

        // TODO Auto-generated method stub
    }



}

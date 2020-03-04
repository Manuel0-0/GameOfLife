
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameWindow extends JFrame implements KeyListener{
    private Grid g;
    private int delay = 1000;
    private int delayMod = 20;
    private int maxDelay = 2000;
    private int minDelay = 20;
    private Timer timer;
    private JButton bStart;
    private JSlider sVitesse;
    private JLabel lGen;
    private int nbGen;


    public GameWindow(int l, int h) {
        g = new Grid(l,h);
        nbGen = 0;
        this.add(g);
        this.add(controlPanel(), BorderLayout.PAGE_END);
        setVisible(true);
        setTitle("game of life");
        setSize(g.getPreferredSize());
        addKeyListener(this);
        requestFocusInWindow();

        timer = new Timer(delay,
                new ActionListener() {
                    public void actionPerformed(ActionEvent ev){
                        g.step();
                        nbGen++;
                        lGen.setText(""+nbGen);
                    }
                });
    }

    private JPanel controlPanel(){
        JPanel cp = new JPanel();
        cp.setLayout(new GridLayout(2,2));
        JButton start = new JButton("start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(timer.isRunning()){
                    timer.stop();
                    start.setLabel("start");
                }else{
                    timer.start();
                    start.setLabel("stop");
                }
            }
        });
        start.setFocusable(false);
        cp.add(start);


        JSlider vitesse = new JSlider(JSlider.HORIZONTAL, -maxDelay, -minDelay, -delay);
        vitesse.setFocusable(false);
        vitesse.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source  = (JSlider)e.getSource();
                delay =-(int)source.getValue();
                timer.setDelay(delay);
            }
        });
        cp.add(vitesse);
        bStart = start;
        sVitesse = vitesse;

        JButton configs = new JButton("random");
        configs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                g.setRandom();
            }
        });
        configs.setFocusable(false);
        cp.add(configs);

        lGen = new JLabel("0");
        cp.add(lGen);

        return cp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER :
                if(timer.isRunning()){
                    timer.stop();
                    bStart.setLabel("start");
                }else{
                    timer.start();
                    bStart.setLabel("stop");
                }
                break;
            case KeyEvent.VK_UP:
                if(delay > minDelay){
                    timer.setDelay(delay -= delayMod);
                    sVitesse.setValue(-delay);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(delay < maxDelay){
                    timer.setDelay(delay += delayMod);
                    sVitesse.setValue(-delay);
                }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Grid getG() {
        return g;
    }

    public Timer getTimer() {
        return timer;
    }
}

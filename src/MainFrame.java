import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MainFrame extends JFrame{
    private JLabel charcter = new JLabel();
    private ImageIcon icon =new ImageIcon("Action/default.png");
    private String icon_name;
    private Timer[] timer=new Timer[10];
    private int objx=0,objy=720-185,objw=106,objh=115,v = 60,t=1;
    private int objbox;
    private double speed=0;
    private int key;
    private boolean jumpflag=true,falldowncheck,rightdirection=false,jumpdirection;
    MainFrame(){
        game();
        timer[0].start();
    }
    private void game(){
        this.setBounds(300,100,1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        charcter.setIcon(icon);
        charcter.setBounds(objx,objy,objw,objh);
        this.add(charcter);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_RIGHT){
                    objx += 5;
                    charcter.setLocation(objx,objy);
                }
                if(e.getKeyCode()==KeyEvent.VK_X && jumpflag){
                    icon =new ImageIcon("Action/jump.png");
                    objw=icon.getIconWidth();
                    objh=icon.getIconHeight();
                    charcter.setIcon(icon);
                    objbox=objy;
                    jumpflag=false;
                    timer[3].start();
                    timer[0].stop();
                    timer[1].stop();
                    while (rightdirection){
                        run();
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_C ){
                    if(jumpflag) {
                        icon = new ImageIcon("Action/shoot.png");
                        objw = icon.getIconWidth();
                        objh = icon.getIconHeight();
                        charcter.setIcon(icon);
                    }
                    else{
                        icon = new ImageIcon("Action/jumpshoot.png");
                        objw = icon.getIconWidth();
                        objh = icon.getIconHeight();
                        charcter.setIcon(icon);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode()==KeyEvent.VK_X){
//                    timer[3].stop();
//                    timer[4].start();
//                }
            }
        });
        /////////////////////////closeeye//////////////////////////////
        timer[0] = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(charcter.getIcon()==icon) {
                    icon = new ImageIcon("Action/closeeye.png");
                    charcter.setIcon(icon);
                    timer[1].start();
                }
            }
        });
        ////////////////////////Default//////////////////////////////
        timer[1] =new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon =new ImageIcon("Action/default.png");
                charcter.setIcon(icon);
                timer[1].stop();
            }
        });
        ////////////////////////jump///////////////////////
        timer[3] = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                v = v-10*t;
                if(v<=0 && v>-60) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                }else if(v <= -60){
                    timer[3].stop();
                    timer[1].start();
                    timer[0].start();
                    jumpflag=true;
                    v = 60 ;
                }else if(v>0){
                    objy -= v* t +(1 / 2) * (-10 * t * t);
                }

                charcter.setBounds(objx,objy,objw,objh);
                charcter.setLocation(objx,objy);

            }
        });
        /////////////////////////jumpfalldown/////////////////////////////
        timer[4] =new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                charcter.setBounds(objx,objy,objw,objh);
                charcter.setLocation(objx,objy);
                if(objy==720-185){
                    timer[4].stop();
                    timer[1].start();
                    timer[0].start();
                    jumpflag=true;
                    t=0;
                }
            }
        });
        ////////////////////////walk/////////////////////////////////////////

    }
    private void run(){
        if(key==KeyEvent.VK_RIGHT){
            objx += 5;
        }
    }
}
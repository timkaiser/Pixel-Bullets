package GUIObjekte;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Spielerauswahlpanel extends JLabel implements GUIObjekt, ActionListener{

    private ArrayList<Textfeld> textfelder;
    private Knopf add, remove;

    private double x,y,b,h;
    private Font font;

    private boolean printBorder;
    public Spielerauswahlpanel(double x, double y, double b, double h,Font font, Container c){
        super("");
        setBounds(100,100,100,100);
        setForeground(Color.BLACK);
        setOpaque(false);
        setVisible(true);
        this.x=x;
        this.y=y;
        this.b=b;
        this.h=h;
        this.font=font;
        c.add(this);

        textfelder = new ArrayList<Textfeld>();
        textfelder.add(new Textfeld("Spieler 1",0.0,0.0,1,0.2,font,this));
        textfelder.add(new Textfeld("Spieler 2",0.0,0.2,1,0.2,font,this)); //0.625 0.875
        add = new Knopf("+",0.5,0.4,0.5,0.2,font, this);
        add.addActionListener(this);
        remove = new Knopf("-",0.0,0.4,0.5,0.2,font, this);
        remove.addActionListener(this);
        remove.setEnabled(false);
        skallieren(c.getWidth(),c.getHeight());  
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == add){
            if(textfelder.size() == 2){                
                textfelder.add(textfelder.size(),new Textfeld("Spieler 3",0.0,0.4,1,0.2,font,this));
                add.setPosition(0.5,0.6,0.5,0.2);
                remove.setPosition(0.0,0.6,0.5,0.2);
                remove.setEnabled(true);
                add.skallieren(getWidth(),getHeight());
                remove.skallieren(getWidth(),getHeight());
            }else if(textfelder.size() == 3){                
                textfelder.add(textfelder.size(),new Textfeld("Spieler 4",0.0,0.6,1,0.2,font,this));
                add.setPosition(0.5,0.8,0.5,0.2);
                add.setEnabled(false);
                remove.setPosition(0.0,0.8,0.5,0.2);
                add.skallieren(getWidth(),getHeight());
                remove.skallieren(getWidth(),getHeight());
            }
        }else if(e.getSource() == remove){
            if(textfelder.size() == 3){
                remove(textfelder.get(textfelder.size()-1));
                textfelder.remove(textfelder.size()-1);
                add.setPosition(0.5,0.4,0.5,0.2);
                remove.setPosition(0.0,0.4,0.5,0.2);
                remove.setEnabled(false);
                add.skallieren(getWidth(),getHeight());
                remove.skallieren(getWidth(),getHeight());
            }else if(textfelder.size() == 4){
                remove(textfelder.get(textfelder.size()-1));
                textfelder.remove(textfelder.size()-1);
                add.setPosition(0.5,0.6,0.5,0.2);
                add.setEnabled(true);
                remove.setPosition(0.0,0.6,0.5,0.2);
                add.skallieren(getWidth(),getHeight());
                remove.skallieren(getWidth(),getHeight());
            }
        }
        revalidate();
        repaint();
    }

    public void skallieren(int frameBreite, int frameHoehe){
        setBounds((int)(frameBreite*x),(int)(frameHoehe*y),(int)(frameBreite*b),(int)(frameHoehe*h));
        setFont(font.deriveFont((float)(frameHoehe*h/2.5)));

        add.skallieren(getWidth(),getHeight());
        remove.skallieren(getWidth(),getHeight());
        for(int i=0; i< textfelder.size();i++){
            textfelder.get(i).skallieren(getWidth(),getHeight());
        }
    }  

    public String[] getNamen(){
        String[] s = new String[textfelder.size()];
        for(int i=0; i< textfelder.size(); i++){
            s[i]=textfelder.get(i).getText();
        }
        return s;
    }

    public void printBorder(boolean b){
        printBorder = b;
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );
        g.setColor(Color.BLACK);
        if(printBorder)
            g.drawRect(0,0,getWidth(),getHeight());
    }
}

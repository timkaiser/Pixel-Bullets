package GUIObjekte;


import javax.swing.*;
import java.awt.*;

public class Textfeld extends JTextField implements GUIObjekt{
    private double x,y,b,h;
    private Font font;
    private boolean markiert=false;
    public Textfeld(String name, double x, double y, double b, double h, Font font, Container jc)    {
        super(name);
        setBounds(100,100,100,100);
        setForeground(Color.BLACK);
        
        setOpaque(false);
        setVisible(true);
        this.x=x;
        this.y=y;
        this.b=b;
        this.h=h;
        this.font=font;
        jc.add(this); 
        skallieren(jc.getWidth(),jc.getHeight());     
    }

    public void skallieren(int frameBreite, int frameHoehe){
        setBounds((int)(frameBreite*x),(int)(frameHoehe*y),(int)(frameBreite*b),(int)(frameHoehe*h));
        setFont(font.deriveFont((float)(frameHoehe*h/2.5)));
    }   

}

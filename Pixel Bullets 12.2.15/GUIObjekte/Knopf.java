package GUIObjekte;

import javax.swing.*;
import java.awt.*;

public class Knopf extends JButton implements GUIObjekt{
    private double x,y,b,h;
    private Font font;
    private boolean markiert=false;
    private boolean printBorder;
    public Knopf(String name, double x, double y, double b, double h, Container jc)    {
        this(name,x,y,b,h,new Font("Monospaced",Font.PLAIN,30),jc);
    }

    public Knopf(String name, double x, double y, double b, double h, Font font, Container jc)    {
        super(name);
        setBounds(100,100,100,100);
        setForeground(Color.BLACK);
        setContentAreaFilled(false);
        setBorderPainted(false);
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

    public void setPosition(double x, double y, double b, double h){
        this.x=x;
        this.y=y;
        this.b=b;
        this.h=h;
    }

    public void markieren(){
        setForeground(new Color(160, 30, 250));
        markiert=true;
    }

    public void entmarkieren(){
        setForeground(new Color(0, 0, 0));
        markiert=false;
    }

    public boolean istMarkiert(){
        return markiert;
    }

    public void printBorder(boolean b){
        printBorder = b;
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );
        g.setColor(Color.BLACK);
        if(printBorder)
            g.drawRect(0,0,getWidth()-1,getHeight()-1);
    }
}

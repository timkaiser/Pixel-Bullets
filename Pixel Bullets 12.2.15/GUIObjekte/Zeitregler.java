package GUIObjekte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Zeitregler extends JLabel implements GUIObjekt,ActionListener{
    private double x,y,b,h;
    protected int zaehler, min, max, schrittweite;
    private Font font;
    private Knopf plus, minus;
    protected Schriftzug label;
    
    public Zeitregler(double x, double y, double b, double h, Container jc)    {
        this(x,y,b,h,new Font("Monospaced",Font.PLAIN,30),jc);
    }

    public Zeitregler(double x, double y, double b, double h, Font font, Container jc)    {
        this(x,y,b,h,0,0,10000,1,font,jc);
    }

    public Zeitregler(double x, double y, double b, double h,int start, int min, int max, int schrittweite, Font font, Container jc)    {
        super("");
        zaehler = start;
        this.min = min;
        this.max = max;
        this.schrittweite = schrittweite;
        minus = new Knopf("<",0.0,0.1,0.3,0.8,font,this);
        minus.addActionListener(this);
        plus = new Knopf(">",0.8,0.1,0.3,0.8,font,this);     
        plus.addActionListener(this);
        label = new Schriftzug("",0.25,0.0,0.8,1,font,this);
        setBounds(100,100,100,100);
        setForeground(Color.BLACK);
        textAktualisieren();
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
        minus.skallieren(getWidth(),getHeight());
        plus.skallieren(getWidth(),getHeight());
        label.skallieren(getWidth(),getHeight());
    }   

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == minus){
            if(zaehler-schrittweite>=min){
                zaehler-=schrittweite;
            }else{
                zaehler=min;
            }
        }else 
        if(e.getSource() == plus){
            if(zaehler+schrittweite<=max){
                zaehler+=schrittweite;
            }else{
                zaehler=max;
            }
        }
        textAktualisieren();
    }
    
    public int getZaehler(){
        return zaehler;
    }
    
    public void textAktualisieren(){
        String s;
        if(zaehler%60<10){
            s=(zaehler/60)+":0"+(zaehler%60);
        }else{
            s=(zaehler/60)+":"+(zaehler%60);
        }
        label.setText(s);
    }
}

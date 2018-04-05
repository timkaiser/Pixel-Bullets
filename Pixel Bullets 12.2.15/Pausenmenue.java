import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import GUIObjekte.*;
public class Pausenmenue extends JPanel implements ActionListener{
    public static JFrame container;
    private static Knopf fortsetzen, optionen, zumHauptmenue, beenden, titel;
    private Font font;
    public Pausenmenue(){
        setBounds(0,0,800,600);
        setBackground(new Color(255,255,255));
        setLayout(null);
        container=Fenster.getFenster();;

        InputStream fin = this.getClass().getResourceAsStream("origa___.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        fortsetzen = new Knopf("Fortsetzen",0, 2.0/6.0, 1.0/1.0, 0.2,font,this);
        fortsetzen.addActionListener(this);

        optionen = new Knopf("",0, 4.0/6.0, 1.0/1.0, 0.2,font,this);
        optionen.addActionListener(this);
        optionen.setEnabled(false);

        zumHauptmenue = new Knopf("Zum Hauptmenue",0, 3.0/6.0, 1.0/1.0, 0.2,font,this);
        zumHauptmenue.addActionListener(this);

        beenden = new Knopf("Beenden",0, 5.0/6.0, 1.0/1.0, 0.2,font,this);
        beenden.addActionListener(this);

        titel=new Knopf("PAUSE", 0, 1.0/300.0, 1, 1.0/3.0, font, this);
        titel.setEnabled(false);

        setVisible(true);
    }

    public static void knoepfeEntfernen(){
        fortsetzen.setVisible(false);
        zumHauptmenue.setVisible(false);
        optionen.setVisible(false);
        beenden.setVisible(false);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == fortsetzen){
            Spielschleife.spielPausieren();
            Steuerung.setPauseAn(false);
            Fenster.pausenmenueAufrufen(false);
        }
        else if(e.getSource() == optionen){
            knoepfeEntfernen();
            Fenster.optionsmenueAufrufen();
        }
        else if(e.getSource() == zumHauptmenue){
            Fenster.hauptmenueAufrufen();
        }
        else if(e.getSource() == beenden){
            Optionsmenue.einstellungenSpeichern();
            System.exit(0);
        }
    }    

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );        
        setPreferredSize(null);
        fortsetzen.skallieren(getWidth(),getHeight());  
        optionen.skallieren(getWidth(), getHeight());
        zumHauptmenue.skallieren(getWidth(),getHeight());
        beenden.skallieren(getWidth(),getHeight());
        titel.skallieren(getWidth(), getHeight());
    }
}
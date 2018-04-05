import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import GUIObjekte.*;
public class Hauptmenue extends JPanel implements ActionListener{
    public static Fenster container;
    private static Knopf spielstart, optionen, credits, beenden, titel;
    private static int zeitZahl=2;
    private Font font;
    public Hauptmenue(Fenster container){
        setBounds(0,0,800,600);
        setBackground(Color.CYAN);
        setLayout(null);
        this.container=container;

        InputStream fin = this.getClass().getResourceAsStream("origa___.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        spielstart = new Knopf("Spielstart",0, 2.0/6.0, 1.0/1.0, 1.0/6.0,font,this);
        spielstart.addActionListener(this);

        optionen = new Knopf("Optionen",0, 3.0/6.0, 1.0/1.0, 1.0/6.0,font,this);
        optionen.addActionListener(this);

        credits = new Knopf("Credits",0, 4.0/6.0, 1.0/1.0, 1.0/6.0,font,this);
        credits.addActionListener(this);

        beenden = new Knopf("Beenden",0, 5.0/6.0, 1.0/1.0, 1.0/6.0,font,this);
        beenden.addActionListener(this);

        titel=new Knopf("Pixel Bullets", 0, 1.0/20.0, 1, 1.0/6.0, font, this);
        titel.setEnabled(false);

        setVisible(true);
    }

    public static void knoepfeEntfernen(){
        spielstart.setVisible(false);
        credits.setVisible(false);
        optionen.setVisible(false);
        beenden.setVisible(false);
        titel.setVisible(false);
    }

    public void actionPerformed(ActionEvent e){

        /*knoepfeEntfernen();
        container.remove(this);
        container.add(new Deathmatch().getEinstellungspanel(container));*/

        if(e.getSource() == spielstart){
            //Spiel starten
            if(Optionsmenue.musikAnAus()==true){
                Fenster.musikHalt();
                //Fenster.musikWeiter("Scabb Island Beach Fire Theme from Monkey Island 2.wav");
            }
            else{
                Fenster.musikHalt();
            }

            knoepfeEntfernen();
            container.remove(this);
            container.add(new Modusauswahlmenue(container));

        }
        else if(e.getSource() == optionen){
            container.remove(this);
            container.add(new Optionsmenue(container));           
            container.revalidate();
            container.repaint();
        }
        else if(e.getSource() == credits){
            container.remove(this);
            container.add(new Credits(container));
            container.revalidate();
            container.repaint();
        }
        else if(e.getSource() == beenden){
            Optionsmenue.einstellungenSpeichern();
            System.exit(0);
        }
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );        
        setPreferredSize(null);
        spielstart.skallieren(getWidth(),getHeight());
        optionen.skallieren(getWidth(),getHeight());
        credits.skallieren(getWidth(),getHeight());
        beenden.skallieren(getWidth(),getHeight());
        titel.skallieren(getWidth(), getHeight());
    }
}

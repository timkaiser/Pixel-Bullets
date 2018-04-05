import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import GUIObjekte.*;
public class Modusauswahlmenue extends JPanel implements ActionListener{
    public static Fenster container;
    private Textauswahlregler regler;
    private Knopf start,zurueck;
    private Font font;
    public Modusauswahlmenue(Fenster container){
        setBounds(0,0,800,600);
        setBackground(Color.CYAN);
        setLayout(null);
        this.container=container;

        InputStream fin = this.getClass().getResourceAsStream("origa___.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        start = new Knopf("Spielstart",0.5, 4.0/6.0, 1.0/2.0, 1.0/6.0,font,this);
        start.addActionListener(this);
        
        zurueck = new Knopf("Zurueck",0, 4.0/6.0, 1.0/2.0, 1.0/6.0,font,this);
        zurueck.addActionListener(this);
        
        regler = new Textauswahlregler(0.0, 2.0/6.0, 1.0/1.0, 1.0/6.0,font,this,"Deathmatch","Survival","GunGame");
        setVisible(true);

    }

    public static void knoepfeEntfernen(){
        //spielstart.setVisible(false);

    }

    public void actionPerformed(ActionEvent e){

        /*knoepfeEntfernen();
        container.remove(this);
        container.add(new Deathmatch().getEinstellungspanel(container));*/

        if(e.getSource() == start){
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
            if(regler.getAuswahlText() == "Deathmatch"){
                container.add(new Deathmatch().getEinstellungspanel(container));
            }
            else if(regler.getAuswahlText() == "Survival"){
                container.add(new Survival(30000).getEinstellungspanel(container));
            }
            else if(regler.getAuswahlText() == "GunGame"){
                container.add(new GunGame().getEinstellungspanel(container));
            }
        }
        else if(e.getSource() == zurueck){
            container.remove(this);
            container.add(new Hauptmenue(container));
        }
        
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );        
        setPreferredSize(null);
        start.skallieren(this.getWidth(), this.getHeight());
        zurueck.skallieren(this.getWidth(), this.getHeight());
        regler.skallieren(this.getWidth(), this.getHeight());
        //spielstart.skallieren(getWidth(),getHeight());
    }
}

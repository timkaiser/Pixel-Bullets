import GUIObjekte.*;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
public class Moduseinstellungspanel extends JPanel implements ActionListener{
    private Spielerauswahlpanel spielerauswahlpanel;
    private Textauswahlregler maps, bluteinstellungen;
    private Knopf bestaetigen,zurueck;
    private Zeitregler zeit;
    private Auswahlregler leben, kills;
    private Schriftzug lebenS, killsS, zeitS;
    private Spielmodus modus;
    private Fenster container;

    public Moduseinstellungspanel(Spielmodus modus, Fenster container)  {
        super();
        setBounds(0,0,800,600);
        setBackground(Color.CYAN);
        setLayout(null);

        this.modus = modus;
        this.container = container;

        Font font = null;
        InputStream fin = this.getClass().getResourceAsStream("origa___.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        spielerauswahlpanel = new Spielerauswahlpanel(0.05,0.05,0.4,0.7,font,this);

        maps = new Textauswahlregler(0.5,0.05,0.5,0.15,font,this,"castle","moon","western");
        bluteinstellungen = new Textauswahlregler(0.4,0.65,0.6,0.12,font,this,"Blut","Kein Blut","Paintball");
        
        zurueck = new Knopf("ZURUECK",0.1,0.83,0.3,0.1,font,this);
        zurueck.printBorder(true);
        zurueck.addActionListener(this);

        bestaetigen = new Knopf("SPIELEN",0.6,0.83,0.3,0.1,font,this);
        bestaetigen.printBorder(true);
        bestaetigen.addActionListener(this);

        killsS = new Schriftzug("KILLS:",0.5,0.20,0.25,0.15,font,this);
        kills = new Auswahlregler(0.72,0.20,0.25,0.15,10,0,100,1,font,this);
        killsS.setVisible(false);
        kills.setVisible(false);

        lebenS = new Schriftzug("LEBEN:",0.5,0.35,0.25,0.15,font,this);
        leben = new Auswahlregler(0.72,0.35,0.25,0.15,10,1,100,1,font,this);
        lebenS.setVisible(false);
        leben.setVisible(false);

        zeitS = new Schriftzug("DAUER:",0.5,0.50,0.25,0.15,font,this);
        zeit = new Zeitregler(0.72,0.50,0.25,0.15,180,0,1200,10,font,this);
        zeitS.setVisible(false);
        zeit.setVisible(false);

        setVisible(true);
        repaint();
    } 

    public void addLebensAuswahl(){
        lebenS.setVisible(true);
        leben.setVisible(true);
    }

    public void addKillsAuswahl(){
        killsS.setVisible(true);
        kills.setVisible(true);
    }

    public void addZeitAuswahl(){
        zeitS.setVisible(true);
        zeit.setVisible(true);
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );        
        setPreferredSize(null);
        spielerauswahlpanel.skallieren(getWidth(),getHeight());
        bluteinstellungen.skallieren(getWidth(),getHeight());
        maps.skallieren(getWidth(),getHeight());
        bestaetigen.skallieren(getWidth(),getHeight());
        zurueck.skallieren(getWidth(),getHeight());
        zeit.skallieren(getWidth(),getHeight());
        leben.skallieren(getWidth(),getHeight());
        kills.skallieren(getWidth(),getHeight());
        lebenS.skallieren(getWidth(),getHeight());
        killsS.skallieren(getWidth(),getHeight());
        zeitS.skallieren(getWidth(),getHeight());
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==zurueck){
            allesUnsichtbar();
            container.remove(this);
            container.add(new Hauptmenue(container));
        }else if(e.getSource()==bestaetigen){
            Spielschleife.fortsetzen();
            Spielschleife.loopBeginnen();
            Fenster.setPauseAn(false);
            container.remove(this);
            modus.setKills(kills.getZaehler());
            modus.setZeit(zeit.getZaehler());
            modus.setLeben(leben.getZaehler());
            Hintergrund.setBlutmodus(bluteinstellungen.getAuswahlText());
            container.add(new Spielfeld(maps.getAuswahlText(),modus,spielerauswahlpanel.getNamen()));
            container.revalidate();
            container.repaint(); 
        }
    }

    public void setMaps(String... mapsP){
        maps.setText(mapsP);
    }

    public void allesUnsichtbar(){
        spielerauswahlpanel.setVisible(false);
        bluteinstellungen.setVisible(false);
        maps.setVisible(false);
        bestaetigen.setVisible(false);
        zurueck.setVisible(false);
        zeit.setVisible(false);
        leben.setVisible(false);
        kills.setVisible(false);
        lebenS.setVisible(false);
        killsS.setVisible(false);
        zeitS.setVisible(false);
    }
}
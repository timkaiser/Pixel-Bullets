import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import GUIObjekte.*;
public class Credits extends Hauptmenue implements ActionListener{
    private Knopf zurueck;
    private Schriftzug christoph, tim, mehmet;
    private Font font;
    public Credits(Fenster container){
        super(container);
        setBackground(Color.GREEN);
        setLayout(null);
        setFocusable(true);

        InputStream fin = this.getClass().getResourceAsStream("origa___.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        zurueck=new Knopf("Zurueck", 0, 5.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
        zurueck.addActionListener(this);
        christoph = new Schriftzug("Christo", 0.4, 2.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
        tim = new Schriftzug("MrToem", 0.4, 3.0/6.0, 1.0/1.0, 1.0/6.0, font, this);
        mehmet = new Schriftzug("MrMG", 0.4, 4.0/6.0, 1.0/1.0, 1.0/6.0 , font, this);

        setVisible(true);
        Hauptmenue.knoepfeEntfernen();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==zurueck){
            container.remove(this);
            container.add(new Hauptmenue(container));
            container.revalidate();
            container.repaint();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setPreferredSize(null);
        christoph.skallieren(getWidth(), getHeight());
        tim.skallieren(getWidth(), getHeight());
        mehmet.skallieren(getWidth(), getHeight());
        zurueck.skallieren(getWidth(), getHeight());
    }

}
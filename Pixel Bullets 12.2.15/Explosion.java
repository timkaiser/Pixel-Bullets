import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
public class Explosion extends Projektil{
    private int lebensZeit = 1000;
    int radius, radiusZeichnen, timer, mitte;
    private int[][] gitter, gitterTmp;
    public Explosion(double x, double y, int radius ,double schaden,Spieler spieler){
        super((int)x,(int)y,1,1,0,0,1,schaden,spieler);
        this.radius = radius;
        mitte = radius+30;
        timer=radius-1;
        gitter = new int[mitte*2+1][mitte*2+1];
        gitter[mitte][mitte]=radius;

        ArrayList<Hindernis> hindernisse = Objektmanager.getHindernisse();
        for(int i=0; i<gitter.length;i++){
            for(int j=0; j<gitter[0].length;j++){
                if(istHindernis(i-mitte+(int)x,j-mitte+(int)y,hindernisse)){
                    gitter[i][j]=-1;
                }
            }
        }
        Objektmanager.getProjektile().add(this);
    }

    public void bewegungBerechnen(){
        super.bewegungBerechnen();
        kollidiertMitSpieler();
        kollidiertMitNPC();
        if(radiusZeichnen>radius){
            Objektmanager.getProjektile().remove(this);
        }

        if(timer>1)
            timer--;

        gitterTmp=new int[gitter.length][gitter[0].length];

        for(int i=0; i<gitter.length;i++){
            for(int j=0; j<gitter[0].length;j++){
                gitterTmp[i][j] = gitter[i][j];
            }
        }
        boolean loeschen = true;
        for(int i=0; i<gitterTmp.length;i++){
            for(int j=0; j<gitterTmp[0].length;j++){
                if(gitter[i][j]>0)
                    if(erweitern(i,j))
                        loeschen = false;
            }
        }

        if(loeschen){
            Objektmanager.getProjektile().remove(this);
        }

        gitter=gitterTmp;
    }

    private boolean erweitern(int xp, int yp){
        if(gitter[xp][yp]>1){
            int p=10;
            int zusatz = 1;
            boolean rueckgabe = false;
            /*if((int)(Math.random()*5)==0)
            zusatz=0;
             */  
            if(xp>0&&gitter[xp-1][yp]==0){
                gitterTmp[xp-1][yp]=gitter[xp][yp]-zusatz;  
                rueckgabe = true;
                if((int)(Math.random()*p)==0 && xp>1 && gitter[xp-2][yp]==0){            
                    gitterTmp[xp-2][yp]=gitter[xp][yp]-zusatz;    
                }
            }
            if(yp>0&&gitter[xp][yp-1]==0){
                gitterTmp[xp][yp-1]=gitter[xp][yp]-zusatz;
                rueckgabe = true;
                if((int)(Math.random()*p)==0 && yp>1 && gitter[xp][yp-2]==0){            
                    gitterTmp[xp][yp-2]=gitter[xp][yp]-zusatz;    
                }
            }
            if(xp<gitter.length-1&&gitter[xp+1][yp]==0){
                gitterTmp[xp+1][yp]=gitter[xp][yp]-zusatz;
                rueckgabe = true;
                if((int)(Math.random()*p)==0 && xp<gitter.length-2 && gitter[xp+2][yp]==0){            
                    gitterTmp[xp+2][yp]=gitter[xp][yp]-zusatz;    
                }
            }
            if(yp<gitter[0].length-1&&gitter[xp][yp+1]==0){
                gitterTmp[xp][yp+1]=gitter[xp][yp]-zusatz;  
                rueckgabe = true;
                if((int)(Math.random()*p)==0 && yp<gitter[0].length-2 && gitter[xp][yp+2]==0){            
                    gitterTmp[xp][yp+2]=gitter[xp][yp]-zusatz;    
                }
            }

            return rueckgabe;
        }else
            return false;
    }

    private boolean istHindernis(int xp, int yp, ArrayList<Hindernis> hindernisse){
        boolean rueckgabe = false;
        for(int i=0; i<hindernisse.size();i++){
            if(hindernisse.get(i).getX()==xp && hindernisse.get(i).getY()==yp&&!hindernisse.get(i).istNiedrig()){
                rueckgabe = true;
            }
        }

        return rueckgabe;
    }

    protected Spieler kollidiertMitSpieler(){
        ArrayList<Spieler> spieler = Objektmanager.getSpieler();
        int sx, sy;
        Rectangle r = rect; 
        for(int i = 0; i < spieler.size();i++){
            if(spieler.get(i).amLeben()){
                sx = (int)(spieler.get(i).getX()+(spieler.get(i).getBreite()/2));
                sy = (int)(spieler.get(i).getY()+(spieler.get(i).getHoehe()/2));

                double abstand = Math.sqrt((x-sx)*(x-sx)+(y-sy)*(y-sy));
                if(abstand<radius){
                    int schadenMin = (int)(schaden/3);
                    int schadenReal = (int) (schaden - ((schaden-schadenMin)*abstand/radius));
                    if(spieler.get(i).getMomentanLeben()-schaden<=0){
                        besitzer.killcounterErhoehen();
                    }
                    spieler.get(i).kriegtSchaden(schaden);
                }
            }
        }
        return null;
    }

    protected NPC kollidiertMitNPC(){
        ArrayList<NPC> npc = Objektmanager.getNPC();
        int sx, sy;
        Rectangle r = rect;
        for(int i = 0; i < npc.size();i++){
            sx = (int)(npc.get(i).getX()+(npc.get(i).getBreite()/2));
            sy = (int)(npc.get(i).getY()+(npc.get(i).getHoehe()/2));

            double abstand = Math.sqrt((x-sx)*(x-sx)+(y-sy)*(y-sy));
            if(abstand<radius){
                int schadenMin = (int)(schaden/3);
                int schadenReal = (int) (schaden - ((schaden-schadenMin)*abstand/radius));
                if(npc.get(i).getMomentanLeben()-schaden<=0){
                    besitzer.killcounterErhoehen();
                }
                npc.get(i).kriegtSchaden(schaden);
            }
        }
        return null;
    }

    public void zeichnen(Graphics g, JPanel panel){
        g.setColor(Color.RED);
        for(int i=0; i<gitter.length;i++){
            for(int j=0; j<gitter[0].length;j++){
                if(gitter[i][j]>0){
                    g.setColor(new Color(250,220*gitter[i][j]/radius/timer+30,(220*gitter[i][j]/radius/timer+30)*2/3));
                    g.fillRect((int)((i-mitte+x)*genauigkeit),(int)((j-mitte+y)*genauigkeit),genauigkeit,genauigkeit);
                }
            }
        }
    }
}

/*import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
public class Explosion extends Projektil{
private int lebensZeit = 1000;
int radius, radiusZeichnen, timer;
public Explosion(double x, double y, int radius ,double schaden,Spieler spieler){
super(x,y,1,1,0,0,1,schaden,spieler);
this.radius = radius;
}

public void bewegungBerechnen(){
kollidiertMitSpieler();
if(radiusZeichnen>radius){
Objektmanager.getProjektile().remove(this);
}
timer++;
if(timer%2==0)
radiusZeichnen++;
}

protected Spieler kollidiertMitSpieler(){
ArrayList<Spieler> spieler = Objektmanager.getSpieler();
int sx, sy;
Rectangle r = rect;
for(int i = 0; i < spieler.size();i++){
sx = (int)(spieler.get(i).getX()+(spieler.get(i).getBreite()/2));
sy = (int)(spieler.get(i).getY()+(spieler.get(i).getHoehe()/2));

double abstand = Math.sqrt((x-sx)*(x-sx)+(y-sy)*(y-sy));
if(abstand<radius){
int schadenMin = (int)(schaden/3);
int schadenReal = (int) (schaden - ((schaden-schadenMin)*abstand/radius));
if(spieler.get(i).getMomentanLeben()-schaden<=0){
besitzer.killcounterErhoehen();
}
spieler.get(i).kriegtSchaden(schaden);
}
}
return null;
}

public void zeichnen(Graphics g, JPanel panel){
g.setColor(Color.RED);
g.fillOval((int)((x-radiusZeichnen/2)*genauigkeit),(int)((y-radiusZeichnen/2)*genauigkeit),radiusZeichnen*genauigkeit,radiusZeichnen*genauigkeit);
g.setColor(Color.ORANGE);
g.fillOval((int)((x-radiusZeichnen/2.2)*genauigkeit),(int)((y-radiusZeichnen/2.2)*genauigkeit),(int)(radiusZeichnen/1.1*genauigkeit),(int)(radiusZeichnen/1.1*genauigkeit));
g.setColor(Color.YELLOW);
g.fillOval((int)((x-radiusZeichnen/4)*genauigkeit),(int)((y-radiusZeichnen/4)*genauigkeit),radiusZeichnen/2*genauigkeit,radiusZeichnen/2*genauigkeit);
g.setColor(Color.WHITE);
g.fillOval((int)((x-radiusZeichnen/6)*genauigkeit),(int)((y-radiusZeichnen/6)*genauigkeit),radiusZeichnen/3*genauigkeit,radiusZeichnen/3*genauigkeit);
}
}*/
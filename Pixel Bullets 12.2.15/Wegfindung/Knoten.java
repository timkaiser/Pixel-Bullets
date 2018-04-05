package Wegfindung;

import java.util.*;
import javax.swing.*;
import java.awt.*;
//Keine Knoten/Pfade am Rand
//Keine Knoten nebeneinander
//an keinen Pfadpunkt grenzen mehr als 2 weiter Pfadpunkte an
//
//moegliche Fehlerquellen:
//verbindungen werden nur einseitig gesetzt
public class Knoten{
    int index;
    int x, y;
    public static Verbindung[][] matrix;
    public static ArrayList<Knoten> knoten;

    Knoten vorgaenger;

    private boolean offen, geschlossen;
    private int h;

    public Knoten(int x, int y){
        this.x=x;
        this.y=y;

        if(knoten == null){
            knoten = new ArrayList<Knoten>();
        }
        index = knoten.size();
        knoten.add(this);
    }

    public static void matrixInitialisieren(int[][] pfade){
        matrix = new Verbindung[knoten.size()][knoten.size()];
        for(int i=0; i<knoten.size();i++){
            if(knoten.get(i).getX()!=0 && knoten.get(i).getX()!=pfade.length && knoten.get(i).getY()!=0 && knoten.get(i).getY()!=pfade.length){               
                boolean b = false;
                Verbindung v = new Verbindung();
                v.addEndpunkt(knoten.get(i));
                b = false; 
                if(pfade[knoten.get(i).getX()+1][knoten.get(i).getY()]==2){
                    b=pfadFolgen(knoten.get(i).getX()+1,knoten.get(i).getY(),knoten.get(i).getX(),knoten.get(i).getY(),v,pfade);
                }
                if(b){
                    matrix[v.getEndpunkte()[0].getIndex()][v.getEndpunkte()[1].getIndex()]=v;
                    matrix[v.getEndpunkte()[1].getIndex()][v.getEndpunkte()[0].getIndex()]=v.getVerbindungRueckwaerts();
                }
                b = false; 
                v = new Verbindung();
                v.addEndpunkt(knoten.get(i));
                if(pfade[knoten.get(i).getX()-1][knoten.get(i).getY()]==2){
                    b=pfadFolgen(knoten.get(i).getX()-1,knoten.get(i).getY(),knoten.get(i).getX(),knoten.get(i).getY(),v,pfade);
                }
                if(b){
                    matrix[v.getEndpunkte()[0].getIndex()][v.getEndpunkte()[1].getIndex()]=v;
                    matrix[v.getEndpunkte()[1].getIndex()][v.getEndpunkte()[0].getIndex()]=v.getVerbindungRueckwaerts();
                }
                v = new Verbindung();
                v.addEndpunkt(knoten.get(i));
                b = false; 
                if(pfade[knoten.get(i).getX()][knoten.get(i).getY()+1]==2){
                    b=pfadFolgen(knoten.get(i).getX(),knoten.get(i).getY()+1,knoten.get(i).getX(),knoten.get(i).getY(),v,pfade);
                }
                if(b){
                    matrix[v.getEndpunkte()[0].getIndex()][v.getEndpunkte()[1].getIndex()]=v;
                    matrix[v.getEndpunkte()[1].getIndex()][v.getEndpunkte()[0].getIndex()]=v.getVerbindungRueckwaerts();
                }

                v = new Verbindung();
                v.addEndpunkt(knoten.get(i));
                b = false; 
                if(pfade[knoten.get(i).getX()][knoten.get(i).getY()-1]==2){
                    b=pfadFolgen(knoten.get(i).getX(),knoten.get(i).getY()-1,knoten.get(i).getX(),knoten.get(i).getY(),v,pfade);
                }
                if(b){
                    matrix[v.getEndpunkte()[0].getIndex()][v.getEndpunkte()[1].getIndex()]=v;
                    matrix[v.getEndpunkte()[1].getIndex()][v.getEndpunkte()[0].getIndex()]=v.getVerbindungRueckwaerts();
                }
            }
        }

        /*for(int i=0; i<matrix.length; i++){
        for(int j=0; j<matrix[0].length; j++){
        if(matrix[i][j]!=null){
        System.out.print("|X");
        }else if(i==j){
        System.out.print("|O");
        }else{
        System.out.print("|.");
        }
        }
        System.out.println("");
        }*/
    } 

    public static boolean pfadFolgen(int xp, int yp, int xAlt, int yAlt,Verbindung v,int[][] pfade){
        boolean b = false;
        v.addPunkt(new Punkt(xp,yp));        
        if(pfade[xp+1][yp]==1 && xp+1!=xAlt){
            b=true;
            v.addEndpunkt(getKnoten(xp+1,yp));
        }else if(pfade[xp-1][yp]==1 && xp-1!=xAlt){
            b=true;
            v.addEndpunkt(getKnoten(xp-1,yp));
        }else if(pfade[xp][yp+1]==1 && yp+1!=yAlt){
            b=true;
            v.addEndpunkt(getKnoten(xp,yp+1));
        }else if(pfade[xp][yp-1]==1 && yp-1!=yAlt){
            b=true;
            v.addEndpunkt(getKnoten(xp,yp-1));
        }else{
            if(pfade[xp+1][yp]==2 && xp+1!=xAlt){
                b=pfadFolgen(xp+1,yp,xp,yp,v,pfade);
            }else
            if(pfade[xp-1][yp]==2 && xp-1!=xAlt){
                b=pfadFolgen(xp-1,yp,xp,yp,v,pfade);
            }else
            if(pfade[xp][yp+1]==2 && yp+1!=yAlt){
                b=pfadFolgen(xp,yp+1,xp,yp,v,pfade);
            }else
            if(pfade[xp][yp-1]==2 && yp-1!=yAlt){
                b=pfadFolgen(xp,yp-1,xp,yp,v,pfade);
            }
        }
        return b;
    }

    public static Knoten getKnoten(int xs, int ys){
        Knoten k=null;
        for(int i=0; i<knoten.size(); i++){
            if(knoten.get(i).getX()==xs && knoten.get(i).getY()==ys){
                k=knoten.get(i); 
            }
        }
        return k;
    }

    public static Knoten getNaechstenKnoten(int xs, int ys){
        int diff = 100000;
        Knoten k=null;
        for(int i=0; i<knoten.size(); i++){
            int diffK = Math.abs(knoten.get(i).getX()-xs)+Math.abs(knoten.get(i).getY()-ys);
            if(diffK<diff){
                diff=diffK;
                k=knoten.get(i); 
            }
        }
        return k;
    }

    public void setVorgaenger(Knoten v){
        vorgaenger = v;
    }

    public ArrayList<Knoten> getNachbarn(){
        ArrayList<Knoten> n = new ArrayList<Knoten>();
        for(int i=0;i<matrix[index].length;i++){
            if(matrix[index][i]!=null){
                n.add(knoten.get(i));
            }
        }
        return n;
    }

    public ArrayList<Punkt> getPfad(){
        ArrayList<Punkt> p;
        if(vorgaenger!=null){
            p = vorgaenger.getPfad();
            p.addAll(getVerbindung(vorgaenger.getIndex(),index).getPunkte());
        }else{
            p = new ArrayList<Punkt>();
        }
        return p;
    }

    public void zuruecksetzen(){
        vorgaenger = null;
        h=0;
        offen=false;
        geschlossen=false;
    }

    public int F(int xz, int yz){
        return G()+H(xz,yz);
    }

    public int G(){
        if(vorgaenger!=null)
            return vorgaenger.G()+getVerbindung(vorgaenger.getIndex(),index).getLaenge();
        else
            return 0;
    }

    public int H(int xz, int yz){
        if(h==0){
            h=Math.abs(x-xz)+Math.abs(y-yz);
        }
        return h;
    }

    public void setOffen(){
        geschlossen=false;
        offen=true;
    }

    public void setGeschlossen(){
        offen=false;
        geschlossen=true;
    }

    public boolean isOffen(){
        return offen;
    }

    public boolean isGeschlossen(){
        return geschlossen;
    }

    public void hBerechenen(int xz, int yz){
        h = Math.abs(x-xz)+Math.abs(y-yz);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public static Verbindung getVerbindung(int s, int z){
        return matrix[s][z];
    }

    public int getIndex(){
        return index;
    }

    public static ArrayList<Knoten> getKnoten(){
        return knoten;
    }

}

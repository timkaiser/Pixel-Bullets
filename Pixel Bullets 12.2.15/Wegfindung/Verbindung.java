package Wegfindung;

import java.util.*;
public class Verbindung{
    ArrayList<Punkt> punkte;
    Knoten[] endpunkte; 
    public Verbindung(){
        punkte = new ArrayList<Punkt>();
        endpunkte = new Knoten[2];
    }

    public void addPunkt(Punkt p){
        punkte.add(p);
    }

    public void addEndpunkt(Knoten p){
        addPunkt(new Punkt(p.getX(),p.getY()));
        if(endpunkte[0]==null){
            endpunkte[0]=p;
        }else{
            endpunkte[1]=p;
        }
    }

    public Knoten[] getEndpunkte(){
        return endpunkte;
    }

    public ArrayList<Punkt> getPunkte(){
        return punkte;
    }

    public int getLaenge(){
        return punkte.size();
    }
    
    public Verbindung getVerbindungRueckwaerts(){
        Verbindung v = new Verbindung();
        v.addEndpunkt(endpunkte[1]);
        for(int i=punkte.size()-2;i>0;i--){
            v.addPunkt(punkte.get(i));
        }
        v.addEndpunkt(endpunkte[0]);
        
        return v;
    }
}

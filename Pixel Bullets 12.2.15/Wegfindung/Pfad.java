package Wegfindung;

import java.util.*;

public class Pfad{   
    ArrayList<Punkt> wegpunkte;
    ArrayList<Knoten> offeneListe;
    ArrayList<Knoten> geschlosseneListe;

    public Pfad()    {
        wegpunkte = new ArrayList<Punkt>();
    }

    public void wegBerechnen(int xs, int ys, int xz, int yz){
        wegBerechnen(Knoten.getNaechstenKnoten(xs,ys),Knoten.getNaechstenKnoten(xz,yz));
    }

    public void wegBerechnen(Knoten s, Knoten z){
        offeneListe = new ArrayList<Knoten>();
        geschlosseneListe = new ArrayList<Knoten>();
        Knoten start = s;
        Knoten ziel = z;
        if(s!=z){
            for(int i=0;i<Knoten.getKnoten().size();i++){
                Knoten.getKnoten().get(i).zuruecksetzen();
            }

            geschlosseneListe.add(start);
            start.setGeschlossen();
            {
                ArrayList<Knoten> nachbarn = start.getNachbarn();
                for(int i=0; i<nachbarn.size(); i++){
                    nachbarn.get(i).setVorgaenger(start);
                    nachbarn.get(i).setOffen();
                }
                offeneListe.addAll(nachbarn);
            }

            boolean fertig=false;
            while(offeneListe.size()>0 && !fertig){
                Knoten k=null;

                int f=10000;
                for(int i=0;i<offeneListe.size(); i++){
                    if(offeneListe.get(i).F(s.getX(),s.getY())<=f){
                        f = offeneListe.get(i).F(s.getX(),s.getY());
                        k = offeneListe.get(i);
                    }
                }
                offeneListe.remove(k);
                geschlosseneListe.add(k);
                k.setGeschlossen();

                if(k==ziel){
                    wegpunkte = k.getPfad();
                    fertig=true;
                    //break;
                }

                ArrayList<Knoten> nachbarn = k.getNachbarn();

                for(int i=0;i<nachbarn.size(); i++){
                    Knoten n = nachbarn.get(i);
                    if(!n.isGeschlossen()&&!n.isOffen()){
                        offeneListe.add(n);
                        n.setVorgaenger(k);
                    }else if(n.isOffen()){
                        if(n.G()>k.G()+1){
                            offeneListe.add(n);
                            n.setOffen();
                            n.setVorgaenger(k);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Punkt> getWegpunkte(){
        return wegpunkte;
    }

}

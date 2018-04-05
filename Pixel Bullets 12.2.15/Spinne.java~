import java.util.*;

public class Spinne extends Gegner{
    public Spinne(double x, double y){
        super(new Animation(Bilder.getSpinnebild(),12,100000),
            new Animation(Bilder.getSpinnebild(),12,100000),
            new Animation(Bilder.getSpinnebild(),12,100000),
            new Animation(Bilder.getSpinnebild(),12,100000),
            Bilder.getSpinnemaske(),x,y,12,12);
        maximalGeschwindigkeit = 10.0;
        xMitte=6;
        yMitte=6;

        maximalLeben = 20;
        momentanLeben = 20;
    }

    protected void ki(ArrayList<Hindernis> hindernisse){
        /* if(punktInRadius(startX,startY,480)){
        if(spielerInRadius(160)){
        aufSpielerZuBewegen();
        }else{
        if(punktInRadius(startX,startY,32)){
        zufaelligBewegen();
        }else{
        zurueckZumStart();
        }
        }
        }else{ 
        zurueckZumStart();
        }
         */
    }

    private void aufSpielerZuBewegen(){
        maximalGeschwindigkeit = 12;
        if(horizontaleGeschwindigkeit==0&&vertikaleGeschwindigkeit==0&&(spieler.getXMittelpunkt()-this.getXMittelpunkt())!=0&&(spieler.getYMittelpunkt()-this.getYMittelpunkt())!=0&&(int)(Math.random()*30)==1){
            double winkel = Math.atan((spieler.getXMittelpunkt()-this.getXMittelpunkt())/(spieler.getYMittelpunkt()-this.getYMittelpunkt()));
            horizontaleGeschwindigkeit = Math.sin(winkel)*maximalGeschwindigkeit;
            vertikaleGeschwindigkeit = Math.cos(winkel)*maximalGeschwindigkeit;

            if(spieler.getXMittelpunkt()<this.getXMittelpunkt()){
                horizontaleGeschwindigkeit = horizontaleGeschwindigkeit*Math.signum(horizontaleGeschwindigkeit)*-1;
            }else{
                horizontaleGeschwindigkeit = horizontaleGeschwindigkeit*Math.signum(horizontaleGeschwindigkeit);
            }
            if(spieler.getYMittelpunkt()<this.getYMittelpunkt()){
                vertikaleGeschwindigkeit = vertikaleGeschwindigkeit*Math.signum(vertikaleGeschwindigkeit)*-1;
            }else{
                vertikaleGeschwindigkeit = vertikaleGeschwindigkeit*Math.signum(vertikaleGeschwindigkeit);
            }
        }
    }

    private void zufaelligBewegen(){
        maximalGeschwindigkeit = 12;
        int rndm = (int)(Math.random()*4);
        if((int)(Math.random()*30)==1){
            if(rndm == 0){               
                beschleunigen(maximalGeschwindigkeit,0);
            }else  if(rndm == 1){               
                beschleunigen(-maximalGeschwindigkeit,0);
            }else  if(rndm == 2){               
                beschleunigen(0,maximalGeschwindigkeit);
            }else  if(rndm == 3){               
                beschleunigen(0,-maximalGeschwindigkeit);
            }
        }
    }

    private void zurueckZumStart(){
        maximalGeschwindigkeit = 2;
        if(startX>x){
            beschleunigen(3,0);
        }else if(startX<x){
            beschleunigen(-3,0);
        }        

        if(startY>y){
            beschleunigen(0,3);
        }else if(startY<y){
            beschleunigen(0,-3);
        }
    }
}

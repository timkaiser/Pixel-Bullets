TODO:
 - Bugfixes:
 
  - Spieler steht in der Wand (Beine ragen in die wand rein(ausgelöst durch rundungsfehler))
  - NPCs bewegen sich nicht (kann ich (Mehmet) nicht alleine fixen, da ich die Pfadfindung net drauf hab)
  (- NPCs töten unbeweglichen Spieler nicht (Tritt bei tests nicht mehr auf))
 - Features:
  - "Elementarprojektile" (Frost, Feuer, Gift, Verfolgungskugeln, Bouncing,...)
  - Balancing der PowerUps
  
 - Gefixt:
  + Sniperkugel durch die Wand !FIXED! (Außer man steht so weit von ner wand weg dass die entfernung durch 2 eine genau Zahl ohne Rest gibt)
  
  
  
  
 

boolean lineSegmentCollision(float[] a1, float[] a2,
                             float[] b1, float[] b2)
{
    float denom = (b2[1] - b1[1]) * (a2[0] - a1[0]) -
                  (b2[0] - b1[0]) * (a2[1] - a1[1]);
    if (Math.abs(denom) < EPSILON) return false;
    
    float ua = ((b2[0] - b1[0]) * (a1[1] - b1[1]) -
                (b2[1] - b1[1]) * (a1[0] - b1[0])) / denom;
    float ub = ((a2[0] - a1[0]) * (a1[1] - b1[1]) -
                (a2[1] - a1[1]) * (a1[0] - b1[0])) / denom;
    return ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1;
}


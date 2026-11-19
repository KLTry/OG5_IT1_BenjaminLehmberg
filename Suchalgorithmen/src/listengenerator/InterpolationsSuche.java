package listengenerator;

import java.util.Random;

public class InterpolationsSuche {

    public static long[] getSortedList(int laenge){
        Random rand = new Random(111111);
        long[] zahlenliste = new long[laenge];
        long naechsteZahl = 0;
        
        for(int i = 0; i < laenge; i++){
            naechsteZahl += rand.nextInt(3)+1;
            zahlenliste[i] = naechsteZahl;
        }
        
        return zahlenliste;
    }
    
    public static int interpolationsSuche(long[] liste, long gesuchteZahl) {
        int links = 0;
        int rechts = liste.length - 1;
        
        while (links <= rechts && gesuchteZahl >= liste[links] && gesuchteZahl <= liste[rechts]) {
            
            if (liste[links] == liste[rechts]) {
                if (liste[links] == gesuchteZahl) return links;
                return -1;
            }
            
            long werteDifferenz = liste[rechts] - liste[links];
            double anteil = (double)(gesuchteZahl - liste[links]) / werteDifferenz;
            int stelle = links + (int)(anteil * (rechts - links));

            if (liste[stelle] == gesuchteZahl) {
                return stelle; 
            }
         
            if (liste[stelle] < gesuchteZahl) {
                links = stelle + 1;
            } 
            else {
                rechts = stelle - 1;
            }
        }
        return -1; 
    }
    
    public static void main(String[] args){
        final int ANZAHL = 60000000; 
        
        System.out.println("Generiere Liste für Interpolationssuche...");
        long[] a = getSortedList(ANZAHL);
        
        long gesuchteZahl = 2000000;
        System.out.println("Starte Interpolationssuche nach: " + gesuchteZahl);
        
        long time = System.nanoTime(); 
        
        int stelle = interpolationsSuche(a, gesuchteZahl);
        
        long dauerNano = System.nanoTime() - time;
        
        System.out.println("Gefunden an Stelle: " + stelle);
        System.out.println("Dauer Interpolationssuche: " + dauerNano + "ns");
    }
}
package listengenerator;

import java.util.Random;

public class LineareSuche {

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
    
    public static void main(String[] args){
        final int ANZAHL = 20000000; 
        
        System.out.println("Generiere Liste für Lineare Suche...");
        long[] a = getSortedList(ANZAHL);
        
        long gesuchteZahl = 19999998;
        System.out.println("Starte Lineare Suche nach: " + gesuchteZahl);
        
        long time = System.nanoTime();
        int stelle = -1; 
        
        for(int i = 0; i < a.length; i++) {
            if(a[i] == gesuchteZahl) {
                stelle = i; 
                break;
            }
        }
        
        long dauer = System.nanoTime() - time;
        
        System.out.println("Gefunden an Stelle: " + stelle); 
        System.out.println("Dauer Lineare Suche: " + dauer + " ns");
    }
}
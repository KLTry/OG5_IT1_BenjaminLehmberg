package test;

import java.util.Scanner;

public class Primzahlen {

	public static void main(String[] args) {

		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Geben Sie mir eine Zahl, die auf Primzahl geprüft werden soll!");
			long zahl = scan.nextLong();

			long startZeit = System.nanoTime();

			System.out.println(isPrim(zahl));	

			long endZeit = System.nanoTime();
			long dauerNano = endZeit - startZeit;
			double dauerMilli = dauerNano / 1_000_000.0;

			System.out.println("Das entspricht " + dauerMilli + " Millisekunden.");
		}
	}

	public static boolean isPrim(long zahl) {
		zahl = Math.abs(zahl);
		boolean prim = true; 
		if(zahl == 1)
		{ prim = true; 
		} else {
			for ( long i = 2; i <= zahl - 1; i++ ) {
				if(zahl % i == 0) {
					prim = false;
					i = (long) zahl;
				} 		
			}
		}
		return prim;
	}
}




package test;


import java.util.Scanner;

public class Primzahlen {

	public static void main(String[] args) {

		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Geben Sie mir eine Zahl, die auf Primzahl geprüft werden soll!");
			int zahl = scan.nextInt();

			long startZeit = System.nanoTime();

			System.out.println(isPrim(zahl));	

			long endZeit = System.nanoTime();
			long dauerNano = endZeit - startZeit;
			double dauerMilli = dauerNano / 1_000_000.0;

			System.out.println("Das entspricht " + dauerMilli + " Millisekunden.");
		}
	}

	public static boolean isPrim(long Zahl) {
		boolean prim = true; 
		if(Zahl == 1)
		{ prim = true; 
		} else {
			for ( int i = 2; i <= Zahl - 1; i++ ) {
				if(Zahl % i == 0) {
					prim = false;
					i = (int) Zahl;
				} 		
			}
		}
		return prim;
	}
}




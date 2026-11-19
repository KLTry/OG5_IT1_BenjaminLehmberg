package de.promocode.logic;

public class CodeSearch {

	public static int NOT_FOUND = -1;

	/**
	 * Sucht einen Code in der Liste.
	 * 
	 * @param list
	 *            Liste der Codes im Long-Format
	 * @param searchValue
	 *            Gesuchter Code
	 * @return Index des Codes, wenn er vorhanden ist, NOT_FOUND wenn er nicht
	 *         gefunden werden konnte
	 */

	 public static int findPromoCode(long[] list, long searchValue) {
	        int links = 0;
	        int rechts = list.length - 1;
	        
	        while (links <= rechts && searchValue >= list[links] && searchValue <= list[rechts]) {
	            
	            if (list[links] == list[rechts]) {
	                if (list[links] == searchValue) return links;
	                return NOT_FOUND;
	            }
	            
	            long werteDifferenz = list[rechts] - list[links];
	            double anteil = (double)(searchValue - list[links]) / werteDifferenz;
	            int stelle = links + (int)(anteil * (rechts - links));

	            if (list[stelle] == searchValue) {
	                return stelle; 
	            }
	         
	            if (list[stelle] < searchValue) {
	                links = stelle + 1;
	            } 
	            else {
	                rechts = stelle - 1;
	            }
	        }
	        return NOT_FOUND; 
	    }

}


package algorithmen;

public class Suchalgorithmen {

	private double t;; 
	private long []a; 
	
	public Suchalgorithmen(long[] a) {
		this.a = a;
	}
	
public double interpolationsSuche (int von, int bis ,int x ) {
	
	if(a[von] < a[bis]) {
	 t = von +((bis - von ) * (x - a[von])/(a[bis] - a[von]));
	} 
	if(x == a [(int) t]) {
		return t;
	}
	

}
}

	


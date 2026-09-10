package de.futurehome.tanksimulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
	public TankSimulator f;

	public MyActionListener(TankSimulator f) {
		this.f = f;
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == f.btnBeenden)
			System.exit(0);

		if (obj == f.btnEinfuellen) {

			double fuellstand = f.myTank.getFuellstand();
			if( fuellstand < 200) {
				fuellstand = fuellstand + 5;
				if (fuellstand > 200) {
					fuellstand = 200; 
				}
				f.myTank.setFuellstand(fuellstand);
				f.lblFuellstand.setText("" + fuellstand + "L");
				double prozent = (fuellstand / 200) * 100;
				f.lblProzent.setText("" + prozent + "%" ); } else {
					f.lblFuellstand.setText("Dein Tank ist voll");	
				}
		}



		if (obj == f.btnVerbrauchen && f.myTank.getFuellstand() >= 2) {
		    double verbrauch = f.myTank.getFuellstand();
		    verbrauch = verbrauch - 2;

		    f.myTank.setFuellstand(verbrauch);

		    f.lblFuellstand.setText("" + verbrauch + "L");

		    double prozent = (verbrauch / 200) * 100;
		    f.lblProzent.setText("" + prozent + "%");
		}



		if (obj == f.btnZuruecksetzen) {
			    f.myTank.setFuellstand(0);

			    f.lblFuellstand.setText("0L");
			    f.lblProzent.setText("0%");
			}








	}
}





		
	
	
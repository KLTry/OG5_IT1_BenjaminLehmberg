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
			fuellstand = fuellstand + 5;
			f.myTank.setFuellstand(fuellstand);

			f.lblFuellstand.setText(""+fuellstand); }

			
			if (obj == f.btnVerbrauchen && f.myTank.getFuellstand() > 1) {
				 double verbrauch = f.myTank.getFuellstand();
				 verbrauch = verbrauch - 2;
				 f.myTank.setFuellstand(verbrauch);
				
				 f.lblFuellstand.setText(""+ verbrauch);
				if(f.myTank.getFuellstand() <= 1) {
					f.lblFuellstand.setText("Du musst Tanken");
				
				}
			}
				if (obj == f.btnZuruecksetzen) {
					double reset = f.myTank.getFuellstand();
					reset = reset - reset ;
					f.myTank.setFuellstand(reset);

					f.lblFuellstand.setText(""+reset ); }
				 }
			}

		
	
	
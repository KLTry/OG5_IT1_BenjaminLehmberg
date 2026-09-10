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

        if (obj == f.btnBeenden) {
            System.exit(0);
        }

        if (obj == f.btnEinfuellen) {
            f.myTank.einfuellen(5);
        }

        if (obj == f.btnVerbrauchen) {
            f.myTank.verbrauchen(2);
        }

        if (obj == f.btnZuruecksetzen) {
            f.myTank.zuruecksetzen();
        }

        // Füllstand als Zahl
        double aktuellerFuellstand = f.myTank.getFuellstand();
        f.lblFuellstand.setText(aktuellerFuellstand + " L");

        // Füllstand in der ProgressBar aktualisieren
        f.prgFuellstand.setValue((int) aktuellerFuellstand);
    }
}	
	
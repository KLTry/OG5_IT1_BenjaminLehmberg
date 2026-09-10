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

        // Anzeige nach jeder Aktion zentral aktualisieren
        f.lblFuellstand.setText(f.myTank.getFuellstand() + " L");
        f.lblProzent.setText("(" + f.myTank.getProzent() + "%)");
    }
}
	
	
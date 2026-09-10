package de.futurehome.tanksimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyActionListener implements ActionListener, ChangeListener {
    public TankSimulator f;

    public MyActionListener(TankSimulator f) {
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == f.btnBeenden) {
            System.exit(0);
        }

        if (obj == f.btnEinfuellen) {
            f.myTank.einfuellen(5);
        }

        if (obj == f.btnVerbrauchen) {
            // Liest den Wert direkt aus dem Slider aus!
            int verbrauchswert = f.sldVerbrauch.getValue();
            f.myTank.verbrauchen(verbrauchswert);
        }

        if (obj == f.btnZuruecksetzen) {
            f.myTank.zuruecksetzen();
        }

        // Anzeige aktualisieren
        aktualisiereAnzeige();
    }

    // Wird aufgerufen, wenn der Slider bewegt wird
    @Override
    public void stateChanged(ChangeEvent e) {
        int verbrauchswert = f.sldVerbrauch.getValue();
        f.lblVerbrauchText.setText("Verbrauch: " + verbrauchswert + " L");
    }

    private void aktualisiereAnzeige() {
        double aktuellerFuellstand = f.myTank.getFuellstand();
        f.lblFuellstand.setText(aktuellerFuellstand + " L");
        f.prgFuellstand.setValue((int) aktuellerFuellstand);
    }
}
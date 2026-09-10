package de.futurehome.tanksimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyActionListener implements ActionListener, ChangeListener {
    public TankSimulator f;
    
    // Formatierer für die Uhrzeit (HH:mm:ss -> z.B. 14:32:05)
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public MyActionListener(TankSimulator f) {
        this.f = f;
        logEintrag("Programm gestartet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == f.btnBeenden) {
            System.exit(0);
        }

        if (obj == f.btnEinfuellen) {
            f.myTank.einfuellen(5);
            logEintrag("5 Liter eingefüllt. Neuer Füllstand: " + f.myTank.getFuellstand() + " L");
        }

        if (obj == f.btnVerbrauchen) {
            int verbrauchswert = f.sldVerbrauch.getValue();
            f.myTank.verbrauchen(verbrauchswert);
            logEintrag(verbrauchswert + " Liter verbraucht. Neuer Füllstand: " + f.myTank.getFuellstand() + " L");
        }

        if (obj == f.btnZuruecksetzen) {
            f.myTank.zuruecksetzen();
            logEintrag("Tank zurückgesetzt.");
        }

        aktualisiereAnzeige();
    }

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

    // Hilfsmethode zum Protokollieren mit Uhrzeit
    private void logEintrag(String nachricht) {
        String uhrzeit = LocalTime.now().format(timeFormatter);
        f.txtLog.append("[" + uhrzeit + "] " + nachricht + "\n");
    }
}
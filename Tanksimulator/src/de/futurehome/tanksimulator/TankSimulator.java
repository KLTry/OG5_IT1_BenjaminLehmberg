package de.futurehome.tanksimulator;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JProgressBar;
import javax.swing.JSlider; // Import für den JSlider

@SuppressWarnings("serial")
public class TankSimulator extends Frame {

    public Tank myTank;

    private Label lblUeberschrift = new Label("Tank-Simulator");
    public Label lblFuellstand = new Label("0.0 L");
    public JProgressBar prgFuellstand = new JProgressBar(0, 200);

    // Slider für den Verbrauch von 1 bis 4 Litern
    public JSlider sldVerbrauch = new JSlider(1, 4, 2);
    public Label lblVerbrauchText = new Label("Verbrauch: 2 L");

    public Button btnBeenden = new Button("Beenden");
    public Button btnEinfuellen = new Button("Einfüllen");
    public Button btnVerbrauchen = new Button("Verbrauchen");
    public Button btnZuruecksetzen = new Button("Zurücksetzen");

    private Panel pnlNorth = new Panel();
    private Panel pnlCenter = new Panel(new GridLayout(2, 1)); // 2 Zeilen für Status & Slider
    private Panel pnlFuellstand = new Panel(new FlowLayout());
    private Panel pnlSlider = new Panel(new FlowLayout());
    private Panel pnlSouth = new Panel(new GridLayout(1, 0));

    private MyActionListener myActionListener = new MyActionListener(this);

    public TankSimulator() {
        super("Tank-Simulator");

        myTank = new Tank(0);

        // Einstellungen für den Fortschrittsbalken
        prgFuellstand.setValue(0);
        prgFuellstand.setStringPainted(true);

        // Einstellungen für den Slider (1 bis 4 Liter)
        sldVerbrauch.setMajorTickSpacing(1);
        sldVerbrauch.setPaintTicks(true);
        sldVerbrauch.setPaintLabels(true);

        this.lblUeberschrift.setFont(new Font("", Font.BOLD, 16));
        this.pnlNorth.add(this.lblUeberschrift);

        // Füllstandselemente im pnlFuellstand platzieren
        this.pnlFuellstand.add(this.lblFuellstand);
        this.pnlFuellstand.add(this.prgFuellstand);

        // Slider-Elemente im pnlSlider platzieren
        this.pnlSlider.add(this.lblVerbrauchText);
        this.pnlSlider.add(this.sldVerbrauch);

        // Beide Unterpanels im pnlCenter zusammenfassen
        this.pnlCenter.add(this.pnlFuellstand);
        this.pnlCenter.add(this.pnlSlider);

        this.pnlSouth.add(this.btnEinfuellen);
        this.pnlSouth.add(this.btnVerbrauchen);
        this.pnlSouth.add(this.btnZuruecksetzen);
        this.pnlSouth.add(this.btnBeenden);

        this.add(this.pnlNorth, BorderLayout.NORTH);
        this.add(this.pnlCenter, BorderLayout.CENTER);
        this.add(this.pnlSouth, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);

        // Ereignissteuerung für Buttons und Slider
        this.btnEinfuellen.addActionListener(myActionListener);
        this.btnVerbrauchen.addActionListener(myActionListener);
        this.btnBeenden.addActionListener(myActionListener);
        this.btnZuruecksetzen.addActionListener(myActionListener);
        this.sldVerbrauch.addChangeListener(myActionListener); // ChangeListener für Schieberegler
    }

    public static void main(String argv[]) {
        new TankSimulator();
    }
}
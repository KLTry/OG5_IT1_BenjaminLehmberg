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
import javax.swing.JScrollPane; // Import für ScrollBars
import javax.swing.JSlider;
import javax.swing.JTextArea;  // Import für mehrzeiligen Text

@SuppressWarnings("serial")
public class TankSimulator extends Frame {

    public Tank myTank;

    private Label lblUeberschrift = new Label("Tank-Simulator");
    public Label lblFuellstand = new Label("0.0 L");
    public JProgressBar prgFuellstand = new JProgressBar(0, 200);

    public JSlider sldVerbrauch = new JSlider(1, 4, 2);
    public Label lblVerbrauchText = new Label("Verbrauch: 2 L");

    // Neues Log-Fenster (Höhe: 6 Zeilen, Breite: 30 Spalten)
    public JTextArea txtLog = new JTextArea(6, 30);
    private JScrollPane scrollLog = new JScrollPane(txtLog);

    public Button btnBeenden = new Button("Beenden");
    public Button btnEinfuellen = new Button("Einfüllen");
    public Button btnVerbrauchen = new Button("Verbrauchen");
    public Button btnZuruecksetzen = new Button("Zurücksetzen");

    private Panel pnlNorth = new Panel();
    private Panel pnlCenter = new Panel(new GridLayout(3, 1)); // Auf 3 Zeilen erweitert
    private Panel pnlFuellstand = new Panel(new FlowLayout());
    private Panel pnlSlider = new Panel(new FlowLayout());
    private Panel pnlLog = new Panel(new FlowLayout());
    private Panel pnlSouth = new Panel(new GridLayout(1, 0));

    private MyActionListener myActionListener = new MyActionListener(this);

    public TankSimulator() {
        super("Tank-Simulator");

        myTank = new Tank(0);

        // Einstellungen für das Log-Fenster
        txtLog.setEditable(false); // Der Nutzer soll hier nicht tippen können

        prgFuellstand.setValue(0);
        prgFuellstand.setStringPainted(true);

        sldVerbrauch.setMajorTickSpacing(1);
        sldVerbrauch.setPaintTicks(true);
        sldVerbrauch.setPaintLabels(true);

        this.lblUeberschrift.setFont(new Font("", Font.BOLD, 16));
        this.pnlNorth.add(this.lblUeberschrift);

        this.pnlFuellstand.add(this.lblFuellstand);
        this.pnlFuellstand.add(this.prgFuellstand);

        this.pnlSlider.add(this.lblVerbrauchText);
        this.pnlSlider.add(this.sldVerbrauch);

        // ScrollPane in das Log-Panel einfügen
        this.pnlLog.add(scrollLog);

        this.pnlCenter.add(this.pnlFuellstand);
        this.pnlCenter.add(this.pnlSlider);
        this.pnlCenter.add(this.pnlLog);

        this.pnlSouth.add(this.btnEinfuellen);
        this.pnlSouth.add(this.btnVerbrauchen);
        this.pnlSouth.add(this.btnZuruecksetzen);
        this.pnlSouth.add(this.btnBeenden);

        this.add(this.pnlNorth, BorderLayout.NORTH);
        this.add(this.pnlCenter, BorderLayout.CENTER);
        this.add(this.pnlSouth, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);

        this.btnEinfuellen.addActionListener(myActionListener);
        this.btnVerbrauchen.addActionListener(myActionListener);
        this.btnBeenden.addActionListener(myActionListener);
        this.btnZuruecksetzen.addActionListener(myActionListener);
        this.sldVerbrauch.addChangeListener(myActionListener);
    }

    public static void main(String argv[]) {
        new TankSimulator();
    }
}
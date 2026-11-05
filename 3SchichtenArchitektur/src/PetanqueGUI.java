import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PetanqueGUI extends JFrame {
    private static final int ZELL_GROESSE = 45;
    private final PetanqueLogik logik = new PetanqueLogik();

    private final JLabel statusLabel = new JLabel();
    private final JLabel kugelnLabel = new JLabel();
    private final SpielfeldPanel panel = new SpielfeldPanel();

    public PetanqueGUI() {
        setTitle("Pétanque Simulation (13x13)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Anzeigebereich oben
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        topPanel.setBackground(new Color(240, 240, 240));

        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        kugelnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        kugelnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(statusLabel);
        topPanel.add(kugelnLabel);
        add(topPanel, BorderLayout.NORTH);

        // Spielfeld
        add(panel, BorderLayout.CENTER);

        // Button unten
        JButton newGameBtn = new JButton("Neues Spiel / Spielfeld zurücksetzen");
        newGameBtn.setFont(new Font("Arial", Font.BOLD, 12));
        newGameBtn.addActionListener(e -> neustart());
        add(newGameBtn, BorderLayout.SOUTH);

        // mousePressed statt mouseClicked: mouseClicked wird nicht ausgelöst,
        // wenn sich die Maus zwischen Drücken und Loslassen minimal bewegt
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / ZELL_GROESSE;
                int y = e.getY() / ZELL_GROESSE;

                if (x >= 0 && x < PetanqueLogik.GRID_SIZE && y >= 0 && y < PetanqueLogik.GRID_SIZE) {
                    if (logik.werfeKugel(x, y)) {
                        aktualisiereAnzeige();
                        panel.repaint();

                        if (logik.istSpielBeendet()) {
                            zeigeSpielendeDialog();
                        }
                    } else {
                        statusLabel.setText(logik.getStatusMeldung());
                    }
                }
            }
        });

        aktualisiereAnzeige();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void aktualisiereAnzeige() {
        statusLabel.setText(logik.getStatusMeldung());
        if (logik.istSpielBeendet()) {
            statusLabel.setForeground(Color.BLACK);
        } else if (logik.getSpielerAmZug() == PetanqueLogik.SPIELER_1) {
            statusLabel.setForeground(Color.BLUE);
        } else {
            statusLabel.setForeground(Color.RED);
        }
        kugelnLabel.setText(String.format(
                "Geworfene Kugeln — Spieler 1 (Blau): %d/10 | Spieler 2 (Rot): %d/10",
                logik.getKugelnP1(), logik.getKugelnP2()));
    }

    private void neustart() {
        logik.neuesSpiel();
        aktualisiereAnzeige();
        panel.repaint();
    }

    private void zeigeSpielendeDialog() {
        Object[] optionen = {"Nochmal spielen", "Beenden"};
        int wahl = JOptionPane.showOptionDialog(
                this,
                logik.getStatusMeldung() + "\n\nMöchtest du direkt eine neue Runde starten?",
                "Runde beendet",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                optionen,
                optionen[0]
        );

        if (wahl == JOptionPane.YES_OPTION) {
            neustart();
        } else if (wahl == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    private class SpielfeldPanel extends JPanel {
        public SpielfeldPanel() {
            setPreferredSize(new Dimension(PetanqueLogik.GRID_SIZE * ZELL_GROESSE,
                                           PetanqueLogik.GRID_SIZE * ZELL_GROESSE));
            setBackground(new Color(225, 205, 165));
        }

        // Nur Zeichnen, keine Spiellogik!
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (int x = 0; x < PetanqueLogik.GRID_SIZE; x++) {
                for (int y = 0; y < PetanqueLogik.GRID_SIZE; y++) {
                    int px = x * ZELL_GROESSE;
                    int py = y * ZELL_GROESSE;

                    // Randfelder dezent hervorheben
                    if (logik.istRandfeld(x, y)) {
                        g2.setColor(new Color(210, 180, 140));
                        g2.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                    }

                    // Gitter zeichnen
                    g2.setColor(Color.GRAY);
                    g2.drawRect(px, py, ZELL_GROESSE, ZELL_GROESSE);

                    // Kugeln zeichnen
                    int zustand = logik.getFeldZustand(x, y);
                    if (zustand == PetanqueLogik.ZIELKUGEL) {
                        g2.setColor(Color.ORANGE);
                        g2.fillOval(px + 12, py + 12, 20, 20);
                        g2.setColor(Color.BLACK);
                        g2.drawOval(px + 12, py + 12, 20, 20);
                    } else if (zustand == PetanqueLogik.SPIELER_1) {
                        g2.setColor(Color.BLUE);
                        g2.fillOval(px + 6, py + 6, 32, 32);
                    } else if (zustand == PetanqueLogik.SPIELER_2) {
                        g2.setColor(Color.RED);
                        g2.fillOval(px + 6, py + 6, 32, 32);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PetanqueGUI().setVisible(true));
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetanqueLogik {
    public static final int GRID_SIZE = 13;
    public static final int LEER = 0;
    public static final int ZIELKUGEL = 1;
    public static final int SPIELER_1 = 2;
    public static final int SPIELER_2 = 3;

    private final int[][] feld = new int[GRID_SIZE][GRID_SIZE];
    private int zielX;
    private int zielY;

    private int spielerAmZug = SPIELER_1;
    private int kugelnP1 = 0;
    private int kugelnP2 = 0;
    private final int MAX_KUGELN = 10; // Jeder Spieler hat 10 Würfe

    private boolean spielBeendet = false;
    private boolean zielkugelImAus = false;
    private String statusMeldung = "";
    private final Random random = new Random();

    public PetanqueLogik() {
        neuesSpiel();
    }

    public void neuesSpiel() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                feld[x][y] = LEER;
            }
        }

        // Zielkugel spawnt zufällig auf einem beliebigen Feld (auch am Rand)
        zielX = random.nextInt(GRID_SIZE);
        zielY = random.nextInt(GRID_SIZE);
        feld[zielX][zielY] = ZIELKUGEL;

        spielerAmZug = SPIELER_1;
        kugelnP1 = 0;
        kugelnP2 = 0;
        spielBeendet = false;
        zielkugelImAus = false;
        statusMeldung = "Neues Spiel! Spieler 1 (Blau) fängt an.";
    }

    // Prüft, ob eine Koordinate auf dem Rand liegt
    public boolean istRandfeld(int x, int y) {
        return x == 0 || x == GRID_SIZE - 1 || y == 0 || y == GRID_SIZE - 1;
    }

    public boolean werfeKugel(int startX, int startY) {
        if (spielBeendet) return false;

        // Abwurf nur vom Rand
        if (!istRandfeld(startX, startY)) {
            statusMeldung = "Ungültig! Bitte von einem Randfeld aus werfen.";
            return false;
        }

        // 1. Wurfrichtung: immer vom angeklickten Rand ins Spielfeld hinein
        //    links -> nach rechts, rechts -> nach links, oben -> nach unten, unten -> nach oben
        boolean amXRand = (startX == 0 || startX == GRID_SIZE - 1);
        boolean amYRand = (startY == 0 || startY == GRID_SIZE - 1);
        int dirX = 0;
        int dirY = 0;
        boolean wurfInXRichtung;
        if (amXRand && amYRand) {
            // Eckfeld: Achse, auf der die Zielkugel weiter entfernt liegt
            wurfInXRichtung = Math.abs(zielX - startX) >= Math.abs(zielY - startY);
        } else {
            wurfInXRichtung = amXRand;
        }
        if (wurfInXRichtung) {
            dirX = (startX == 0) ? 1 : -1;
        } else {
            dirY = (startY == 0) ? 1 : -1;
        }
        // Quervektor (senkrecht zur Wurfrichtung) für die seitliche Abweichung
        int querX = -dirY;
        int querY = dirX;

        // 2. Zufall: 0 bis 5 Felder nach vorne, -1/0/+1 Feld seitlich
        int weite = random.nextInt(6);
        int abweichung = random.nextInt(3) - 1;
        int landeX = startX + weite * dirX + abweichung * querX;
        int landeY = startY + weite * dirY + abweichung * querY;

        // 3. Flugbahn vom Abwurffeld (NICHT von der Zielkugel) bis zum Landefeld ablaufen
        List<int[]> bahn = berechneBahn(startX, startY, landeX, landeY);

        int trefferX = landeX;
        int trefferY = landeY;
        int stossX = dirX;
        int stossY = dirY;
        boolean imAus = false;

        int vorherX = startX;
        int vorherY = startY;
        for (int[] p : bahn) {
            if (!imFeld(p[0], p[1])) {
                imAus = true; // Kugel fliegt über den Spielfeldrand hinaus
                break;
            }
            if (feld[p[0]][p[1]] != LEER) {
                trefferX = p[0];
                trefferY = p[1];
                // Stoßrichtung = tatsächliche Flugrichtung im letzten Schritt
                stossX = Integer.compare(p[0], vorherX);
                stossY = Integer.compare(p[1], vorherY);
                break;
            }
            vorherX = p[0];
            vorherY = p[1];
        }

        // 4. Kollision: besetzte Kugel (samt Kette) wegschieben
        if (!imAus && feld[trefferX][trefferY] != LEER) {
            verschiebe(trefferX, trefferY, stossX, stossY);
        }

        // 5. Zielkugel aus dem Feld gestoßen -> sofort Unentschieden
        if (zielkugelImAus) {
            spielBeendet = true;
            statusMeldung = "SPIELENDE: Zielkugel wurde aus dem Feld gestoßen! Unentschieden!";
            return true;
        }

        // 6. Kugel ablegen (im Aus: ersatzlos verloren, zählt aber als Wurf)
        if (!imAus) {
            feld[trefferX][trefferY] = spielerAmZug;
        }
        if (spielerAmZug == SPIELER_1) {
            kugelnP1++;
        } else {
            kugelnP2++;
        }

        wechselSpielerOderBeende();
        if (imAus) {
            statusMeldung = "Kugel im Aus! " + statusMeldung;
        }
        return true;
    }

    private boolean imFeld(int x, int y) {
        return x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
    }

    // Liefert alle Felder zwischen Start (ausgeschlossen) und Ziel (eingeschlossen)
    private List<int[]> berechneBahn(int x0, int y0, int x1, int y1) {
        List<int[]> bahn = new ArrayList<>();
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = Integer.compare(x1, x0);
        int sy = Integer.compare(y1, y0);
        int err = dx - dy;
        int x = x0;
        int y = y0;

        while (x != x1 || y != y1) {
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
            bahn.add(new int[]{x, y});
        }
        return bahn;
    }

    private void verschiebe(int x, int y, int dirX, int dirY) {
        if (dirX == 0 && dirY == 0) return; // Schutz vor Endlosrekursion

        int zielXNeu = x + dirX;
        int zielYNeu = y + dirY;

        // Kugel verlässt das Spielfeld
        if (zielXNeu < 0 || zielXNeu >= GRID_SIZE || zielYNeu < 0 || zielYNeu >= GRID_SIZE) {
            if (feld[x][y] == ZIELKUGEL) {
                zielkugelImAus = true;
            }
            feld[x][y] = LEER;
            return;
        }

        // Kettenreaktion: vordere Kugel zuerst wegschieben
        if (feld[zielXNeu][zielYNeu] != LEER) {
            verschiebe(zielXNeu, zielYNeu, dirX, dirY);
        }

        // Position der Zielkugel mitführen
        if (feld[x][y] == ZIELKUGEL) {
            zielX = zielXNeu;
            zielY = zielYNeu;
        }

        feld[zielXNeu][zielYNeu] = feld[x][y];
        feld[x][y] = LEER;
    }

    private void wechselSpielerOderBeende() {
        if (kugelnP1 >= MAX_KUGELN && kugelnP2 >= MAX_KUGELN) {
            spielBeendet = true;
            auswerten();
        } else if (spielerAmZug == SPIELER_1) {
            spielerAmZug = SPIELER_2;
            statusMeldung = "Spieler 2 (Rot) ist am Zug.";
        } else {
            spielerAmZug = SPIELER_1;
            statusMeldung = "Spieler 1 (Blau) ist am Zug.";
        }
    }

    // Wertung: Kugeln auf den 8 angrenzenden Feldern (Moore-Nachbarschaft)
    private void auswerten() {
        int nachbarn1 = zaehleNachbarn(SPIELER_1);
        int nachbarn2 = zaehleNachbarn(SPIELER_2);

        if (nachbarn1 > nachbarn2) {
            statusMeldung = "SPIELENDE: Spieler 1 (Blau) gewinnt mit " + nachbarn1
                    + " Kugel(n) direkt an der Zielkugel!";
        } else if (nachbarn2 > nachbarn1) {
            statusMeldung = "SPIELENDE: Spieler 2 (Rot) gewinnt mit " + nachbarn2
                    + " Kugel(n) direkt an der Zielkugel!";
        } else {
            // Gleichstand: die am nächsten liegende Kugel entscheidet
            double besteP1 = naechsteDistanz(SPIELER_1);
            double besteP2 = naechsteDistanz(SPIELER_2);
            if (besteP1 < besteP2) {
                statusMeldung = "SPIELENDE: Gleichstand bei den Nachbarn, Spieler 1 (Blau) gewinnt durch die nächste Kugel!";
            } else if (besteP2 < besteP1) {
                statusMeldung = "SPIELENDE: Gleichstand bei den Nachbarn, Spieler 2 (Rot) gewinnt durch die nächste Kugel!";
            } else {
                statusMeldung = "SPIELENDE: Unentschieden!";
            }
        }
    }

    private int zaehleNachbarn(int spieler) {
        int anzahl = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int x = zielX + dx;
                int y = zielY + dy;
                if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE && feld[x][y] == spieler) {
                    anzahl++;
                }
            }
        }
        return anzahl;
    }

    private double naechsteDistanz(int spieler) {
        double beste = Double.MAX_VALUE;
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if (feld[x][y] == spieler) {
                    double dist = Math.sqrt(Math.pow(x - zielX, 2) + Math.pow(y - zielY, 2));
                    beste = Math.min(beste, dist);
                }
            }
        }
        return beste;
    }

    // Getter (nur lesend, für die GUI)
    public int getFeldZustand(int x, int y) { return feld[x][y]; }
    public int getSpielerAmZug() { return spielerAmZug; }
    public int getKugelnP1() { return kugelnP1; }
    public int getKugelnP2() { return kugelnP2; }
    public String getStatusMeldung() { return statusMeldung; }
    public boolean istSpielBeendet() { return spielBeendet; }
}
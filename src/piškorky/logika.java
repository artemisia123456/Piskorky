/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piškorky;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author Artemisia
 */
public class logika {

    private int[][] krizuky;
    private int pocetStlpcov;
    private int pocetRiadkov;
    private int dlzka;
    private int d = 1;

    logika() {

    }

    public logika(int pocetStlpcov, int pocetRiadkov, int dlzka) {
        this.pocetStlpcov = pocetStlpcov;
        this.pocetRiadkov = pocetRiadkov;
        this.dlzka = dlzka;
        krizuky = new int[pocetStlpcov][pocetRiadkov];

    }

    public void vykrasli(Graphics g, int sirka, int vyska) {
        double s = sirka / (double) pocetStlpcov;
        double v = vyska / (double) pocetRiadkov;
        g.setColor(Color.black);
        for (int i = 1; i < pocetStlpcov; i++) {
            g.drawLine((int) s * i, 0, (int) s * i, vyska);
        }

        for (int i = 1; i < pocetRiadkov; i++) {
            g.drawLine(0, (int) v * i, sirka, (int) v * i);
        }

        int stlpec = 0;
        int riadok = 0;
        for (int[] stlpce : krizuky) {
            for (int okienko : stlpce) {
                if (okienko == 2) {
                    g.setColor(Color.blue);
                    g.drawLine(stlpec * (int) s, riadok * (int) v, stlpec * (int) s + (int) s, riadok * (int) v + (int) v);
                    g.drawLine(stlpec * (int) s + (int) s, riadok * (int) v, stlpec * (int) s, riadok * (int) v + (int) v);
                    //System.out.println(riadok + " " + stlpec);
                } else if (okienko == 1) {
                    g.setColor(Color.red);
                    g.drawOval(stlpec * (int) s + 5, riadok * (int) v + 5, (int) s - 10, (int) v - 10);
                }

                riadok += 1;
            }
            riadok = 0;
            stlpec += 1;
        }

        g.drawLine(vyska, vyska, vyska, vyska);

    }

    public void click(int x, int y, int sirka, int vyska) {
        //x je 232, y74, 500x500
        double s = sirka / (double) pocetStlpcov;
        double v = vyska / (double) pocetRiadkov;

        int stlpec = (int) (x / s);
        int riadok = (int) (y / v);

        d++;

        if (d % 2 == 0) {
            krizuky[stlpec][riadok] = 2;
            this.skontroluj(2, stlpec, riadok);
        } else {
            krizuky[stlpec][riadok] = 1;
            this.skontroluj(1, stlpec, riadok);
        }

        // System.out.println(stlpec+ " "+ riadok);
    }

    public boolean skontroluj(int d, int stlpec, int riadok) {
        System.out.println(" ");
        int znak = 0;
        for (int s = 0; s < pocetStlpcov; s++) {
            if (krizuky[s][riadok] == d) {
                znak++;
            } else {
                if (znak == dlzka) {
                    System.out.println("win");
                    this.vyhral(d);
                    return true;
                }

                System.out.println(znak);
                znak = 0;
            }
        }
        if (znak == dlzka) {
            System.out.println("win");
            this.vyhral(d);
            return true;
        }
        znak = 0;
        for (int r = 0; r < pocetStlpcov; r++) {
            if (krizuky[stlpec][r] == d) {
                znak++;
            } else {
                if (znak == dlzka) {
                    System.out.println("win");
                    this.vyhral(d);
                    return true;
                }

                System.out.println(znak);
                znak = 0;
            }
        }
        if (znak == dlzka) {
            System.out.println("win");
            this.vyhral(d);
            return true;
        }

        int startR = Math.max(riadok - ((pocetStlpcov - 1) - stlpec), 0);
        int startS = Math.min(stlpec + riadok, pocetStlpcov - 1);
        znak = 0;
        for (;;) {
            if (krizuky[startS][startR] == d) {
                znak++;
            } else {
                if (znak == dlzka) {
                    System.out.println("win");
                    this.vyhral(d);
                    return true;
                }
                System.out.println(znak);
                znak = 0;
            }
            startR++;
            startS--;
            if (startR == pocetRiadkov || startS == -1) {
                break;
            }
        }
        if (znak == dlzka) {
            System.out.println("win");
            this.vyhral(d);
            return true;
        }

        startR = Math.max(riadok - stlpec, 0);
        startS = Math.max(stlpec - riadok, 0);
        znak = 0;
        for (;;) {
            if (krizuky[startS][startR] == d) {
                znak++;
            } else {
                if (znak == dlzka) {
                    System.out.println("win");
                    this.vyhral(d);
                    return true;
                }
                System.out.println(znak);
                znak = 0;
            }
            startR++;
            startS++;
            if (startR == pocetRiadkov || startS == pocetStlpcov) {

                break;
            }
        }
        if (znak == dlzka) {
            System.out.println("win");
            this.vyhral(d);
            return true;
        }

        return false;

    }

    public void vyhral(int i) {
        String vytaz = "";
        if (i == 1) {
            vytaz = "kruzok";
        } else {
            vytaz = "krizik";
        }
        int n = JOptionPane.showConfirmDialog(
                null, "Vyhral " + vytaz,
                "Koniec hry",
                JOptionPane.CANCEL_OPTION);

        krizuky = new int[pocetStlpcov][pocetRiadkov];
        d = 1;
    }

}

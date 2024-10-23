/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package piškorky;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Artemisia
 */
public class Panel extends javax.swing.JPanel implements MouseListener  {

    private logika doska;
    private Client client;

    /**
     * Creates new form Panel
     */
    Panel() {
        initComponents();
        doska = new logika(3, 3,3);
        client = new Client (this);
        addMouseListener(this);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.vykresli(g);
    }

    public void vykresli(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        doska.vykrasli(g, this.getWidth(), this.getHeight());
    }

    public void prekresli() {
        Graphics g = this.getGraphics();
        this.vykresli(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseClicked(MouseEvent e) {
        doska.click(e.getX(), e.getY(), this.getWidth(), this.getHeight());
        this.prekresli();
        client.odosli(e.getX() + " " + e.getY() );
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void click (String a){
        String[] split = a.split(" ");
        int x = Integer.parseInt(split [0]);
        int y = Integer.parseInt(split [1]);
        
        doska.click(x, y, this.getWidth(), this.getHeight());
        this.prekresli();
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package piškorky;

/**
 *
 * @author Artemisia
 */
public class Piškorky {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Kreslenie piskorky = new Kreslenie ();
        piskorky.setVisible(true);
        Thread.sleep(2000);
        piskorky.getPanel1().prekresli();
        
        
        
    }
    
}

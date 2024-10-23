/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piškorky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chose
 */
public class Client {

    private Socket socket;
    private OutputStream stream; //niečo kde posielam data
    private BufferedReader input;
    private Panel panel;
    
    
    Client(Panel panel){
        
        try {
            socket = new Socket("localhost", 25555); //názov IPčky a port - dačo čo ma pridelene každá aplikácia
            
            stream = socket.getOutputStream();
            run();
            System.out.println("pripojene");
            
             
            
            
            
        } catch (IOException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("nepodarilo sa");
        }
        this.panel = panel;
        
    }
    
    public void run (){
         Thread t = new Thread(){
            @Override
            public void run() {
                input();
            }
        };
        t.start();
    }
    
    public void input(){
        try {
            while(true){
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                panel.click(input.readLine());
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
    public void odosli (String s){
         PrintWriter output = new PrintWriter(stream, true);
         output.println(s);
    }
    
    
    
}
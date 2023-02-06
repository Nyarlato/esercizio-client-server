/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainclient;

/**
 *
 * @author vicbo
 */
public class MainClient {
    
    /**
     * Metodo statico per l'avvio della classe.
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        Client c1 = new Client("MC", "\u001b[34;1m");
        c1.connetti("localhost", 2000);
        boolean continua = true;
        //SOLO IL CLIENT PUO' CHIUDERE LA COMUNICAZIONE con "Addio"
        while(continua){
            continua = c1.scrivi();
            if(continua){
                c1.leggi();         
            }            
        }
        c1.chiudi();        
    }
    
}
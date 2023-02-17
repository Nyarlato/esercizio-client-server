/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainserver;

/**
 *
 * @author Ilenia Pinna
 */

//Questa classe serve per attivare i thread della classe server
public class MainServer {
   
//Metodo statico per l'avvio della classe. Il server sta sulla porta 2000
    public static void main(String[] args) {
        Server s = new Server(2000);  
        if (s != null) {
                s.attendi();   //attende che il client gli invia un messaggio
                s.leggi();     //legge il messaggio
                s.scrivi();    //scrive il messaggio
                s.chiudi();    //chiude la connessione
            }
    }
}

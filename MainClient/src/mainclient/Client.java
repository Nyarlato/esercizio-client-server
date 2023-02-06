/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainclient;

/**
 *
 * @author vicbo
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MC
 * @version dic 2022
 */
public class Client{
    String nome;
    String colore;
    Socket connection;
    OutputStream os;
    
    //Valore per il reset del colore di output
    public static final String RESET = "\u001B[0m";
    
   public Client(String nomeDefault, String coloreDefault){
       nome = nomeDefault;
       colore = coloreDefault;
   }
   
   public void connetti(String nomeServer, int portaServer){
       
        try {
           connection = new Socket(nomeServer, portaServer);
           System.out.println("-----------------------------------------");
           System.out.println(this.colore + "Connessione avvenuta con il server!");
           System.out.println("-----------------------------------------");
           System.out.println(this.colore + "CHAT APERTA CON IL SERVER !!!");
           
        } 
        catch(UnknownHostException ex){
            
            System.err.println("Errore DNS!");
        }
        catch (IOException ex) {
            
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Errore di comunicazione con la scheda di rete");
        }
   
   }
   
  public boolean scrivi(){
        //input da tastiera
        String messaggio="";
        System.out.print("Invia: ");
        BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in));
        try {
            messaggio = br.readLine();
           
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            //Scrittura sullo stream di output
            PrintWriter out =
                new PrintWriter(
                new BufferedWriter(
                new OutputStreamWriter(
                connection.getOutputStream())),true);
            //Invio del messaggio al server
            out.println(messaggio);
            out.flush();
        }
        catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Errore nella scrittura e/o nell'invio dei dati al server!");
        }
        
        if(messaggio.equals("Addio")){
            
            return false;
        }else{
            
            return true;
        }          
   }
    public void leggi(){
        
        String messaggioRicevuto="";
        try {
            BufferedReader br = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
            messaggioRicevuto = br.readLine();
            System.out.println("Ricevuto: " + messaggioRicevuto);
        } catch (IOException ex) {
          System.out.println(ex.toString());
        }
        
    }           
   
   public void chiudi(){
        
        try {
             if (connection!=null)
                    connection.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("La connessione con il server è stata chiusa e/o l'applicazione verrà terminata!");
   }   
   
}

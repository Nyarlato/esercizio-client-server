/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainserver;

/**
 *
 * @author vicbo
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MC
 * @version dic 2022
 */
public class Server{
    ServerSocket connection;
    Socket clientSocket;
    int porta;
    InputStream is;
    OutputStream os;
    
    public Server(int porta){
        this.porta = porta;
        try {
            connection = new ServerSocket(porta);
        } catch (BindException ex) {
           System.err.println(ex.toString());
        }catch(IOException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }
    
    
    public void attendi(){
        
        try {
            clientSocket = connection.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leggi(){
       System.out.println("attesa di lettura");
        String messaggioRicevuto;
        
        
        try {
            System.out.println("attesa di lettura");
        BufferedReader br = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream()));
            messaggioRicevuto = br.readLine();
        System.out.println("CLIENT: " + messaggioRicevuto);
        } catch (Exception ex) {
            System.out.println("attesa di lettura");
           System.out.println(ex.toString());
        }
        System.out.println("attesa di lettura");
    }
    public void scrivi(){
        //input da tastiera
        String messaggio="";
        System.out.println("scrivi messaggio");
        BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in));
        try {
            messaggio = br.readLine();
           
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            //Scrittura sullo stream di output
            os = clientSocket.getOutputStream();
            os.write(messaggio.getBytes());
            //Invio del messaggio al server
            os.flush();
            System.out.println("si");
        }
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Errore nella scrittura e/o nell'invio dei dati al server!");
        }
        
   }
     public void chiudi(){
        
        try {
             if (connection!=null)
                    connection.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("La connessione con il server è stata chiusa e/o l'applicazione verrà terminata!");
   }   
   
        
}

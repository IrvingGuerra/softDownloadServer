/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static colors.colors.ANSI_BLUE;
import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RESET;
import static colors.colors.ANSI_YELLOW;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


/**
 *
 * @author irvingguerra
 */
public class ServidorMulticast extends Thread{
 
    /* Variables*/
    
    public static final String MCAST_ADDR  = "228.1.1.1";
    public static final int MCAST_PORT = 9014;
    public static final int DGRAM_BUF_LEN = 1024;
    
    InetAddress group =null;

    public void run(){
        System.out.print( ANSI_GREEN + "[OK] "+ANSI_RESET+" Servidor Multicast Iniciado. ");
        try{
            group = InetAddress.getByName(MCAST_ADDR);
            while(true){
               //System.out.println( ANSI_YELLOW + "[SENDING] "+ANSI_RESET+" Enviando mensaje...");
                send("HereIAm");
                try{
                    Thread.sleep(5000);
                } 
                catch(InterruptedException ex) 
                {
                    Thread.currentThread().interrupt();
                }
                
            }        
        }catch(IOException e){
            e.printStackTrace();
            System.exit(2);
        }
    }
     
    public static void main(String[] args) {
        try{
	    ServidorMulticast servidorM = new ServidorMulticast();
	    servidorM.start();
	}catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Boolean send(String msg){
        try{
            MulticastSocket socketEnvio = new MulticastSocket(MCAST_PORT);
            socketEnvio.joinGroup(group); // se configura para escuchar el paquete
            DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.length(),group,MCAST_PORT);
            socketEnvio.send(packet);
            //System.out.println( ANSI_BLUE + "[SEND]"+ANSI_RESET+" Instruccion enviada: "+msg);
            socketEnvio.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
}


import static colors.colors.ANSI_GREEN;
import static colors.colors.ANSI_RESET;
import static colors.colors.ANSI_YELLOW;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author irvingguerra
 */
public class initServidores extends Thread{

    ServidorMulticast ServidorMulticast = new ServidorMulticast();
    ServidorRMI ServidorRMI = new ServidorRMI();
    ServidorUnicast ServidorUnicast = new ServidorUnicast();
    
    public initServidores() {
        System.out.println( ANSI_GREEN + "[ INIT ] "+ANSI_RESET+" initServidores Iniciando...");
        System.out.print( ANSI_YELLOW + "[ INFO ] "+ANSI_RESET+" Iniciando Servidor Multicast. ");
        System.out.print( ANSI_YELLOW + "[ INFO ] "+ANSI_RESET+" Iniciando Servidor RMI. ");
        System.out.println( ANSI_YELLOW + "[ INFO ] "+ANSI_RESET+" Iniciando Servidor Unicast. ");
        ServidorMulticast.start();
        ServidorRMI.start();
        ServidorUnicast.start();
    }
    
    public static void main(String[] args) {
        try{
	    initServidores servidores = new initServidores();
	    servidores.start();
	}catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}

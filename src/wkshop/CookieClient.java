package wkshop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CookieClient {
    
    public static void main(String[] args) {
        
        String portNumber = ""; 
        if(args.length > 0) {
            portNumber = args[0];
        } else {
            System.err.println("Invalid arguments input");
        }

        try {
            Socket s = new Socket("localhost", Integer.parseInt(portNumber));

            InputStream is = s.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            OutputStream os = s.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            
            Console cons = System.console();
            String cookie = "";
            String consoleInput = "";
            while(!consoleInput.toLowerCase().equals("quit")){
                consoleInput = cons.readLine("Enter '1' to request for a cookie ('quit' to terminate): ");

                dos.writeUTF(consoleInput);
                dos.flush();

                cookie = dis.readUTF();
                System.out.println(cookie);

            }
            

        } catch (Exception e) {
        }
    }
}

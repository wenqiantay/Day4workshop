package wkshop;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws NumberFormatException, IOException {
        
        ExecutorService es = Executors.newFixedThreadPool(3);
        //args[0] -> port number
        //args[1] -> directory

        String portNumber = "";
        String dirPath = "";
        String fileName = "";

        if(args.length > 0) {
            portNumber = args[0];
            dirPath = args[1];
            fileName = args[2];
        } else {
            System.err.println("Invalid number of arguments");
            System.exit(0);
        }

        File newDirectory = new File(dirPath);
        //if the directory dont exist, make a new directory
        if(!newDirectory.exists()) {
            newDirectory.mkdir();
        }

        //read and print cookies
        //c.printCookies();
        
        
        //day 4 slide 8
        ServerSocket ss = new ServerSocket(Integer.parseInt(portNumber));
        //open up the port to listen for imput
        
        //System.out.printf("Websocket server started on port... %s\r\n", portNumber);
        System.out.println("Waiting for connection...");
        
        while(true){
            Socket s = ss.accept();
            System.out.println("Connected!");
            Cookie c = new Cookie();
            c.readCookieFile(dirPath + File.separator + fileName);
            CookieClientHandler handler = new CookieClientHandler(s, c);
            es.submit(handler);
            

        }
        
        // //day4 slide 9
        // try {
        //     InputStream is = s.getInputStream();
        //     BufferedInputStream bis = new BufferedInputStream(is);
        //     DataInputStream dis = new DataInputStream(bis);

        //     String msgReceived = "";

        //     OutputStream os = s.getOutputStream();
        //     BufferedOutputStream bos = new BufferedOutputStream(os);
        //     DataOutputStream dos = new DataOutputStream(bos);

        //     while (!msgReceived.toLowerCase().equals("quit")) {
        //         System.out.println("Waiting for client input...");
        //         msgReceived = dis.readUTF(dis);
        //         //do something to serve the cookie 
        //         String retrievedCookie = c.getRandomCookie();

        //         //put it to the DataOutputStream
        //         dos.writeUTF(retrievedCookie);
        //         dos.flush();
                    
        //     }

        //     dos.close();
        //     bos.close();
        //     os.close();

        //     dis.close();
        //     bis.close();
        //     is.close();

            
        // } catch (EOFException e) {
        //     System.err.println(e.toString());

        // }
    }

}
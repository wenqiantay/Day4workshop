package wkshop;

import java.io.*;
import java.net.Socket;


public class CookieClientHandler implements Runnable {

    private Socket s;
    private Cookie c;

    
    public CookieClientHandler(Socket sock, Cookie cookie) {
        this.s = sock;
        this.c= cookie;
    }

    @Override
    public void run(){

        try {
            InputStream is = s.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String msgReceived = "";

            OutputStream os = s.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            while (!msgReceived.toLowerCase().equals("quit")) {
                System.out.println("Waiting for client input...");
                msgReceived = dis.readUTF(dis);
                //do something to serve the cookie 
                String retrievedCookie = c.getRandomCookie();

                //put it to the DataOutputStream
                dos.writeUTF(retrievedCookie);
                dos.flush();
                    
            }

            dos.close();
            bos.close();
            os.close();

            dis.close();
            bis.close();
            is.close();

            
        } catch (Exception e) {
            System.err.println(e.toString());

        }

    }

    
 }

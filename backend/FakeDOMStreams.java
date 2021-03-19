

import java.util.*;
import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FakeDOMStreams extends Thread{
    public String filePath = "D:\\stonks\\MDFF_CME_20130714-20130715_7818_0";
    private File DOMFile = new File(filePath);
    private int port;
    private String serverName;
    private ServerSocket serverSocket;
    

    public FakeDOMStreams(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while(true){
            try{
                System.out.println("waiting for clients . . . ");
                Socket DOMServer = serverSocket.accept();

                System.out.println("client connected from " + DOMServer.getRemoteSocketAddress());
                DataInputStream DOMin = new DataInputStream(DOMServer.getInputStream());

                //String ticker = in.readUTF();
                DataOutputStream out = new DataOutputStream(DOMServer.getOutputStream());
                
                DOMParser p = new DOMParser(DOMFile, 1);
                
                //sendTradeData(out);
            }
            catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }



    public static void main(String [] args) {
        int port = Integer.parseInt(args[0]);
        try {
            Thread t = new FakeDOMStreams(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
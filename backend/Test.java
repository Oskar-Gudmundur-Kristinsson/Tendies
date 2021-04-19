import java.util.*;
import java.util.Date;
import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.text.*;

public class Test {

    public static void main(String [] args) {
        try {
            Socket client = new Socket("localhost", 6666);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
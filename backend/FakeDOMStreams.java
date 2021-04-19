import java.util.*;
import java.util.Date;
import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.text.*;
import java.time.format.DateTimeFormatter;

public class FakeDOMStreams extends Thread{
    public String filePath = "C:\\Users\\Vala\\AndroidStudioProjects\\Tendies\\MDFF_CME_20130714-20130715_7817_0";
    private File DOMFile = new File(filePath);
    private int port;
    private String serverName;
    private ServerSocket serverSocket;
    

    public FakeDOMStreams(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }
    /*
        Date date = Calendar.getInstance().getTime();
        DateFormat dF = new SimpleDateFormat("yyyyMMdd");
        String strDate = dF.format(date);
        System.out.println(strDate + FIXTimeStamp.substring(8,15));
        LocalDateTime FIXDate = LocalDateTime.parse(strDate + FIXTimeStamp.substring(8,15));
    */
    public Instant toEpoc(String FIXTimeStamp){
        Date date = Calendar.getInstance().getTime();
        DateFormat dF = new SimpleDateFormat("yyyyMMdd");
        String strDate = dF.format(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.US);
        LocalDateTime FIXDate = LocalDateTime.parse(strDate + FIXTimeStamp.substring(8,14), formatter);
        Instant ins = FIXDate.atZone(ZoneId.systemDefault()).toInstant().plus(Long.parseLong(FIXTimeStamp.substring(14,17)),ChronoUnit.MICROS);
        /*long ret = ins.toEpochMilli();
        ret = ret + Long.parseLong(FIXTimeStamp.substring(14,17));*/
        return ins;// ret;
    }

     

    public void run() {
        while(true){
            try{
                System.out.println("waiting for clients . . . ");
                Socket DOMServer = serverSocket.accept();

                System.out.println("client connected from " + DOMServer.getRemoteSocketAddress());

                DataOutputStream out = new DataOutputStream(DOMServer.getOutputStream());
                System.out.println("going in ");
                DOMParser p = new DOMParser(DOMFile, 1);
                
                while(p.isSorted == false){
                    //do nothing
                }
                System.out.println("came out");
                //System.out.println(Long.toString(toEpoc(p.sortedData[1])));
                
                Date date = Calendar.getInstance().getTime();
                int indexClosestToNow = 0;
                for(int i = 0; i < p.sortedData.length; i++){
                    if((indexClosestToNow < -1) && (toEpoc(p.sortedData[i]).toEpochMilli() > date.getTime())){
                        indexClosestToNow = i;
                    }
                }

                System.out.println(Integer.toString(indexClosestToNow));
                
                date = Calendar.getInstance().getTime();
                for(int i = indexClosestToNow; i < p.sortedData.length; i++){
                    while(toEpoc(p.sortedData[i]).toEpochMilli()-date.getTime() > 0){
                        date = Calendar.getInstance().getTime();
                    }
                    System.out.println(p.sortedData[i]);
                    out.writeUTF(p.sortedData[i]);
                }
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
import java.util.*;
import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DOMParser{

    public List<String> sendOrder = new ArrayList<>();
    public String[] sortedData;
    public boolean isSorted = false;

    public DOMParser(File f,int mode){ //mode 1 for DOMveiw 2 for change in last traded price
        if(mode == 1){
            sendTradeData(f);
        }
    }
    
    public void sendTradeData(File f){
        try{
            Scanner readDOM = new Scanner(f);
            while(readDOM.hasNextLine()){
                String data = readDOM.nextLine();
                data = timeValue(data);
                //System.out.println(data +" std");
                if(data != ""){
                    sendOrder.add(data);
                }
            }
            readDOM.close();
        }catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }
        sortData();
    }

    public String timeValue(String s){
        //might as well delete entries we dont need
        String payloadTag = "269=";
        int index = s.indexOf(payloadTag);
        if(index == -1){
            return "";
        }
        else if(Character.getNumericValue(s.charAt(index+4)) < 3){//check if the char after 269 is 0 1 or 2
            String tag = "52=";
            String ret = s.substring(s.indexOf(tag)+3);
            int i = 0;
            //System.out.println(ret+ " tv");
            while(i < ret.length()){
                if(Character.getNumericValue(ret.charAt(i)) == -1){
                    //System.out.println(String.valueOf(ret.charAt(i)) + " char at");
                    return (ret.substring(0,i)) + "!t!"+ s;
                }
                i++;
            }
        }
        return "";
    }

    public void sortData(){
        sortedData = new String [sendOrder.size()];
        sendOrder.toArray(sortedData);
        Arrays.sort(sortedData);
        isSorted = true;
    }

    public static void main(String [] args){
        File f = new File("D:\\stonks\\MDFF_CME_20130714-20130715_7817_0");
        DOMParser p =new DOMParser(f,1);
        for(int i= 0; i < 5; i++){
            System.out.println(p.sortedData[i]);
        }
    }
}

import java.util.*;
import java.net.*;
import java.io.*;


public class price {
    List<double[]> priceList = new ArrayList<double[]>();

    public price(List<double[]> openPrice){
        priceList = openPrice;
    }

    public synchronized void updatePrice(double price, double volume){
        for(double[] priceEntry: priceList){
            if(priceEntry[0] == price){
                priceList.set(priceList.indexOf(priceEntry),{price,volume+priceEntry[1]});
                break;
            }
        }
    }
}
import java.util.*;
import java.net.*;
import java.io.*;


public class price {
    private double tick;
    private double [][] priceArr;

    public price(double tickSize, double[][] openPrice){
        tick = tickSize;
        priceArr = openPrice;
    }

    public synchronized void updatePrice(double price, double volume){
        
    }
}
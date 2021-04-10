package subsetsum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import  java.io.BufferedReader;
import java.util.Scanner;

/**
 * Reads the grocery list
 *
 * @author CS1C, Foothill College, Marian Zlateva
 * @version 2.0
 */
public class GroceriesFileReader {

    /**
     * Reads Grocery list and returns an arrayList of the Doubles in the list
     * @param fileName used to read the filename
     * @return a list of grocery prices
     */
    public static ArrayList<Double> readFile(String fileName) {
        //reads file
        ArrayList<Double>priceList = new ArrayList<Double>();
        File shoppingList = new File(fileName);

        Scanner fileScan;

        try{
            fileScan = new Scanner(shoppingList);
        }catch(FileNotFoundException e){
            return new ArrayList<Double>();
        }

        while(fileScan.hasNextLine()){
            String line = fileScan.nextLine();
            int commaIndex = line.indexOf(",");

            line = line.substring(commaIndex+1);

            double price = Double.parseDouble(line);
            priceList.add(price);
        }

        return priceList;
    }


}

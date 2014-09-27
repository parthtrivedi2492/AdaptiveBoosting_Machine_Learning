/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AdaboostRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parth
 */
public class AdaboostRunner {

    /**
     * @param args the command line arguments
     */
    //Below method is main method for project.
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        ArrayList<ArrayList<Double>> arr=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> arr1=new ArrayList<ArrayList<Double>>();
        arr=readFile(args[0]);
        arr1=readFile(args[0]);
    
        System.out.println("Binary Adaptive Boosting:");
        System.out.println("--------------------------------------------------------");
        BinaryAdaptiveBoostingMethod(arr);
        System.out.println("--------------------------------------------------------");
        System.out.println("Real Adaptive Boosting:");
        System.out.println("--------------------------------------------------------");
        RealAdaptiveBoostingMethod(arr1);
           
    }
    
    //Below method reads data from file.
    public   static ArrayList<ArrayList<Double>> readFile(String dataFile) throws FileNotFoundException{
//        String[][] recieverArray=new String[20][2];
    BufferedReader in = null;
    ArrayList<ArrayList<Double>> arr=new ArrayList<ArrayList<Double>>();
    ArrayList<Double> tem;
    
    String[] temp=new String[40];
            int i=0;
            int m=0;
        
             
            //System.out.println("got here");
            
            
            in = new BufferedReader(new FileReader(new File(".\\"+dataFile)));
            int line = 0;
        try {
            
            
            
            for (String x = in.readLine(); x != null ; x = in.readLine()) {
                line++;
                tem=new ArrayList<>();
                
                String[] s=x.split(" ");
                
                if(line>0){
                    for (String item : s) {
                        //a[line-1][k]=Integer.parseInt(s[k+1]);
                        double mi = Double.parseDouble(item);
                        //System.out.println(a[line-1][k]+"line:"+line+" K:"+k);
                        tem.add(mi);
                    }
            arr.add(tem);
            
                
            }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(AdaboostRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
                return arr;
        
    }
    
    //Below method calls Binary adaptive boosting class for calculating binary adaptive boosting.
    public static void BinaryAdaptiveBoostingMethod(ArrayList<ArrayList<Double>> separationInput){
        
        BinaryAdaptiveBoosting binaryBoosting=new BinaryAdaptiveBoosting(separationInput);
        
        int iteration;
        iteration=binaryBoosting.iterationFinder();
        
        for(int i=0;i<iteration;i++){
        System.err.println("Iteration:"+i);
        binaryBoosting.positiveClassifierCalculator();
        binaryBoosting.negativeClassifierCalculator(); 
        binaryBoosting.finalErrorCalculator();
        binaryBoosting.alphaCalculator();
        binaryBoosting.zeeCalculator();
        binaryBoosting.qCalculator();
        binaryBoosting.probUpdater();
        binaryBoosting.classifier();
        binaryBoosting.boostedErrorFinder();
        System.out.println("------------------------------------");
        }
    };
   
    //Below method calls Real adaptive boosting class for calculating binary adaptive boosting.
    public static void RealAdaptiveBoostingMethod(ArrayList<ArrayList<Double>> separationInput){
        
        BinaryAdaptiveBoosting binaryBoosting=new BinaryAdaptiveBoosting(separationInput);
        RealAdaptiveBoosting realBoosting=new RealAdaptiveBoosting(separationInput);
        
        int iteration;
        iteration=realBoosting.iterationFinder();
        
        for(int i=0;i<iteration;i++){
        System.err.println("Iteration:"+i);
        realBoosting.classifier();
        realBoosting.lessThanClassifierCalculator();
        realBoosting.graterThanClassifierCalculator();
        realBoosting.geeCalculator();
        realBoosting.finalGeeCalculator();
        realBoosting.cPlusCalculator();
        realBoosting.cMinusCalculator();
        realBoosting.qCalculator();
        realBoosting.probUpdater();
        realBoosting.boostedErrorFinder();
        System.out.println("------------------------------------");
        }
        
        
    };
    
}

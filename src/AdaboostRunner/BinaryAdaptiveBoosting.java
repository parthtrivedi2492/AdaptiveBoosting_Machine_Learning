/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AdaboostRunner;

import java.util.ArrayList;

/**
 *
 * @author Parth
 */

//This class implements Binary Adaptive boosting algorithm.

public class BinaryAdaptiveBoosting {

        double ebsilon;
        ArrayList<Double> inputX=new ArrayList<Double>();
        ArrayList<Double> inputY=new ArrayList<Double>();
        ArrayList<Double> inputProb=new ArrayList<Double>();
        ArrayList<Double> errorPositiveArray=new ArrayList<>();
        ArrayList<Double> errorNegativeArray=new ArrayList<>();
        ArrayList<Double> classifierFinder=new ArrayList<>();
        ArrayList<Double> boostedArray=new ArrayList<>();
        ArrayList<Double> q=new ArrayList<>();
        double alpha;
        double zee;
        double finalError;
        double temp;
        double bound=1;
        int finalIndex;
        int sign;
        int iteration;
        int boostedError;
        String s;

    BinaryAdaptiveBoosting(ArrayList<ArrayList<Double>> separationInput){
        ebsilon=separationInput.get(0).get(2);
        temp=separationInput.get(0).get(0);
        iteration=(int)temp;
        inputX=separationInput.get(1);
        inputY=separationInput.get(2);
        inputProb=separationInput.get(3);
        
        
        for(int i=0;i<inputY.size();i++){
            double d=0;
            boostedArray.add(d);
        }
        
        //System.out.println("ebsilon"+ebsilon);
        //System.out.println("inputX"+inputX);
        //System.out.println("inputY"+inputY);
        //System.out.println("inputProb"+inputProb);
           
        }
    
    //Below method reads input from text file and seperate input.
    public void inputSeparater(ArrayList<ArrayList<Double>> separationInput){
        
        temp=separationInput.get(0).get(0);
        iteration=(int)temp;
        ebsilon=separationInput.get(0).get(2);
        inputX=separationInput.get(1);
        inputY=separationInput.get(2);
        inputProb=separationInput.get(3);
        
    }
    
    //Below method calculates positive classifier.
    public void positiveClassifierCalculator(){
        
        //ArrayList<Double> errorPositiveArray=new ArrayList<>();
        
       
        //errorPositive is wrongly classifed example where left side is positive and right side is negative.
        //errorNegative is wrongly classifed example where left side is negative and right side is positive.
        
       
        
        for(int index=0;index<inputY.size()-1;index++){
        double errorPositive=0;
        for(int i=0;i<inputY.size();i++){
            
            if(i<=index){
              
                if(inputY.get(i)!=1){
                    errorPositive+=inputProb.get(i);
                    //System.out.println("i"+i+" inputY(i)"+inputY.get(i)+" errorPositive"+errorPositive);
                }
                
            }
            else
            {
              if(inputY.get(i)!=-1){
                    errorPositive+=inputProb.get(i);
                    //System.out.println("i"+i+" inputY(i)"+inputY.get(i)+" errorPositive"+errorPositive);
                }
            }
        }
        errorPositiveArray.add(errorPositive);
        }
        
        //System.out.println("Positive error"+errorPositiveArray);
        
     
    }
    
    //Below method calculates negative classifier.
    public void negativeClassifierCalculator(){
        
      
        
       
        //errorPositive is wrongly classifed example where left side is positive and right side is negative.
        //errorNegative is wrongly classifed example where left side is negative and right side is positive.
        
       
        for(int index=0;index<inputY.size();index++){
        double errorNegative=0;
        for(int i=0;i<inputY.size();i++){
            
            if(i<index){
                if(inputY.get(i)!=-1){
                    errorNegative+=inputProb.get(i);
                }
            }
            else{
                if(inputY.get(i)!=1){
                    errorNegative+=inputProb.get(i);
                }
            }
                
        }
        errorNegativeArray.add(errorNegative);
        }
        
       //System.out.println("Negative error"+errorNegativeArray);
        
      
    }
     
    //Below method calculates alpha for data.
    public void alphaCalculator(){
        
        
        alpha=(Math.log((1-finalError)/finalError))/2;
        
        System.out.println("The error of ht:"+finalError);
        System.out.println("The weight of ht: αt:"+alpha);
        
      
    }
    
    //Below method calculates zee.
    public void zeeCalculator(){
        
        
        zee=2*(Math.sqrt((1-finalError)*finalError));
        
        System.out.println("The probabilities normalization factor: Zt.:"+zee);
        
        
    }
   
    //Below method calculates final error.
    public void finalErrorCalculator(){
        
         double positiveError=errorPositiveArray.get(0);
         int positiveIndex=0;
        
        for(int i=0;i<errorPositiveArray.size();i++){
            if(positiveError>errorPositiveArray.get(i)){
                positiveError=errorPositiveArray.get(i);
                positiveIndex=i;
            }
        }
        
        double negativeError=errorNegativeArray.get(1);
        int negativeIndex=0;
        
        for(int i=1;i<errorNegativeArray.size();i++){
            if(negativeError>errorNegativeArray.get(i)){
                negativeError=errorNegativeArray.get(i);
                negativeIndex=i;
            }
        }
        
        if(positiveError<negativeError){
             finalError=positiveError;
             finalIndex=positiveIndex;
             sign=0;
         }
        else
         {
             finalError=negativeError;
             finalIndex=negativeIndex;
             sign=1;
         }
        Double temp;
        
        if(sign==0){
            temp=inputX.get(finalIndex)+0.5;
        System.out.println("Selected hypothysis:(X<"+temp+")");
        //System.out.println("sign:"+sign);
       // s+="+"+alpha+"(X<"+temp+")";
        }
        else{
           temp=inputX.get(finalIndex)-0.5;
        System.out.println("Selected hypothysis:(X>"+temp+")");
        //System.out.println("sign:"+sign); 
         //s+="+"+alpha+"(X>"+temp+")";
        }
        
        
        
        //System.out.println("The selected weak classifier: ht:");
        
        
        
    }
    
    //Below method calculates q.
    public void qCalculator(){
        
        
        double qVariable=0;
        
        if(sign==0){
        for(int i=0;i<inputY.size();i++){
            if(i<=finalIndex){
              
                if(inputY.get(i)!=1){
                    qVariable=Math.exp(alpha);
                     q.add(qVariable);
                    //System.out.println("i"+i+"q:"+qVariable);
                }
                else{
                    qVariable=Math.exp(-1*alpha);
                     q.add(qVariable);
                     //System.out.println("i"+i+"q:"+qVariable);
                }
                
                
            }
            else
            {
              if(inputY.get(i)!=-1){
                    qVariable=Math.exp(alpha);
                    q.add(qVariable);
                   // System.out.println("i"+i+"q:"+qVariable);
                    
                }
              else{
                    qVariable=Math.exp(-1*alpha);
                     q.add(qVariable);
                    // System.out.println("i"+i+"q:"+qVariable);
                }
              
              
            }
        }
        }
        
        else{
            
            for(int i=0;i<inputY.size();i++){
            
            if(i>=finalIndex){
              
                if(inputY.get(i)!=1){
                    qVariable=Math.exp(alpha);
                     q.add(qVariable);
                   // System.out.println("i"+i+"q:"+qVariable);
                    
                }
                else{
                    qVariable=Math.exp(-1*alpha);
                     q.add(qVariable);
                     //System.out.println("i"+i+"q:"+qVariable);
                }
                
            }
            else
            {
              if(inputY.get(i)!=-1){
                    qVariable=Math.exp(alpha);
                     q.add(qVariable);
                   // System.out.println("i"+i+"q:"+qVariable);
                    
                }
              else{
                    qVariable=Math.exp(-1*alpha);
                     q.add(qVariable);
                    // System.out.println("i"+i+"q:"+qVariable);
                }
            }
        }
        }
        
        
        
    }

    //Below method calculates probability.
    public void probUpdater(){
        
        double temp;
        
        for(int i=0;i<inputProb.size();i++){
            temp=inputProb.get(i);
            temp*=q.get(i);
            temp/=zee;
            inputProb.set(i, temp);
            System.out.println("The probabilities after normalization: pi:"+inputProb.get(i));
            
        }
        
        errorPositiveArray.clear();
        errorNegativeArray.clear();
        classifierFinder.clear();
        q.clear();
        
        bound*=finalError;
        
        if(sign==0){
            temp=inputX.get(finalIndex)+0.5;
        //System.out.println("Selected hypothysis:(X<"+temp+")");
        //System.out.println("sign:"+sign);
        s+="+"+alpha+"(X<"+temp+")";
        }
        else{
           temp=inputX.get(finalIndex)-0.5;
        //System.out.println("Selected hypothysis:(X>"+temp+")");
        //System.out.println("sign:"+sign); 
         s+="+"+alpha+"(X>"+temp+")";
        }
        
        System.out.println("Function:"+s);
        System.out.println("Bound:"+bound);
    }
    
    //Below method calculates boosted error.
    public void boostedErrorFinder(){
        
        double error=0;
        int flag;
        
        for(int i=0;i<inputY.size();i++){
            
            double temp1=boostedArray.get(i);
            
            temp1+=(alpha*classifierFinder.get(i));
            boostedArray.set(i, temp1);
            
            
            if(temp1>0){
                flag=1;
            }
            else{
                flag=-1;
            }
            
            if(flag!=inputY.get(i)){
                error++;
            }
            
        }
        
        error/=inputY.size();
        System.out.println("Boosted error:"+error);
        //System.out.println("Boosted array:"+boostedArray);
    }
   
    //Below method classifies given data.
    public void classifier(){
        
        double error=0;
        if(sign==0){
           
        for(int i=0;i<inputY.size();i++){
        
             if(i<=finalIndex){
                 error=1;
                 classifierFinder.add(error);
             }
             else{
                  error=-1;
                 classifierFinder.add(error);
             }
            
        }
        }
        
        else{
            
            for(int i=0;i<inputY.size();i++){
        
             if(i<finalIndex){
                 error=-1;
                 classifierFinder.add(error);
             }
             else{
                  error=1;
                 classifierFinder.add(error);
             }
            
        }
            
        }
            
        
        //System.out.println("Classifier is:"+classifierFinder);
        
    }
    
    //Below method finds number of iterations.
    public int iterationFinder(){
        
        return iteration;
    }
}

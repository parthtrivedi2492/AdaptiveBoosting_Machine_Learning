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

//This class implements Real Adaptive boosting algorithm.

public class RealAdaptiveBoosting {

    double ebsilon;
        ArrayList<Double> inputX=new ArrayList<Double>();
        ArrayList<Double> inputY=new ArrayList<Double>();
        ArrayList<Double> inputProb=new ArrayList<Double>();
        ArrayList<Double> positiveRightLessthanArray=new ArrayList<Double>();
        ArrayList<Double> positiveWrongLessthanArray=new ArrayList<Double>();
        ArrayList<Double> negativeRightLessthanArray=new ArrayList<Double>();
        ArrayList<Double> negativeWrongLessthanArray=new ArrayList<Double>();
        ArrayList<Double> positiveRightGraterthanArray=new ArrayList<Double>();
        ArrayList<Double> positiveWrongGraterthanArray=new ArrayList<Double>();
        ArrayList<Double> negativeRightGraterthanArray=new ArrayList<Double>();
        ArrayList<Double> negativeWrongGraterthanArray=new ArrayList<Double>();
        ArrayList<Double> zeeLessThan=new ArrayList<Double>();
        ArrayList<Double> zeeGraterThan=new ArrayList<Double>();
        ArrayList<Double> classifierFinder=new ArrayList<Double>();
        ArrayList<ArrayList<Double>> classifierLessthanFinder=new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> classifierGraterthanFinder=new ArrayList<ArrayList<Double>>();
        ArrayList<Double> boostedArray=new ArrayList<Double>();
        ArrayList<Double> q=new ArrayList<Double>();
        double alpha;
        double gee;
        double finalGee;
        double finalZee;
        double temp;
        double bound=1;
        double cPlus;
        double cMinus;
        int finalIndex;
        int sign;
        int iteration;
        int boostedError;
        String s;

    
        RealAdaptiveBoosting(ArrayList<ArrayList<Double>> separationInput){
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
        
        //Below method calculates less than classifier.
        public void lessThanClassifierCalculator(){
        
        //ArrayList<Double> errorPositiveArray=new ArrayList<>();
        
       
        //errorPositive is wrongly classifed example where left side is positive and right side is negative.
        //errorNegative is wrongly classifed example where left side is negative and right side is positive.
        
       
        
        for(int index=0;index<inputY.size();index++){
        double errorPositiveWrong=0;
        double errorNegativeWrong=0;
        double errorPositiveRight=0;
        double errorNegativeRight=0;
        for(int i=0;i<inputY.size();i++){
            
            if(i<=index){
              
                if(inputY.get(i)!=1){
                    errorNegativeWrong+=inputProb.get(i);
                    //System.out.println("i"+i+" inputY(i)"+inputY.get(i)+" errorPositive"+errorPositive);
                }
                else{
                    errorPositiveRight+=inputProb.get(i);
                }
                
            }
            else
            {
              if(inputY.get(i)!=-1){
                    errorPositiveWrong+=inputProb.get(i);
                    //System.out.println("i"+i+" inputY(i)"+inputY.get(i)+" errorPositive"+errorPositive);
                }
              else{
                    errorNegativeRight+=inputProb.get(i);
                }
            }
        }
        positiveRightLessthanArray.add(errorPositiveRight);
        negativeRightLessthanArray.add(errorNegativeRight);
        positiveWrongLessthanArray.add(errorPositiveWrong);
        negativeWrongLessthanArray.add(errorNegativeWrong);
        }
        
        //System.out.println("Positive error"+errorPositiveArray);
        
     
    }
  
        //Below method calculates grater than classifier.
        public void graterThanClassifierCalculator(){
        
        for(int index=0;index<inputY.size();index++){
        double errorPositiveWrong=0;
        double errorNegativeWrong=0;
        double errorPositiveRight=0;
        double errorNegativeRight=0;
        
        for(int i=0;i<inputY.size();i++){
            
            if(i<index){
                if(inputY.get(i)!=-1){
                    errorPositiveWrong+=inputProb.get(i);
                }
                else{
                    errorNegativeRight+=inputProb.get(i);
                }
                
            }
            else{
                if(inputY.get(i)!=1){
                    errorNegativeWrong+=inputProb.get(i);
                }
                else{
                    errorPositiveRight+=inputProb.get(i);
                }
            }
                
        }
        
        positiveRightGraterthanArray.add(errorPositiveRight);
        negativeRightGraterthanArray.add(errorNegativeRight);
        positiveWrongGraterthanArray.add(errorPositiveWrong);
        negativeWrongGraterthanArray.add(errorNegativeWrong);
        }
       
        
     
    }
        
        //Below method calculates final gee among all posible gee.
        public void finalGeeCalculator(){
        
         double positiveError=zeeLessThan.get(0);
         int positiveIndex=0;
        
        for(int i=0;i<zeeLessThan.size();i++){
            if(positiveError>zeeLessThan.get(i)){
                positiveError=zeeLessThan.get(i);
                positiveIndex=i;
            }
        }
        
        double negativeError=zeeGraterThan.get(0);
        int negativeIndex=0;
        
        for(int i=0;i<zeeGraterThan.size()-1;i++){
            if(negativeError>zeeGraterThan.get(i)){
                negativeError=zeeGraterThan.get(i);
                negativeIndex=i;
            }
        }
        
        if(positiveError<=negativeError){
             finalGee=positiveError;
             finalIndex=positiveIndex;
             sign=0;
         }
        else
         {
             finalGee=negativeError;
             finalIndex=negativeIndex;
             sign=1;
             
         }
        Double temp;
        finalZee=2*finalGee;
        
        if(sign==0){
            temp=inputX.get(finalIndex)+0.5;
        System.out.println("The selected weak classifier: ht:(X<"+temp+")");
        //System.out.println("sign:"+sign);
        //s+="+(X<"+temp+")";
        }
        else{
           temp=inputX.get(finalIndex)-0.5;
        System.out.println("The selected weak classifier: ht:(X>"+temp+")");
        //System.out.println("sign:"+sign); 
         //s+="+(X>"+temp+")";
        }
        
        System.out.println("G:"+finalGee);
        System.out.println("Z:"+finalZee);
        
        
        //System.out.println("The selected weak classifier: ht:"+finalIndex+" Sign:"+sign);
        
        
        
    }
        
        //Below method calculates gee.
        public void geeCalculator(){
            
            for(int i=0;i<inputY.size()-1;i++){
                double temp;
                
                temp=Math.sqrt(positiveRightLessthanArray.get(i)*negativeWrongLessthanArray.get(i))+Math.sqrt(positiveWrongLessthanArray.get(i)*negativeRightLessthanArray.get(i));
                zeeLessThan.add(temp);
                
            }
            
            for(int i=0;i<inputY.size()-1;i++){
                double temp;
                
                temp=Math.sqrt(positiveRightGraterthanArray.get(i)*negativeWrongGraterthanArray.get(i))+Math.sqrt(positiveWrongGraterthanArray.get(i)*negativeRightGraterthanArray.get(i));
                zeeGraterThan.add(temp);
                
            }
            
            //System.out.println("Zee Less:"+zeeLessThan);
            //System.out.println("Zee Geater:"+zeeGraterThan);
        }
        
        //Below method calculates cplus of data.
        public void cPlusCalculator(){
            
            double temp;
            if(sign==0){
            temp=(Math.log((positiveRightLessthanArray.get(finalIndex)+ebsilon)/(negativeWrongLessthanArray.get(finalIndex)+ebsilon)))/2;
            
                //System.out.println("Trmp"+temp);
            cPlus=temp;
            }
            else{
                temp=(Math.log((positiveRightGraterthanArray.get(finalIndex)+ebsilon)/(negativeWrongGraterthanArray.get(finalIndex)+ebsilon)))/2;
            
                //System.out.println("Trmp"+temp);
                 cPlus=temp;
            }
            
            System.out.println("Cplus:"+cPlus);
        }
        
        //Below method calculates cminus of data.
        public void cMinusCalculator(){
            
            double temp;
            if(sign==0){
            temp=(Math.log((positiveWrongLessthanArray.get(finalIndex)+ebsilon)/(negativeRightLessthanArray.get(finalIndex)+ebsilon)))/2;
            
                //System.out.println("Trmp"+temp);
                cMinus=temp;
            }
            else{
                temp=(Math.log((positiveWrongGraterthanArray.get(finalIndex)+ebsilon)/(negativeRightGraterthanArray.get(finalIndex)+ebsilon)))/2;
            
                //System.out.println("Trmp"+temp);
                cMinus=temp;
            }
            
            System.out.println("CMinus:"+cMinus);
        }

        //Below method calculates Q.
        public void qCalculator(){
        
       
        double qVariable=0;
        
        if(sign==0){
        for(int i=0;i<inputY.size();i++){
            
            if(i<=finalIndex){
              
                if(inputY.get(i)!=1){
                    qVariable=Math.exp(-1*inputY.get(i)*cPlus);
                     q.add(qVariable);
                    //System.out.println("i"+i+"q:"+qVariable);
                }
                else{
                    qVariable=Math.exp(-1*inputY.get(i)*cPlus);
                     q.add(qVariable);
                     //System.out.println("i"+i+"q:"+qVariable);
                }
                
                
            }
            else
            {
              if(inputY.get(i)!=-1){
                    qVariable=Math.exp(-1*inputY.get(i)*cMinus);
                    q.add(qVariable);
                   // System.out.println("i"+i+"q:"+qVariable);
                    
                }
              else{
                    qVariable=Math.exp(-1*inputY.get(i)*cMinus);
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
                    qVariable=Math.exp(-1*inputY.get(i)*cPlus);
                     q.add(qVariable);
         
                    
                }
                else{
                    qVariable=Math.exp(-1*inputY.get(i)*cPlus);
                     q.add(qVariable);
         
                }
                
            }
            else
            {
              if(inputY.get(i)!=-1){
                    qVariable=Math.exp(-1*inputY.get(i)*cMinus);
                     q.add(qVariable);
         
                    
                }
              else{
                    qVariable=Math.exp(-1*inputY.get(i)*cMinus);
                     q.add(qVariable);
         
                }
            }
        }
        }
        
         
        
    }
        
        //Below method updates probability.
        public void probUpdater(){
        
        double temp;
        
        for(int i=0;i<inputProb.size();i++){
            temp=inputProb.get(i);
            temp*=q.get(i);
            temp/=finalZee;
            inputProb.set(i, temp);
            System.out.println("The probabilities after normalization: pi:"+inputProb.get(i));
            
        }
        
        positiveRightLessthanArray.clear();
        positiveWrongLessthanArray.clear();
        negativeRightLessthanArray.clear();
        negativeWrongLessthanArray.clear();
        positiveRightGraterthanArray.clear();
        positiveWrongGraterthanArray.clear();
        negativeRightGraterthanArray.clear();
        negativeWrongGraterthanArray.clear();
        zeeLessThan.clear();
        zeeGraterThan.clear();
        q.clear();
        
        
         
        }
        
        //Below method finds boosted error.
        public void boostedErrorFinder(){
        
        double error=0;
        int flag;
        
        for(int i=0;i<inputY.size();i++){
            
            double temp1=boostedArray.get(i);
            
            
            if(sign==0){
                if(i<=finalIndex){
                    temp1+=cPlus;
                }
                else{
                    temp1+=cMinus;
                }
                
            }
            else{
                if(i>=finalIndex){
                    temp1+=cPlus;
                }
                else{
                    temp1+=cMinus;
                }
                
                
            }
            
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
            System.out.println("Boosted array"+boostedArray);
        System.out.println("Boosted error:"+error);
        
        bound*=finalZee;
        
        if(sign==0){
            temp=inputX.get(finalIndex)+0.5;
        System.out.println("Selected hypothysis:(X<"+temp+")");
        //System.out.println("sign:"+sign);
        s+="+(X<"+temp+")";
        }
        else{
           temp=inputX.get(finalIndex)-0.5;
        System.out.println("Selected hypothysis:(X>"+temp+")");
        //System.out.println("sign:"+sign); 
         s+="+(X>"+temp+")";
        }
        
        System.out.println("Function:"+s);
        System.out.println("Bound:"+bound);
        //System.out.println("Boosted array:"+boostedArray);
    }
        
        //Below method classify data.
        public void classifier(){
      
        double error=0;
        for(int sign=0;sign<2;sign++){
        for(int finalIndex=0;finalIndex<inputY.size();finalIndex++){
            ArrayList<Double> classifierFinder=new ArrayList<Double>();
        if(sign==0){
           
            if(finalIndex==inputY.size()-1){
                continue;
            }
            
            
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
        
        classifierLessthanFinder.add(classifierFinder);
        
        }
        
        else{
   
            if(finalIndex==0){
                continue;
            }
            
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
          
            classifierGraterthanFinder.add(classifierFinder);
            
            
        }
            
        
        
        
    }
        }
            
        }
        
        //Below method finds iteration of data.
        public int iterationFinder(){
        
        return iteration;
    }
           
}

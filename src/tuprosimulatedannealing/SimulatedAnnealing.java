/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuprosimulatedannealing;

import java.util.Random;

/**
 *
 * @author ghost
 */
public class SimulatedAnnealing {
    
    public static final double RATE_OF_COOLING      = 0.009;
    public static final double INITIAL_TEMPERATURE  = 1000;
    public static final double MIN_TEMPERATURE      = 0.99;
    
    public void findMinima(){
        double temperature              = INITIAL_TEMPERATURE;
        State newState                  = randomized();
        State currentState              = newState;
        double newFunc                  = Formula.countResult(currentState);
        double currFunc                 = newFunc;
        double bestOfFar                = newFunc;
        double bestX1                   = newState.getX1();
        double bestX2                   = newState.getX2();
        double randomNumber             = Math.random();
        double acceptanceProbability    = 1.0;
        
        while (temperature > MIN_TEMPERATURE) {
            
            if (newFunc < bestOfFar ) {
                currFunc        = newFunc;
                bestOfFar       = newFunc;
                currentState    = newState;
                bestX1          = currentState.getX1();
                bestX2          = currentState.getX2();
            }else {
                acceptanceProbability   = Math.exp((bestOfFar - newFunc)/temperature);
                if (acceptanceProbability > randomNumber) {
                    currFunc        = newFunc;
                }
            }
            
            System.out.print(String.format("%21s %5s", newState.getX1(), "|"));
            System.out.print(String.format("%21s %5s", newState.getX2(), "|"));
            System.out.print(String.format("%7s %2s", String.format("%.2f", temperature), "|"));
            System.out.print(String.format("%22s %4s", newFunc, "|"));
            System.out.print(String.format("%22s %4s", currFunc, "|"));
            System.out.print(String.format("%22s %4s", bestOfFar  , "|"));
            
            System.out.print(String.format("%5s %1s", String.format("%.2f", randomNumber), "|"));
            System.out.println(String.format("%5s %1s", String.format("%.2f", acceptanceProbability), "|"));
            
            newState   = randomized();
            randomNumber             = Math.random();
            newFunc    = Formula.countResult(newState);
//            if (acceptProb(newFunc, bestOfFar  , temperature)) {
////                bestSoFar = tempBestSoFar;
//                currentState    = newState;
////                currFunc        = newFunc;
//                bestX1          = currentState.getX1();
//                bestX2          = currentState.getX2();
//            }            
            temperature *= 1-RATE_OF_COOLING;
        }
        
        System.out.println("");
        System.out.println("----------------------------------------------");
        System.out.println("| Best so Far  : " + String.format("%23s %5s", bestOfFar, "|"));
        System.out.println("| Current Func : " + String.format("%23s %5s", currFunc, "|"));
        System.out.println("| x1           : " + String.format("%23s %5s", bestX1, "|"));
        System.out.println("| x2           : " + String.format("%23s %5s", bestX2, "|"));
        System.out.println("----------------------------------------------");
    }
    
     public State randomized(){
        double max      = 10.0d;
        double min      = -10.0d;
        Random ran      = new Random();
        double x1       = ran.nextDouble()* (max - min) + min;
        double x2       = ran.nextDouble()* (max - min) + min;
        
        State newState  = new State(x1, x2);
        return newState;
    }
    
//    public boolean acceptProb(double newFunc, double currentSolution, double temperature){
//        double acceptanceProbability    = 1.0;
//        String decision                 = null;
//        boolean lowerResult             = true;
//        boolean acceptResultFlag        = false;
//        double randomNumber             = Math.random();
//        
//        if (newFunc >= currentSolution) {
//            acceptanceProbability   = Math.exp((currentSolution - newFunc)/temperature);
//            lowerResult             = false;
//        }
//        if (acceptanceProbability > randomNumber) {
//            acceptResultFlag = true;
//        }
//        
//        System.out.print(String.format("%5s %1s", String.format("%.2f", randomNumber), "|"));
//        System.out.println(String.format("%5s %1s", String.format("%.2f", acceptanceProbability), "|"));
//        
//        
//        return acceptResultFlag;
//    }
    
   
}

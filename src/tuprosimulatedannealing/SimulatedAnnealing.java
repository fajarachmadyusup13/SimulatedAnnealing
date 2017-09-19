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
        double temperature      = INITIAL_TEMPERATURE;
        State newState          = randomized();
        State currentState      = newState;
        double newFunc          = Formula.countResult(newState);
        double currentFunc      = newFunc;
        
        while (temperature > MIN_TEMPERATURE) {
            
            System.out.print(String.format("%21s %5s", currentState.getX1(), "|"));
            System.out.print(String.format("%21s %5s", currentState.getX2(), "|"));
            System.out.print(String.format("%7s %2s", String.format("%.2f", temperature), "|"));
            System.out.print(String.format("%22s %4s", newFunc, "|"));
            System.out.print(String.format("%22s %4s", currentFunc, "|"));
            
            
            newState                = randomized();
            newFunc    = Formula.countResult(newState);
            
            if (newFunc < currentFunc) {
                currentFunc     = newFunc;
                currentState    = newState;
            }
            if (acceptProb(newFunc, currentFunc, temperature)) {
//                bestSoFar = tempBestSoFar;
                currentState = newState;
            }
            
            temperature *= 1-RATE_OF_COOLING;
        }
    }
    
    public boolean acceptProb(double newFunc, double currentSolution, double temperature){
        double acceptanceProbability = 1.0;
        String decision = null;
        boolean lowerResult = true;
        boolean acceptResultFlag = false;
        double randomNumber = Math.random();
        
        if (newFunc >= currentSolution) {
            acceptanceProbability = Math.exp((currentSolution - newFunc)/temperature);
            lowerResult = false;
        }
        if (acceptanceProbability > randomNumber) {
            acceptResultFlag = true;
        }
        
        System.out.print(String.format("%5s %1s", String.format("%.2f", randomNumber), "|"));
        System.out.println(String.format("%5s %1s", String.format("%.2f", acceptanceProbability), "|"));
        
        
        return acceptResultFlag;
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
}

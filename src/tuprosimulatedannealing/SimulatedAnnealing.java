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
        double temperature  = INITIAL_TEMPERATURE;
        State newState      = randomized();
        State currentState  = newState;
        double bestSoFar    = Formula.countResult(currentState);
        
        while (temperature > MIN_TEMPERATURE) {
            newState                = randomized();
            double tempBestSoFar    = Formula.countResult(newState);
            System.out.print(String.format("%21s %5s", currentState.getX1(), "|"));
            System.out.print(String.format("%21s %5s", currentState.getX2(), "|"));
            System.out.print(String.format("%7s %2s", String.format("%.2f", temperature), "|"));
            System.out.print(String.format("%22s %4s", bestSoFar, "|"));
            if (tempBestSoFar < bestSoFar) {
                bestSoFar       = tempBestSoFar;
                currentState    = newState;
            }
            if (acceptProb(tempBestSoFar, bestSoFar, temperature)) {
                bestSoFar = tempBestSoFar;
                currentState = newState;
            }
            
            temperature *= 1-RATE_OF_COOLING;
        }
        
        
        System.out.println("Best Of Far: "+ bestSoFar);
    }
    
    public boolean acceptProb(double tempBestSoFar, double bestSoFar, double temperature){
        double acceptanceProbability = 1.0;
        String decision = null;
        boolean lowerResult = true;
        boolean acceptResultFlag = false;
        
        if (tempBestSoFar >= bestSoFar) {
            acceptanceProbability = Math.exp(-(tempBestSoFar - bestSoFar)/temperature);
            lowerResult = false;
        }
        double randomNumber = Math.random();
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
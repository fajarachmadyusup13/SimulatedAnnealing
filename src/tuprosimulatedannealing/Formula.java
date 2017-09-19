/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuprosimulatedannealing;

/**
 *
 * @author ghost
 */
public class Formula {
    
    public static double countResult(State state){
        double x1 = state.getX1();
        double x2 = state.getX2();
        double result = ((4 - 2.1 * Math.pow(x1, 2D) + (Math.pow(x1, 4D)/3)) * Math.pow(x1, 2D)) + 
                        (x1 * x2) + ((-4 + 4 * Math.pow(x2, 2D)) * Math.pow(x2, 2D));
        return result;
    }
    
}

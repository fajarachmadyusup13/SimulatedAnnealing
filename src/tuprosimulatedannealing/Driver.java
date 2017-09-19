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
public class Driver {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        heading();
        new SimulatedAnnealing().findMinima();
        System.out.println("");
        System.out.println("");
    }
    
    public static void heading(){
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.print(String.format("%13s %13s", "x1", "|"));
        System.out.print(String.format("%13s %13s", "x2", "|"));
        System.out.print(String.format("%6s %3s", "Temp", "|"));
        System.out.print(String.format("%20s %6s", "Best So Far", "|"));
        System.out.print(String.format("%5s %1s", "Rand", "|"));
        System.out.println(String.format("%5s %1s", "Prob", "|"));
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }
    
}

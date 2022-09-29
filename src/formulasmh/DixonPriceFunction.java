/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class DixonPriceFunction extends Formula{

    public DixonPriceFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super(semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    
    @Override
    public double ejecucion(double vector[]) {
        
        double x1 = vector[0];
        double term1 = Math.pow(x1-1,2);
        double sum = 0;
        for (int i = 1; i<getDimension();i++){
            double xi = vector[i];
            double xold = vector[i-1];
            double nuevo = i*Math.pow(2*Math.pow(xi, 2)-xold,2);
            sum = sum + nuevo;
        }
        return term1+sum;
    }


    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class TridFunction extends Formula {

    public TridFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super( semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
        double sum1 = Math.pow(vector[0]-1, 2);
        double sum2=0;
        for (int i =1;i<getDimension();i++){
            sum1+=(Math.pow(vector[i]-1, 2));
            sum2+=vector[i]*vector[i-1];
        } 
        return sum1-sum2;
    }
    
    
}

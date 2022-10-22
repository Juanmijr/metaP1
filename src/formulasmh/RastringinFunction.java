/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class RastringinFunction extends Formula {

    public RastringinFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super(semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
        double prod = 1;
        double sum = 0;


        for (int i = 0; i < getDimension(); i++) {
            double x = vector[i];
            sum += Math.pow(x, 2)/4000;
            prod *= Math.cos(x/Math.sqrt(i+1));
        }
         

        return sum-prod+1;
    }

}

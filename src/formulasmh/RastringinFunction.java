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
        double sum = 0;


        for (int i = 0; i < getDimension(); i++) {
            double x = vector[i];
            sum += x*x - 10*Math.cos(2*Math.PI*x);
        }
         

        return 10* getDimension() + sum;
    }

}

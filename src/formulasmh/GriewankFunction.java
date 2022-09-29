/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class GriewankFunction extends Formula {

    public GriewankFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super( semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    

    
    @Override
    public double ejecucion( double vector[]) {

        double p1=0;
        double p2=1;
        for (int i = 1; i <= getDimension(); i++) {
            p1 += ((Math.pow(numAleatorio(getRangoMin(),getRangoMax()),2))/4000);
            p2 *= Math.cos((numAleatorio(getRangoMin(), getRangoMax()))/Math.sqrt(i));
        }

        return p1 - p2 + 1;

    }

}

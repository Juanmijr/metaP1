/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class PermFunction extends Formula {

    public PermFunction( long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super( semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
        double resul = 0;
        double b = 0.5;
        for (int i = 1; i <= getDimension(); i++) {
            double p1 = 0;
            for (int j = 1; j < getDimension(); j++) {
                // MIRAR LA BETA
                double xj = vector[j];
                p1 += (Math.pow(j,i)+ b) * (Math.pow(xj/j,i-1) );
            }
            resul += Math.pow(p1,2);
        }

        return resul;
    }
}

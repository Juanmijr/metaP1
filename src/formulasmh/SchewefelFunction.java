/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class SchewefelFunction extends Formula {

    public SchewefelFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super(semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
        double resul = 0;
        double p1 = 0;

        for (int i = 1; i <= getDimension(); i++) {
            p1 += (vector[i - 1] * Math.sin(Math.sqrt(Math.abs(vector[i - 1]))));
        }
        resul = 418.9829 * getDimension() - p1;
        return resul;
    }

}

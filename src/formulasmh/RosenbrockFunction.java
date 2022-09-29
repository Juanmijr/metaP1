/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class RosenbrockFunction extends Formula {

    public RosenbrockFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super(semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
        double p1 = 0;
        double resul = 0;

        for (int i = 1; i < getDimension(); i++) {
            resul += 100 * Math.pow(vector[i] - Math.pow(vector[i - 1], 2), 2) + Math.pow(vector[i - 1] - 1, 2);
        }

        return resul;
    }

}

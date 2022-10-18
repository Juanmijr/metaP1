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
        double p1 = 0;


        for (int i = 1; i <= getDimension(); i++) {
            p1 += ((Math.pow(vector[i - 1], 2)) - 10 * Math.cos(2 * Math.PI * vector[i - 1]));
        }
         

        return 10 * getDimension() + p1;
    }

}

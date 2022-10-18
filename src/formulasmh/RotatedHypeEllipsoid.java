/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class RotatedHypeEllipsoid extends Formula {

    public RotatedHypeEllipsoid(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super(semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
        double p1 = 0;
        double resul = 0;

        for (int i = 1; i <= getDimension(); i++) {
            p1 = 0;
            for (int j = 1; j <= i; j++) {
                p1 += Math.pow(vector[j - 1], 2);
            }
            resul += p1;
        }

        return resul;
    }

}

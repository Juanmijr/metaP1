/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class AckleyFunction extends Formula {

    public AckleyFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super(semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
        {

            double a = 20;
            double b = 0.2;
            double c = 2 * Math.PI;
            double optimo = -1000;
            double resul = 0;
            double p1 = 0;
            double p2 = 0;
            double dim = getDimension();
            for (int i = 1; i <= dim; i++) {
                p1 += Math.pow(vector[i - 1], 2);
                p2 += Math.cos(c * vector[i - 1]);
            }
            resul = -a * Math.exp(-b * Math.sqrt(p1 / dim)) - Math.exp(p2 / dim) + a + Math.exp(1);
            return resul;
        }

    }

}

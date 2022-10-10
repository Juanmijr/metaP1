/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class BusquedaLocalK {

    public BusquedaLocalK() {
    }

    public double busquedaMejor(int tam, boolean evalK, double evaluaciones, double[] solActual, double rangoMin, double rangoMax, Formula fi) {
        long tiempoinicial = System.currentTimeMillis();
        double[] solVecina = new double[tam];
        double[] mejorVecina = new double[tam];
        int it = 0;
        double mejorCoste = fi.ejecucion(solActual);
        double mejorVeci = Double.POSITIVE_INFINITY;
        boolean mejora;
        double numVecinos = 0;
        do {
            mejora = false;
            if (evalK) {
                numVecinos = fi.numAleatorio(4, 10);

            } else {
                numVecinos = 3;
            }
            for (int i = 1; i < numVecinos; i++) {
                for (int j = 0; j < tam; j++) {

                    if (fi.numAleatorio(0, 1) <= 0.3) {
                        double inf, sup;
                        inf = solActual[j] * 0.9;
                        sup = solActual[j] * 1.1;
                        if (inf < rangoMin) {
                            inf = rangoMin;
                        }
                        if (sup > rangoMax) {
                            sup = rangoMax;
                        }
                        solVecina[j] = fi.numAleatorio(inf, sup);
                    } else {
                        solVecina[j] = solActual[j];
                    }

                }
                double costeVecino = fi.ejecucion(solVecina);
                if (costeVecino < mejorVeci) {
                    mejorVeci = costeVecino;
                    mejorVecina = solVecina;

                }

            }

            if (mejorVeci < mejorCoste) {
                solActual = mejorVecina;
                mejorCoste = mejorVeci;
                mejora = true;
                it++;
            }

        } while (it < evaluaciones && mejora);
        long tiempofin = System.currentTimeMillis();

        return mejorCoste;
    }

}

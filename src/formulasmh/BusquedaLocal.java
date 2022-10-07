/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class BusquedaLocal {
     public BusquedaLocal() {
    }

    public double busquedaMejor(int tam, double numVecinos, double evaluaciones, double[] solActual, double rangoMin, double rangoMax, Formula fi) {
        double[] solVecina = new double[tam];
        double[] mejorVecina = new double[tam];
        int it = 0;
        double mejorCoste = fi.ejecucion(solActual);
        double mejorVeci = Double.POSITIVE_INFINITY;
        boolean mejora;
        do {
                        mejora = false;

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
        return mejorCoste;
    }

}

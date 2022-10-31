/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

import static formulasmh.Utils.imprimeVectorDouble;
/**
 *
 * @author JuanMi
 */
public class BusquedaLocalK {

    public BusquedaLocalK() {
    }
   

    public double busquedaMejor(int tam, boolean evalK, double evaluaciones, double[] solActual, Formula fi, StringBuilder sb,double porceVecinos, double porRangoVeci) {
        sb.append("\n*** Empieza busquedaMejor del algoritmo: "+ fi.getClass()+ " ***\n");
        long tiempoinicial = System.nanoTime();
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

                    if (fi.numAleatorio(0, 1) <= porceVecinos) {
                        double inf, sup;
                        inf = solActual[j] * (1-porRangoVeci);
                        sup = solActual[j] * (1+porRangoVeci);
                        if (solActual[j]<0){
                            double aux = sup;
                            sup = inf;
                            inf = aux;
                        }
                        if (inf < fi.getRangoMin()) {
                            inf = fi.getRangoMin();
                        }
                        if (sup > fi.getRangoMax()) {
                            sup = fi.getRangoMax();
                        }
                        solVecina[j] = fi.numAleatorio(inf, sup);
                    } else {
                        solVecina[j] = solActual[j];
                    }

                }
                double costeVecino = fi.ejecucion(solVecina);
                if (Math.abs(costeVecino) < Math.abs(mejorVeci)) {
                    mejorVeci = costeVecino;
                    mejorVecina = solVecina;

                }

            }

            if (Math.abs(mejorVeci) < Math.abs(mejorCoste)) {
                sb.append("\nITERACIÓN: "+ it + " - \n");
                sb.append("El mejor vecino pasa de ser: "+ mejorCoste + " a ser: "+mejorVeci+"\n");
                sb.append("\n*** SOLUCIÓN ACTUAL: ***\n");
                sb.append(imprimeVectorDouble(solActual));
                solActual = mejorVecina;
                mejorCoste = mejorVeci;
                mejora = true;
                it++;
                
            }

        } while (it < evaluaciones && mejora);
        if (evalK){
                     sb.append("\nResultado mejora blk: " + (mejorCoste) + "\n");
        }
        else{
            sb.append("\nResultado mejora bl3: "+mejorCoste+"\n");
        }
        long tiempofin = System.nanoTime();
        sb.append("Duracion " + (tiempofin - tiempoinicial)/1000+ " microsegundos\n");

        return mejorCoste;
    }

}

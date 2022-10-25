/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author JuanMi
 */
public class BusquedaTabu {

    static void menosVisitados(int[][] mat, int[][] matMarcaje, double[] nuevaSol, Formula form) {
        System.out.println("EMPIEZO LA EJECUCIÓN: ");
        int tam = nuevaSol.length;
        double menor;
        int pc = 0;
        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                menor = Double.POSITIVE_INFINITY;
                for (int j = 0; j < 10; j++) {
                    if (mat[i][j] <= menor && matMarcaje[i][j]==0) {
                        menor = mat[i][j];
                        pc = j;
                    }
                }
                matMarcaje[i][pc]=1;
                //ALEATORIO ENTRE 0,2
                int aleatorio = (int) form.numAleatorio(0, 2);
                double ancho = (form.getRangoMax() - form.getRangoMin() + 1)/10;
                //MULTIPLICAR LA COLUMNA POR EL ANCHO
                double ini = form.getRangoMin() + (pc * ancho);
                double fin = ini + ancho;

                nuevaSol[i] = form.numAleatorio(ini, fin);
            }
        }

    }

    static void masVisitados(int [][] mat, int [][] matMarcaje, double[] nuevaSol, Formula form) {
        int tam = nuevaSol.length;
        double mayor;
        int pc = 0;
        int[] columnas = new int[3];
        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                mayor = 0;
                for (int j = 0; j < 10; j++) {
                    if (mat[i][j] >= mayor&& matMarcaje[i][j]==0) {
                        mayor = mat[i][j];
                        pc = j;
                    }
                }
                columnas[k] = pc;
                matMarcaje[i][pc]=1;
                //ALEATORIO ENTRE 0,2
                int aleatorio = (int) form.numAleatorio(0, 2);
                int col = columnas[aleatorio];
                double ancho = form.getRangoMax() - form.getRangoMin() + 1;
                //MULTIPLICAR LA COLUMNA POR EL ANCHO
                double ini = form.getRangoMin() + (col * ancho);
                double fin = ini + ancho;

                nuevaSol[i] = form.numAleatorio(ini, fin);
            }
        }

    }

    static void imprimeMatriz(double[][] memFrec) {
        for (int i = 0; i < memFrec.length; i++) {
            for (int j = 0; j < memFrec.length; j++) {
                System.out.print(memFrec[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    static void imprimeVector(double[] vector) {
        for (int i = 0; i < vector.length; i++) {

            System.out.print(vector[i] + " ");
        }
        System.out.println("\n");
    }

    static void imprimeLista(Queue<double[]> lTabu) {
        for (double[] elemento : lTabu) {
            for (int j = 0; j < elemento.length; j++) {
                System.out.print(elemento[j] + " ");
            }
        }
    }

    static boolean comprobarTabu1(Queue<double[]> lTabu, double[] vecino) {
        boolean iguales = true;

        for (double[] elemento : lTabu) {
            for (int j = 0; j < elemento.length; j++) {
                double inf = 0;
                double sup = 0;
                if (elemento[j] > 0) {
                    inf = elemento[j] * 0.99;
                    sup = elemento[j] * 1.01;
                } else {
                    sup = elemento[j] * 0.99;
                    inf = elemento[j] * 1.01;
                }
                if (vecino[j] < inf || vecino[j] > sup) {
                    iguales = false;
                    break;
                }
            }
            if (!iguales) {
                break;
            }
        }
        return iguales;
    }

    static boolean comprobarTabu2(Queue<int[]> lTabu, int[] cambiosVecino) {

        for (int[] elemento : lTabu) {

            if (Arrays.equals(cambiosVecino, elemento)) {
                return true;
            }
        }
        return false;
    }

    static int[] copiaVector(int[] cambiosVecino) {
        int[] nuevo = new int[cambiosVecino.length];
        for (int i = 0; i < cambiosVecino.length; i++) {
            nuevo[i] = cambiosVecino[i];
        }
        return nuevo;
    }

    static double[] copiaVectorDouble(double[] mejorVecino) {
        double[] nuevo = new double[mejorVecino.length];
        for (int i = 0; i < mejorVecino.length; i++) {
            nuevo[i] = mejorVecino[i];
        }
        return nuevo;
    }

    double BTabu(int iteraciones, double[] SolActual, int tamTabu, Formula form, int numRangos, StringBuilder sb) {
        double costeActual = form.ejecucion(SolActual);
        double CosteMejorPeor, CGlobal = costeActual, CosteMejorMomento = Double.POSITIVE_INFINITY;
        int OEMejoraI = 0, OEnoMejoraI = 0, OEMejoraD = 0, OEnoMejoraD = 0, osc = 0;
        int[][] memFrec = new int[SolActual.length][numRangos];
        int[][] matMarcaje = new int [SolActual.length][numRangos];
        //INICIALIZO MATRIZ A CEROS
        for (int i = 0; i < memFrec.length; i++) {
            for (int j = 0; j < memFrec[i].length; j++) {
                memFrec[i][j] = 0;
                matMarcaje[i][j]= 0 ; 
            }
        }

        //CREO LISTA TABÚ COMPLETAS
        Queue<double[]> lTabu = new LinkedList<double[]>();
        Queue<int[]> lTabuMov = new LinkedList<int[]>();
        //VECTOR DE CAMBIOS DEL VECINO PARA AÑADIRLO A LA LISTA DE MOV
        int[] cambiosVecino = new int[SolActual.length];
        int[] cambiosMejorVecino = new int[SolActual.length];
        lTabu.add(SolActual);

        double[] mejorPeores = new double[SolActual.length], SolGlobal = SolActual, nuevaSol = new double[SolActual.length];
        int iter = 0;
        int contador = 0; //ESTANCAMIENTOS
        boolean mejora;

        double[] vecino = new double[SolActual.length];
        double[] mejorVecino = new double[SolActual.length];
        double mejorCosteVecino = Double.POSITIVE_INFINITY;
        CosteMejorPeor = Double.POSITIVE_INFINITY;

        int multiarranque = 0;

        while (iter < iteraciones) {
            iter++;
            mejora = false;
            boolean noTabu = true;
            double vecinos = Math.floor(form.numAleatorio(4, 10));
            for (int i = 1; i <= vecinos; i++) {
                //*********************************************************************************************************
                for (int k = 0; k < SolActual.length; k++) {
                    if (form.numAleatorio(0, 1) <= 0.3) { //Si aleatorio < 0.3
                        cambiosVecino[k] = 1;
                        if (multiarranque == 0) {
//VNS caso 1
                            double inf = SolActual[k] * 0.9;
                            double sup = SolActual[k] * 1.1;

                            if (SolActual[k] < 0) {
                                double aux = sup;
                                sup = inf;
                                inf = aux;

                            }
                            if (inf < form.getRangoMin()) {
                                inf = form.getRangoMin();
                            }
                            if (sup > form.getRangoMax()) {
                                sup = form.getRangoMax();
                            }
                            vecino[k] = form.numAleatorio(inf, sup);
                        } else {
                            if (multiarranque == 1) {
                                //VNS caso 2    
                                vecino[k] = form.numAleatorio(form.getRangoMin(), form.getRangoMax());
                            } else {
                                //VNS caso 3
                                if (form.getClass().getName() != "formulasmh.MichalewiczFunction") {
                                    vecino[k] = SolActual[k] * -1;
                                } else {
                                    vecino[k] = 1 / SolActual[k];
                                }
                                if (vecino[k] < form.getRangoMin()) {
                                    vecino[k] = form.getRangoMin();
                                }
                                if (vecino[k] > form.getRangoMax()) {
                                    vecino[k] = form.getRangoMax();
                                }
                            }
                        }
                    } else {
                        vecino[k] = SolActual[k];
                        cambiosVecino[k] = 0;
                    }
                }
                //*********************************************************************************************************

                if (comprobarTabu1(lTabu, vecino) == false) {

                    if (comprobarTabu2(lTabuMov, cambiosVecino) == false) {

                        noTabu = false;

                        double costeVecino = form.ejecucion(vecino);
                        if (costeVecino < mejorCosteVecino) {
                            mejorVecino = copiaVectorDouble(vecino);
                            mejorCosteVecino = costeVecino;
                            cambiosMejorVecino = copiaVector(cambiosVecino);
                        }
                    }
                }

            }
            //************************************************************************
            if (!noTabu) {
                double ancho = (form.getRangoMax()+1 - (form.getRangoMin()-1)) / numRangos;
                for (int i = 0; i < memFrec.length; i++) {
                    double intervalo = Math.floor((mejorVecino[i] - (form.getRangoMin())) / ancho);
                    memFrec[i][(int) intervalo]++;
                }

                lTabu.add(copiaVectorDouble(mejorVecino));
                if (lTabu.size() > tamTabu) {
                    lTabu.remove();
                }

                lTabuMov.add(copiaVector(cambiosMejorVecino));
                if (lTabuMov.size() > tamTabu) {
                    lTabuMov.remove();
                }

                if (mejorCosteVecino < costeActual) {
                    SolActual = copiaVectorDouble(mejorVecino);
                    costeActual = mejorCosteVecino;
                    mejora = true;
                } else {
                    if (mejorCosteVecino < CosteMejorPeor) {
                        CosteMejorPeor = mejorCosteVecino;
                        mejorPeores = copiaVectorDouble(SolActual);
                    }
                }

                if (!mejora) {
                    costeActual = CosteMejorPeor;
                    SolActual = copiaVectorDouble(mejorPeores);
                    contador++;

                    multiarranque = (multiarranque + 1) % 3;
                } else {
                    contador = 0;
                    if (costeActual < CGlobal) {
                        CGlobal = costeActual;
                        SolGlobal = copiaVectorDouble(SolActual);
                    }
                }
                System.out.println("\n*******Contador: "+ contador +"  ****************\n" );
                if (contador == 50) {
                    if (osc == 0) {
                        if (CosteMejorMomento > costeActual) {
                            CosteMejorMomento = costeActual;
                            System.out.println("La oscilación mejora");
                            OEMejoraD++;

                        } else {
                                                        System.out.println("La oscilación NO mejora");

                            OEnoMejoraD++;
                        }
                    } else {
                        if (CosteMejorMomento > costeActual) {
                            CosteMejorMomento = costeActual;
                            System.out.println("La oscilación mejora");
                            OEMejoraI++;

                        } else {
                            System.out.println("La oscilación NO mejora");
                            OEnoMejoraI++;
                        }
                    }
                    
                    contador = 0;
                    double prob = form.numAleatorio(0,1);
                    System.out.println("Prob: " + prob);
                    if (prob <= 0.5) {
                        osc = 0;
                        System.out.println("ESTOY AQUÍ EN MENOS VISITADOS");
                        menosVisitados(memFrec, matMarcaje, nuevaSol, form);
                    } else {
                        osc = 1;
                        System.out.println("ESTOY AQUÍ EN MÁS VISITADOS");

                        masVisitados(memFrec, matMarcaje, nuevaSol, form);
                    }

                    SolActual = copiaVectorDouble(nuevaSol);

                    costeActual = form.ejecucion(SolActual);

                    if (costeActual < CGlobal) {   //Por si mejora al mejor Global
                        CGlobal = costeActual;
                        SolGlobal = copiaVectorDouble(SolActual);
                    }

                    for (int i = 0; i < memFrec.length; i++) {
                        for (int j = 0; j < memFrec[i].length; j++) {
                            memFrec[i][j] = 0;
                        }
                    }

                    lTabu = new LinkedList<double[]>();
                    lTabuMov = new LinkedList<int[]>();
                }

            }
            sb.append("\n**************************************");
            sb.append("\n Paso = " + iter);
            sb.append("\nCoste Actual: " + costeActual);
            sb.append("\nCoste MejorPeor: " + CosteMejorPeor);
            sb.append("\nCoste Mejor Global: " + CGlobal);

        }
        System.out.println("MEJORAS-D: "+ OEMejoraD + " NO MEJORAS-D: "+ OEnoMejoraD);
                System.out.println("MEJORAS-I: "+ OEMejoraI + " NO MEJORAS-I: "+ OEnoMejoraI);

                SolActual = SolGlobal;
        sb.append("\n MEJOR RESULTADO TABÚ: " + CGlobal);
        return CGlobal;
    }
}

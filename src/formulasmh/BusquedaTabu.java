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

    static void menosVisitados(double[][] mat, double[] nuevaSol, double rmin, double rmax, Formula form) {
        int tam = nuevaSol.length;
        double menor;
        int pc = 0;
        int[] columnas = new int[3];
        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                menor = Double.POSITIVE_INFINITY;
                for (int j = 0; j < 10; j++) {
                    if (mat[i][j] <= menor) {
                        menor = mat[i][j];
                        pc = j;
                    }
                }
                columnas[k] = pc;
                // ¿MAT[i][pc]=99999999999;?
                //ALEATORIO ENTRE 0,2
                int aleatorio = (int) form.numAleatorio(0, 2);
                int col = columnas[aleatorio];
                double ancho = rmax - rmin + 1;
                //MULTIPLICAR LA COLUMNA POR EL ANCHO
                double ini = rmin + (col * ancho);
                double fin = ini + ancho;

                nuevaSol[i] = form.numAleatorio(ini, fin);
            }
        }

    }

    static void masVisitados(double[][] mat, double[] nuevaSol, double rmin, double rmax, Formula form) {
        int tam = nuevaSol.length;
        double mayor;
        int pc = 0;
        int[] columnas = new int[3];
        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                mayor = 0;
                for (int j = 0; j < 10; j++) {
                    if (mat[i][j] >= mayor) {
                        mayor = mat[i][j];
                        pc = j;
                    }
                }
                columnas[k] = pc;
                // ¿MAT[i][pc]=99999999999;?
                //ALEATORIO ENTRE 0,2
                int aleatorio = (int) form.numAleatorio(0, 2);
                int col = columnas[aleatorio];
                double ancho = rmax - rmin + 1;
                //MULTIPLICAR LA COLUMNA POR EL ANCHO
                double ini = rmin + (col * ancho);
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
    
    static void imprimeVector(double[] vector){
        for (int i = 0; i < vector.length; i++) {
            
            System.out.print(vector[i]+" ");
        }
    }
    
    static boolean comprobarTabu1(Queue<double[]> lTabu, double[] vecino){  
        boolean iguales = false;
        for (double[] elemento : lTabu){
                    for (int j = 0; j<elemento.length;j++){
                        double inf = 0;
                        double sup = 0; 
                        if (elemento[j]>0){
                         inf = elemento[j] * 0.99;
                         sup = elemento[j]*1.01;
                        }
                        else{
                           inf = elemento[j] * 0.99;
                           sup = elemento[j]*1.01;
                        }
                       if (vecino[j]<inf || vecino[j]>sup){
                           iguales = true;
                           break;
                       }
                    }
                    if (iguales){
                        break;
                    }
                }
        return iguales;
    }

    
    static boolean comprobarTabu2 (Queue<int[]> lTabu, int[] cambiosVecino){
                for (int[] elemento : lTabu){
                    if (Arrays.equals(cambiosVecino, elemento)){
                        return true;
                    }
                }
                return false;
    }
    
    static double BTabu(int iteraciones, double[] SolActual, double rmin, double rmax, int tamTabu, Formula form, int numRangos) {
        double costeActual = form.ejecucion(SolActual);
        double CosteMejorPeor, CGlobal = costeActual, CosteMejorMomento = Double.POSITIVE_INFINITY;
        int OEMejoraI = 0, OEnoMejoraI = 0, OEMejoraD = 0, OEnoMejoraD = 0, osc = 0;
        double[][] memFrec = new double[SolActual.length][numRangos];
        //INICIALIZO MATRIZ A CEROS
        System.out.println("Tamaño filas: " + memFrec.length + " Tamaño COL: " + memFrec[1].length);
        for (int i = 0; i < memFrec.length; i++) {
            for (int j = 0; j < memFrec[i].length; j++) {
                memFrec[i][j] = 0;
            }
        }
        imprimeMatriz(memFrec);

        //CREO LISTA TABÚ COMPLETAS
        Queue<double[]> lTabu = new LinkedList<double[]>();
        Queue<int[]> lTabuMov = new LinkedList<int[]>();
        //VECTOR DE CAMBIOS DEL VECINO PARA AÑADIRLO A LA LISTA DE MOV
        int[] cambiosVecino = new int[SolActual.length];
        int[] cambiosMejorVecino = new int [SolActual.length];
        lTabu.add(SolActual);

        double[] mejorPeores, SolGlobal = SolActual, nuevaSol = SolActual;
        int iter = 0;
        int contador = 0; //ESTANCAMIENTOS
        boolean mejora;

        double[] vecino = new double[SolActual.length];
        double[] mejorVecino = new double[SolActual.length];
        double mejorCosteVecino = Double.POSITIVE_INFINITY;

        int multiarranque = 0;

        while (iter < iteraciones) {
            iter++;
            boolean noTabu = true;
            double vecinos = form.numAleatorio(4, 10);
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
                            if (inf < rmin) {
                                inf = rmin;
                            }
                            if (sup > rmax) {
                                sup = rmax;
                            }
                                vecino[k] =form.numAleatorio(inf, sup) ;
                            } else {
                                if (multiarranque == 1) {
                                    //VNS caso 2    
                                    vecino[k] = form.numAleatorio(rmin, rmax);
                                } else {
                                    //VNS caso 3
                                    vecino[k] = SolActual[k] * -1;
                                }
                        }
                    } else {
                        vecino[k] = SolActual[k];
                        cambiosVecino[k] = 0;
                    }
                }
                //*********************************************************************************************************
                boolean tabu = comprobarTabu1(lTabu, vecino);
                
                if (!tabu){
                    tabu = comprobarTabu2(lTabuMov, cambiosVecino);
                }
                
                if (!tabu){
                    noTabu = false;
                    
                    double costeVecino = form.ejecucion(vecino);
                    if(costeVecino<mejorCosteVecino ){
                        mejorVecino = vecino;
                        mejorCosteVecino = costeVecino;
                        cambiosMejorVecino = cambiosVecino;
                    }
                    
                }
              
            }
            //************************************************************************
        if (!noTabu){
            
        }
        }
        return CGlobal;
    }
}

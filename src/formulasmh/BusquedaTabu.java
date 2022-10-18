/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

import java.util.ArrayList;

/**
 *
 * @author JuanMi
 */
public class BusquedaTabu {

    void menosVisitados(double[][] mat, double[] nuevaSol, double rmin, double rmax, Formula form) {
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

    void masVisitados(double[][] mat, double[] nuevaSol, double rmin, double rmax, Formula form) {
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

    double BTabu(int iteraciones, double[] SolActual, double rmin, double rmax, int tamTabu, Formula form, int numRangos) {
        double costeActual = form.ejecucion(SolActual);
        double CosteMejorPeor, CGlobal = costeActual, CosteMejorMomento = Double.POSITIVE_INFINITY;
        int OEMejoraI = 0, OEnoMejoraI = 0, OEMejoraD = 0, OEnoMejoraD = 0, osc = -1;
        double[][] memFrec = new double[SolActual.length][numRangos];
        //INICIALIZO MATRIZ A CEROS
        System.out.println("Tamaño filas: " + memFrec.length + " Tamaño COL: " + memFrec[1].length);
        for (int i = 0; i < memFrec.length; i++) {
            for (int j = 0; j < memFrec[i].length; j++) {
                memFrec[i][j] = 0;
            }
        }

        //CREO LISTA TABÚ COMPLETAS
        ArrayList<double[]> lTabu = new ArrayList<double[]>();
        ArrayList<int[]> lTabuMov = new ArrayList<int[]>();
        //VECTOR DE CAMBIOS DEL VECINO PARA AÑADIRLO A LA LISTA DE MOV
        int[] cambiosVecino = new int[SolActual.length];
        lTabu.add(SolActual);

        double[] mejorPeores, SolGlobal = SolActual, nuevaSol = SolActual;
        double inf, sup;
        int iter = 0;
        int contador = 0; //ESTANCAMIENTOS
        boolean mejora;

        double[] vecino = new double[SolActual.length];
        double[] mejorVecino = new double[SolActual.length];
        double mejorCosteVecino = Double.POSITIVE_INFINITY;

        int multiarranque = 1;

        while (iter < iteraciones) {
            iter++;     //cada nuevo movimiento a otro vecindario
            mejora = false;
            CosteMejorPeor = Double.POSITIVE_INFINITY;

            int x = (int)form.numAleatorio(4, 10);      //dinamico
            for (int i = 1; i <= x; i++) {
                for (int j = 0; j < SolActual.length; j++) {
                    double uniforme = form.numAleatorio(0, 1);
                    if (uniforme <= 0.3) { //Si aleatorio < 0.3
                        cambiosVecino[j] = 1;
                        if (multiarranque == 1) {
                            //VNS caso 1
                            inf = SolActual[j] * 0.9;
                            sup = SolActual[j] * 1.1;
                            if (inf < rmin) {
                                inf = rmin;
                            }
                            if (sup > rmax) {
                                sup = rmax;
                            }
                            vecino[j] = form.numAleatorio(inf, sup);
                        } else {
                            if (multiarranque == 2) {
                                //VNS caso 2    
                                // vecino[k] = Randfloat(rmin, rmax);   
                            } else {
                                //VNS caso 3
                                // vecino[k] = SolActual[k]*-1;
                            }
                        }
                    } else {
                        vecino[j] = SolActual[j];
                        cambiosVecino[j] = 0;
                    }

                }

                //PRIMERA COMPROBACION TABU
                //COMPROBAR AQUI si esta en lista Tabu
                boolean tabu = false;

                for (double[] it : lTabu) {
                    int cont = 0;
                    for (int k = 0; k < SolActual.length; k++) {
                        inf = (it)[k] * 0.99;
                        sup = (it)[k] * 1.01;

                        if (vecino[k] < inf || vecino[k] > sup) {
                            cont++;
                            break;
                        }
                    }
                    if (cont == 0) {
                        tabu = true;
                        break;
                    }
                }

                //SEGUNDA COMPROBACION TABU
                //SI No es Tabu
                if (!tabu) {
                    //mejor vecino del VECINDARIO
                    double costeVecino = form.ejecucion(vecino);

                    if (costeVecino < mejorCosteVecino) {
                        mejorVecino = vecino;
                        mejorCosteVecino = costeVecino;

                    }
                } else {
                    //j--;    // Hasta que no tengamos un vecindario completo(por si algunos salen tabu)
                }

            }
            //ACTUALIZO la memoria de frecuencias
            //Por cada valor (dimension), del vector mejor Vecino obtenido
            for (int i = 0; i < memFrec.length; i++) {
                double ancho = (rmax - rmin + 1) / 10;
                int posCol = 0; //columna con el rango donde actualizar la memoria
                for (int j = (int)rmin; j < rmax; j += ancho) {
                    if (mejorVecino[i] >= j && mejorVecino[i] < j + ancho) {
                        memFrec[i][posCol]++;
                        break;
                    }
                    posCol++;
                }
            }

            //ACTUALIZO tabu la solucion completa(explicita) del vecino
            lTabu.add(mejorVecino);
            if (lTabu.size() > tamTabu) {
                lTabu.remove(0);
            }

            SolActual = mejorVecino;

            if (mejorCosteVecino < costeActual) { //el mejor del vecindario mejora al actual
                costeActual = mejorCosteVecino;
                mejora = true;
                contador = 0;  //inicializamos el estancamiento

                if (costeActual < CGlobal) {  //ademas actualizamos al Global y su coste
                    CGlobal = costeActual;
                    SolGlobal = SolActual;
                }
            } else {  // anotamos que es peor que la SolActual y un estancamiento mas
                if (mejorCosteVecino < CosteMejorPeor) {
                    CosteMejorPeor = mejorCosteVecino;
                }

                multiarranque = (multiarranque + 1) % 4;
                if (multiarranque == 0) {
                    multiarranque = 1;
                }

                contador++;
            }

            if (contador == 50) {
                if (osc == 0) {
                    if (CosteMejorMomento > costeActual) {
                        CosteMejorMomento = costeActual;
                        //cout << "La Oscilacion ha mejorado" << endl;
                        OEMejoraD++;

                    } else {
                        //cout << "La Oscilacion NO ha mejorado" << endl;
                        OEnoMejoraD++;
                    }
                } else {
                    if (CosteMejorMomento > costeActual) {
                        CosteMejorMomento = costeActual;
                        //cout << "La Oscilacion ha mejorado" << endl;
                        OEMejoraI++;

                    } else {
                        //cout << "La Oscilacion NO ha mejorado" << endl;
                        OEnoMejoraI++;
                    }
                }

                contador = 0;

                int prob = (int)form.numAleatorio(1, 100);
                //mostrarmatriz(memFrec);
                if (prob <= 50) {
                    osc = 0;
                    menosVisitados(memFrec, nuevaSol, rmin, rmax,form);
                } else {
                    osc = 1;
                    masVisitados(memFrec, nuevaSol, rmin, rmax,form);

                }
                System.out.println( "** Reinicializo **");

                SolActual = nuevaSol;

                double CosteActual = form.ejecucion(SolActual);  /// NUEVA Solucion

                if (CosteActual < CGlobal) {   //Por si mejora al mejor Global
                    CGlobal = CosteActual;
                    SolGlobal = SolActual;
                }
                //Borramos la matriz de frecuencias 

                for (int i = 0; i < memFrec.length; i++) {
                    for (int j = 0; j < memFrec[i].length; j++) {
                        memFrec[i][j] = 0;
                    }
                }

                // Borramos la lista tabu                                
                lTabu.clear();
                //lTabu.resize(tenenciaTabu);  
            }

//        cout << endl <<"Paso = " << iter << endl;
//        cout <<  "Coste Actual: " << costeActual << endl;
//        cout <<  "Coste MejorPeor: " << CosteMejorPeor << endl;
//        cout <<  "Coste Mejor Global: " << CGlobal << endl;
        }
        System.out.println("MEJORAS-D: " + OEMejoraD + " NO MEJORAS-D: " + OEnoMejoraD);
        System.out.println("MEJORAS-I: " + OEMejoraI + " NO MEJORAS-I: " + OEnoMejoraI );
        SolActual = SolGlobal;

//        cout << "Actual:";mostrarVector(SolActual);
//        cout << "Global:";mostrarVector(SolGlobal);
        return CGlobal;
    }
}

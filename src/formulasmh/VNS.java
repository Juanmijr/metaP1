/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

import java.util.LinkedList;
import java.util.Queue;
import static formulasmh.Utils.comprobarTabu1;
import static formulasmh.Utils.comprobarTabu2;
import static formulasmh.Utils.copiaVector;
import static formulasmh.Utils.copiaVectorDouble;
import static formulasmh.Utils.imprimeVector;
import static formulasmh.Utils.imprimelTabu;
import static formulasmh.Utils.imprimelTabuMov;
import static formulasmh.Utils.imprimeVectorDouble;
import static formulasmh.Utils.masVisitados;
import static formulasmh.Utils.menosVisitados;
/**
 *
 * @author JuanMi
 */
public class VNS {

    public VNS() {
    }

    double ejecucion(int iteraciones, double[] SolActual, int tamTabu, Formula form, int numRangos, StringBuilder sb, double porcentaje) {
        double costeActual = form.ejecucion(SolActual);
        double CosteMejorPeor, CGlobal = costeActual, CosteMejorMomento = Double.POSITIVE_INFINITY;
        int mejoraIntensif = 0, noMejoraIntensif = 0, mejoraDiverisf = 0, noMejoraDiversif = 0;
        boolean oscilacion = false;
        int[][] memFrec = new int[SolActual.length][numRangos];
        int[][] matMarcaje = new int[SolActual.length][numRangos];
        //INICIALIZO MATRIZ A CEROS
        for (int i = 0; i < memFrec.length; i++) {
            for (int j = 0; j < memFrec[i].length; j++) {
                memFrec[i][j] = 0;
                matMarcaje[i][j] = 0;
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
                    if (form.numAleatorio(0, 1) <= 0.3) {
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

                        double costeVecino = form.ejecucion(vecino);
                        if (costeVecino < mejorCosteVecino) {
                            noTabu = false;
                            mejorVecino = copiaVectorDouble(vecino);
                            mejorCosteVecino = costeVecino;
                            cambiosMejorVecino = copiaVector(cambiosVecino);
                        }
                    }
                }

            }
            //************************************************************************
            if (!noTabu) {
                double ancho = (form.getRangoMax() + 1 - (form.getRangoMin() - 1)) / numRangos;
                for (int i = 0; i < memFrec.length; i++) {
                    double intervalo = Math.floor((mejorVecino[i] - (form.getRangoMin())) / ancho);
                    memFrec[i][(int) intervalo]++;
                }
                sb.append(imprimeVector(cambiosMejorVecino));
                sb.append(imprimelTabuMov(lTabuMov));
                lTabu.add(copiaVectorDouble(mejorVecino));
                if (lTabu.size() > tamTabu) {
                    lTabu.remove();
                }
                sb.append(imprimelTabu(lTabu));
                lTabuMov.add(copiaVector(cambiosMejorVecino));
                if (lTabuMov.size() > tamTabu) {
                    lTabuMov.remove();
                }
                if (mejorCosteVecino < costeActual) {
                    sb.append("EL MEJOR VECINO:" + mejorCosteVecino + " MEJORA LA SOLACTUAL: " + costeActual + "\n");
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
                if (contador == 50) {
                    if (oscilacion = false) {
                        if (CosteMejorMomento > costeActual) {
                            CosteMejorMomento = costeActual;
                            sb.append("\nLa oscilación mejora");
                            mejoraDiverisf++;

                        } else {
                            sb.append("\nLa oscilación NO mejora");

                            noMejoraDiversif++;
                        }
                    } else {
                        if (CosteMejorMomento > costeActual) {
                            CosteMejorMomento = costeActual;
                            sb.append("\nLa oscilación mejora");
                            mejoraIntensif++;

                        } else {
                            sb.append("\nLa oscilación NO mejora");
                            noMejoraIntensif++;
                        }
                    }

                    contador = 0;
                    double prob = form.numAleatorio(0, 1);
                    if (prob <= porcentaje) {
                        oscilacion = false;
                        menosVisitados(memFrec, matMarcaje, nuevaSol, form);
                    } else {
                        oscilacion = true;

                        masVisitados(memFrec, matMarcaje, nuevaSol, form);
                    }

                    SolActual = copiaVectorDouble(nuevaSol);

                    costeActual = form.ejecucion(SolActual);

                    if (costeActual < CGlobal) {
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
        sb.append("\nMEJORAS DIVERSIFICANDO: " + mejoraDiverisf + " NO MEJORAS DIVERSIFICANDO: " + noMejoraDiversif);
        sb.append("\nMEJORAS INTENSIFICANDO: " + mejoraIntensif + " NO MEJORAS INTENSIFICANDO: " + noMejoraIntensif);

        SolActual = SolGlobal;
        sb.append("\n************************ MEJOR RESULTADO TABÚ: " + CGlobal+ " *******************************");
        sb.append("\n*** MEJOR SOLUCIÓN: ***\n");
        sb.append(imprimeVectorDouble(SolActual));
        return CGlobal;
    }
}

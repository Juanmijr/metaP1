/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

import java.util.Queue;

/**
 *
 * @author JuanMi
 */
public class Utils {
    static void menosVisitados(int[][] mat, int[][] matMarcaje, double[] nuevaSol, Formula form) {
        int tam = nuevaSol.length;
        double menor;
        int pc = 0;
        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                menor = Double.POSITIVE_INFINITY;
                for (int j = 0; j < 10; j++) {
                    if (mat[i][j] <= menor && matMarcaje[i][j] == 0) {
                        menor = mat[i][j];
                        pc = j;
                    }
                }
                matMarcaje[i][pc] = 1;
                //ALEATORIO ENTRE 0,2
                int aleatorio = (int) form.numAleatorio(0, 2);
                double ancho = (form.getRangoMax() - form.getRangoMin() + 1) / 10;
                //MULTIPLICAR LA COLUMNA POR EL ANCHO
                double ini = form.getRangoMin() + (pc * ancho);
                double fin = ini + ancho;

                nuevaSol[i] = form.numAleatorio(ini, fin);
            }
        }

    }

    static void masVisitados(int[][] mat, int[][] matMarcaje, double[] nuevaSol, Formula form) {
        int tam = nuevaSol.length;
        double mayor;
        int pc = 0;
        int[] columnas = new int[3];
        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                mayor = 0;
                for (int j = 0; j < 10; j++) {
                    if (mat[i][j] >= mayor && matMarcaje[i][j] == 0) {
                        mayor = mat[i][j];
                        pc = j;
                    }
                }
                columnas[k] = pc;
                matMarcaje[i][pc] = 1;
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

    static boolean comprobarArray(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean comprobarTabu2(Queue<int[]> lTabu, int[] cambiosVecino) {
        if (lTabu.isEmpty()) {
            return false;
        }
        for (int[] elemento : lTabu) {

            if (comprobarArray(elemento, cambiosVecino)) {
                return true;
            }

        }
        return false;
    }

    static StringBuilder imprimelTabu(Queue<double[]> lTabu) {
        StringBuilder sb = new StringBuilder("\n*** IMPRIMO LISTA TABÚ EXPLÍCITA ***\n");

        for (double[] elemento : lTabu) {
            for (int j = 0; j < elemento.length; j++) {
                sb.append(elemento[j] + " ");
            }

            sb.append("\n");
        }
        sb.append("*******************************\n");

        return sb;
    }

    static StringBuilder imprimelTabuMov(Queue<int[]> lTabuMov) {
        StringBuilder sb = new StringBuilder("\n*** IMPRIMO LISTA TABÚ MOVIMIENTOS ***\n");

        for (int[] elemento : lTabuMov) {
            for (int j = 0; j < elemento.length; j++) {
                sb.append(elemento[j] + " ");
            }
            sb.append("\n");
        }
        sb.append("*******************************\n");
        return sb;
    }

    static int[] copiaVector(int[] cambiosVecino) {
        int[] nuevo = new int[cambiosVecino.length];
        for (int i = 0; i < cambiosVecino.length; i++) {
            nuevo[i] = cambiosVecino[i];
        }
        return nuevo;
    }

    static StringBuilder imprimeVector(int[] cambios) {
        StringBuilder sb = new StringBuilder("\n*** INTENTO METER MEJOR VECINO ***\n");
        for (int ele : cambios) {
            sb.append(ele + " ");
        }
        sb.append("\n");
        return sb;
    }

    static double[] copiaVectorDouble(double[] mejorVecino) {
        double[] nuevo = new double[mejorVecino.length];
        for (int i = 0; i < mejorVecino.length; i++) {
            nuevo[i] = mejorVecino[i];
        }
        return nuevo;
    }

    static void imprimeArray(int[] ar) {
        for (int e : ar) {
            System.out.print(e + " ");
        }
        System.out.println("\n ");
    }

    static StringBuilder imprimeVectorDouble (double[] sol){
         StringBuilder sb = new StringBuilder();
        for (double ele : sol) {
            sb.append(ele + " ");
        }
        sb.append("\n");
        return sb;
    }
}

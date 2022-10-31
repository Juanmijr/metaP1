/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author JuanMi
 */
public class Configurador {

    ArrayList<String> funciones;
    ArrayList<Long> semillas;
    ArrayList<Double> rangos;
    ArrayList<Double> optimos;
    double porcentajeObscilacion;
    double porcentajeRangoVecinos;
    double porcentajeObstencionVecinos;
    int iteraciones;
    int numRangos;
    int dimension;
    int tamTabu;

    public Configurador(String ruta) {
        funciones = new ArrayList<>();
        semillas = new ArrayList<>();
        rangos = new ArrayList<>();
        optimos = new ArrayList<>();
        String linea;
        FileReader f = null;
        try {
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);
            while ((linea = b.readLine()) != null) {
                String[] split = linea.split("=");
                switch (split[0]) {
                    case "Rangos":
                        String[] v = split[1].split(" ");
                        for (int i = 0; i < v.length; i++) {
                            rangos.add(Double.parseDouble(v[i]));
                        }
                        break;

                    case "Semillas":
                        String[] vSemillas = split[1].split(" ");
                        for (int i = 0; i < vSemillas.length; i++) {
                            semillas.add(Long.parseLong(vSemillas[i]));
                        }
                        break;
                    case "Funciones":
                        String[] vFunciones = split[1].split(" ");
                        for (int i = 0; i < vFunciones.length; i++) {
                            funciones.add(vFunciones[i]);
                        }
                        break;
                    case "Dimensión":
                        dimension = Integer.parseInt(split[1]);
                        break;
                    case "Óptimos":
                        String[] vOptimo = split[1].split(" ");
                        for (int i = 0; i < vOptimo.length; i++) {
                            optimos.add(Double.parseDouble(vOptimo[i]));
                        }
                        break;
                    case "PorcentajeObscilación":
                        porcentajeObscilacion = Double.parseDouble(split[1]);
                        break;
                    case "PorcentajeObstenciónVecinos":
                        porcentajeObstencionVecinos = Double.parseDouble(split[1]);

                        break;
                    case "PorcentajeRangosVecinos":
                        porcentajeRangoVecinos = Double.parseDouble(split[1]);

                        break;
                    case "Iteraciones":
                        iteraciones = Integer.parseInt(split[1]);
                        break;
                    case "NúmeroRangos":
                        numRangos = Integer.parseInt(split[1]);

                        break;
                    case "TamañoTabú":
                        tamTabu = Integer.parseInt(split[1]);
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getFunciones() {
        return funciones;
    }

    public ArrayList<Long> getSemillas() {
        return semillas;
    }

    public int getDimension() {
        return dimension;
    }

    public ArrayList<Double> getRangos() {
        return rangos;
    }

    public ArrayList<Double> getOptimos() {
        return optimos;
    }

    public double getPorcentajeObscilacion() {
        return porcentajeObscilacion;
    }

    public double getPorcentajeRangoVecinos() {
        return porcentajeRangoVecinos;
    }

    public double getPorcentajeObstencionVecinos() {
        return porcentajeObstencionVecinos;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public int getNumRangos() {
        return numRangos;
    }

    public int getTamTabu() {
        return tamTabu;
    }

}

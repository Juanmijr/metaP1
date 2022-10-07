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
    int dimension;

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
                        String[] vFunciones= split[1].split(" ");
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
    
    
    
}

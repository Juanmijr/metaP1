/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

import java.util.Random;

/**
 *
 * @author JuanMi
 */
public abstract class Formula {
    private double optimoGlobal;
    private double dimension;
    private Random aleatorio;
    private double rangoMin;
    private StringBuilder log; 
    private double rangoMax;

    public Formula( long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        aleatorio=new Random (semilla);
        this.dimension = dimension;
        this.rangoMax = rangoMax;
        this.rangoMin= rangoMin;
        this.optimoGlobal = optimoGlobal;
        log = new StringBuilder();
    }

    
     public double[] vectorAleatorios(){
        double vector[] = new double[10];
        
        for (int i = 0; i<dimension;i++){
            vector[i] = numAleatorio(rangoMin, rangoMax);
        }
        return vector;
    }
     
    public double numAleatorio(double vMin, double vMax){
        return vMin + aleatorio.nextDouble()*(vMax-vMin+1);
    }

    public double getDimension() {
        return dimension;
    }

    public double getRangoMin() {
        return rangoMin;
    }

    public double getRangoMax() {
        return rangoMax;
    }

    public StringBuilder getLog() {
        return log;
    }

    public void setLog(StringBuilder log) {
        this.log = log;
    }
    
    
    
    
    public abstract double ejecucion(double vector[]);

 
}

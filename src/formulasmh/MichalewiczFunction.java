/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formulasmh;

/**
 *
 * @author JuanMi
 */
public class MichalewiczFunction extends Formula {
     public MichalewiczFunction(long semilla, double dimension, double rangoMin, double rangoMax, double optimoGlobal) {
        super( semilla, dimension, rangoMin, rangoMax, optimoGlobal);
    }

    @Override
    public double ejecucion(double vector[]) {
      double result = 0;
      for (int i =0; i<getDimension();i++){
          result += Math.sin(vector[i])*Math.pow(Math.sin((i*Math.pow(vector[i],2))/Math.PI),2);
      }

      return result*-1;

    }
}

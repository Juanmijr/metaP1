/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formulasmh;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author JuanMi
 */
public class FormulasMH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Configurador config = new Configurador(args[0]);

        for (int i = 0; i < config.getFunciones().size(); i++) {
            
            //HOLAAAAAA
            //Aaa
            //CountDownLatch cdl = new CountDownLatch(config.getSemillas().size());
            switch (config.getFunciones().get(i)) {
                case "AckleyFunction":
                    ArrayList<Formula> f = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        AckleyFunction ac = new AckleyFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(0), config.getRangos().get(1), config.getOptimos().get(0));
                        f.add(ac);
                        double vector[] = ac.vectorAleatorios();
                        System.out.println("Resultado Ackley: " + ac.ejecucion(vector));
                    }
                    break;
                case "GriewankFunction":
                    ArrayList<Formula> f1 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        GriewankFunction gf1 = new GriewankFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(2), config.getRangos().get(3), config.getOptimos().get(1));
                        f1.add(gf1);
                        double vector[] = gf1.vectorAleatorios();
                        System.out.println("Resultado Griewank: " + gf1.ejecucion(vector));

                    }
                    break;
                case "GriewankFunction2":
                    ArrayList<Formula> f2 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        GriewankFunction2 gf2 = new GriewankFunction2(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(4), config.getRangos().get(5), config.getOptimos().get(2));
                        f2.add(gf2);
                        double vector[] = gf2.vectorAleatorios();
                        System.out.println("Resultado Griewank2: "+gf2.ejecucion(vector));

                    }
                    break;
                case "PermFunction":
                    ArrayList<Formula> f3 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        PermFunction pf = new PermFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(6), config.getRangos().get(7), config.getOptimos().get(3));
                        f3.add(pf);
                        double vector[] = pf.vectorAleatorios();
                        System.out.println("Resultado PermFunction: "+pf.ejecucion(vector));

                    }
                    break;
                case "RastringinFunction":
                    ArrayList<Formula> f4 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RastringinFunction rf = new RastringinFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(8), config.getRangos().get(9), config.getOptimos().get(4));
                        f4.add(rf);
                        double vector[] = rf.vectorAleatorios();
                        System.out.println("Resultado RastringinFunction: "+rf.ejecucion(vector));

                    }
                    break;
                case "RosenbrockFunction":
                    ArrayList<Formula> f5 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RosenbrockFunction rbf = new RosenbrockFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(10), config.getRangos().get(11), config.getOptimos().get(5));
                        f5.add(rbf);
                        double vector[] = rbf.vectorAleatorios();
                        System.out.println("Resultado: "+rbf.ejecucion(vector));

                    }
                    break;
                case "RotatedHypeEllipsoid":
                    ArrayList<Formula> f6 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RotatedHypeEllipsoid rhe = new RotatedHypeEllipsoid(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(12), config.getRangos().get(13), config.getOptimos().get(6));
                        f6.add(rhe);
                        double vector[] = rhe.vectorAleatorios();
                    System.out.println("Resultado: "+rhe.ejecucion(vector));

                    }
                    break;
                case "SchewefelFunction":
                    ArrayList<Formula> f7 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        SchewefelFunction sf2 = new SchewefelFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(14), config.getRangos().get(15), config.getOptimos().get(7));
                        f7.add(sf2);
                        double vector[] = sf2.vectorAleatorios();
                        System.out.println("Resultado: "+sf2.ejecucion(vector));

                    }
                    break;
                case "TridFunction":
                    ArrayList<Formula> f8 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        TridFunction tf = new TridFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(16), config.getRangos().get(17), config.getOptimos().get(8));
                        f8.add(tf);
                        double vector[] = tf.vectorAleatorios();
                        System.out.println("Resultado: "+tf.ejecucion(vector));

                    }
                    break;
                case "DixonPriceFunction":
                    ArrayList<Formula> f9 = new ArrayList<>();
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        DixonPriceFunction pf = new DixonPriceFunction(config.getSemillas().get(k), config.getDimension(), config.getRangos().get(18), config.getRangos().get(19), config.getOptimos().get(9));
                        f9.add(pf);
                        double vector[] = pf.vectorAleatorios();
                        System.out.println("Resultado: "+pf.ejecucion(vector));

                    }
                    break;
            }
        }

    }
}

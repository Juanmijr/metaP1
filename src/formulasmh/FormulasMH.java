/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formulasmh;

import java.util.ArrayList;

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
        BusquedaLocal bl3 = new BusquedaLocal();
        BusquedaLocalK blk = new BusquedaLocalK();
        for (int i = 0; i < config.getFunciones().size(); i++) {

            switch (config.getFunciones().get(i)) {
                case "AckleyFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        AckleyFunction ac = new AckleyFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(0), config.getRangos().get(1), config.getOptimos().get(0));
                        double vector[] = ac.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(0), config.getRangos().get(1), ac));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(0), config.getRangos().get(1), ac));
                        System.out.println("Resultado Ackley: " + ac.ejecucion(vector));
                    }
                    break;
                case "GriewankFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        GriewankFunction gf = new GriewankFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(2), config.getRangos().get(3), config.getOptimos().get(1));
                        double vector[] = gf.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(2), config.getRangos().get(3), gf));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(2), config.getRangos().get(3), gf));
                        System.out.println("Resultado GriewankFunction: " + gf.ejecucion(vector));
                    }
                    break;
                case "GriewankFunction2":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        GriewankFunction2 gf2 = new GriewankFunction2(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(4), config.getRangos().get(5), config.getOptimos().get(2));
                        double vector[] = gf2.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(4), config.getRangos().get(5), gf2));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(4), config.getRangos().get(5), gf2));
                        System.out.println("Resultado GriewankFunction2: " + gf2.ejecucion(vector));
                    }
                    break;
                case "PermFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        PermFunction pf = new PermFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(6), config.getRangos().get(7), config.getOptimos().get(2));
                        double vector[] = pf.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(6), config.getRangos().get(7), pf));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(6), config.getRangos().get(7), pf));
                        System.out.println("Resultado PermFunction: " + pf.ejecucion(vector));
                    }
                    break;
                case "RastringinFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RastringinFunction rf = new RastringinFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(8), config.getRangos().get(9), config.getOptimos().get(3));
                        double vector[] = rf.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(8), config.getRangos().get(9), rf));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(8), config.getRangos().get(9), rf));
                        System.out.println("Resultado RastringinFunction: " + rf.ejecucion(vector));
                    }
                    break;
                case "RosenbrockFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RosenbrockFunction rf = new RosenbrockFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(10), config.getRangos().get(11), config.getOptimos().get(4));
                        double vector[] = rf.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(10), config.getRangos().get(11), rf));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(11), config.getRangos().get(11), rf));
                        System.out.println("Resultado RosenbrockFunction: " + rf.ejecucion(vector));
                    }
                    break;
                case "RotatedHypeEllipsoid":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RotatedHypeEllipsoid gf = new RotatedHypeEllipsoid(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(12), config.getRangos().get(13), config.getOptimos().get(5));
                        double vector[] = gf.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(12), config.getRangos().get(13), gf));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(12), config.getRangos().get(13), gf));
                        System.out.println("Resultado RotatedHypeEllipsoid: " + gf.ejecucion(vector));
                    }
                    break;
                case "SchewefelFunction":
                   for (int k = 0; k < config.getSemillas().size(); k++) {
                        SchewefelFunction sf = new SchewefelFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(14), config.getRangos().get(15), config.getOptimos().get(6));
                        double vector[] = sf.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(14), config.getRangos().get(15), sf));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(14), config.getRangos().get(15), sf));
                        System.out.println("Resultado Schewefel: " + sf.ejecucion(vector));
                    }
                    break;
                case "TridFunction":
                  for (int k = 0; k < config.getSemillas().size(); k++) {
                        TridFunction tf = new TridFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(16), config.getRangos().get(17), config.getOptimos().get(7));
                        double vector[] = tf.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(16), config.getRangos().get(17), tf));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(16), config.getRangos().get(17), tf));
                        System.out.println("Resultado TridFunction: " + tf.ejecucion(vector));
                    }
                    break;
                case "DixonPriceFunction":
                  for (int k = 0; k < config.getSemillas().size(); k++) {
                        DixonPriceFunction df = new DixonPriceFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(18), config.getRangos().get(19), config.getOptimos().get(8));
                        double vector[] = df.vectorAleatorios();
                        System.out.println("Resultado mejora BL3: " + bl3.busquedaMejor(config.getDimension(), 3, 1000,
                                vector, config.getRangos().get(18), config.getRangos().get(19), df));
                        System.out.println("Resultado mejora blk: " + blk.busquedaMejor(config.getDimension(), 1000,
                                vector, config.getRangos().get(18), config.getRangos().get(19), df));
                        System.out.println("Resultado DixonPrice: " + df.ejecucion(vector));
                    }
                    break;
            }
        }

    }
}

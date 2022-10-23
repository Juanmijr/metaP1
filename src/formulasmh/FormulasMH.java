/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formulasmh;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

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
        BusquedaLocalK blk = new BusquedaLocalK();
        BusquedaTabu bt = new BusquedaTabu();
        StringBuilder sb = null;
        double rangoMin = 0;
        double rangoMax = 0;
        for (int i = 0; i < config.getFunciones().size(); i++) {

            switch (config.getFunciones().get(i)) {
                case "AckleyFunction":
                    rangoMax = config.getRangos().get(1);
                    rangoMin = config.getRangos().get(0);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        
                        
                        AckleyFunction ac = new AckleyFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(0), config.getRangos().get(1), config.getOptimos().get(0));
                        double vector[] = ac.vectorAleatorios();
                        sb.append("\nResultado Ackley sin mejora: " + ac.ejecucion(vector) + "\n");
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, ac, sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector, ac, sb);
                        System.out.println("RESULTADO ACKLEY TABÚ: " + bt.BTabu(1000, vector, 5, ac, 10));
                        System.out.println("HE SUPERADO EL ACKLEY");
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "GriewankFunction":
                    rangoMin = config.getRangos().get(2);
                    rangoMax = config.getRangos().get(3);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        GriewankFunction gf = new GriewankFunction(config.getSemillas().get(k), config.getDimension(),
                                rangoMin , rangoMax, config.getOptimos().get(1));
                        double vector[] = gf.vectorAleatorios();
                        sb.append("\nResultado GriewankFunction sin mejora: " + gf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, gf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector, gf,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "MichakewiczFunction":
                    rangoMin=config.getRangos().get(4);
                    rangoMax=config.getRangos().get(5);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                         MichalewiczFunction mf = new MichalewiczFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(4), config.getRangos().get(5), config.getOptimos().get(2));
                        double vector[] = mf.vectorAleatorios();
                        sb.append("\nResultado GriewankFunction2 sin mejora: " + mf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, mf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector, mf,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "PermFunction":
                    rangoMin=config.getRangos().get(6);
                    rangoMax=config.getRangos().get(7);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        PermFunction pf = new PermFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(6), config.getRangos().get(7), config.getOptimos().get(2));
                        double vector[] = pf.vectorAleatorios();
                        sb.append("\nResultado PermFunction: " + pf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, pf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector,  pf,sb);
                        
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "RastringinFunction":
                    rangoMin=config.getRangos().get(8);
                    rangoMax=config.getRangos().get(9);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        RastringinFunction rf = new RastringinFunction(config.getSemillas().get(k), config.getDimension(),
                               rangoMin, rangoMax, config.getOptimos().get(3));
                        double vector[] = rf.vectorAleatorios();
                        sb.append("\nResultado RastringinFunction sin mejora: " + rf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, rf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector,  rf,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "RosenbrockFunction":
                    rangoMin = config.getRangos().get(10);
                    rangoMax = config.getRangos().get(11);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        RosenbrockFunction rf = new RosenbrockFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(10), config.getRangos().get(11), config.getOptimos().get(4));
                        double vector[] = rf.vectorAleatorios();
                        sb.append("\nResultado RosenbrockFunction: " + rf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, rf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector,rf,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "RotatedHypeEllipsoid":
                    rangoMin=config.getRangos().get(12);
                    rangoMax = config.getRangos().get(13);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        RotatedHypeEllipsoid gf = new RotatedHypeEllipsoid(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(12), config.getRangos().get(13), config.getOptimos().get(5));
                        double vector[] = gf.vectorAleatorios();
                        sb.append("\nResultado RotatedHypeEllipsoid: " + gf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, gf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector, gf,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "SchewefelFunction":
                    rangoMin=config.getRangos().get(14);
                    rangoMax=config.getRangos().get(15);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        SchewefelFunction sf = new SchewefelFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(14), config.getRangos().get(15), config.getOptimos().get(6));
                        double vector[] = sf.vectorAleatorios();
                        sb.append("\nResultado Schewefel: " + sf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, sf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector, sf,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "TridFunction":
                    rangoMin=config.getRangos().get(16);
                    rangoMax=config.getRangos().get(17);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        TridFunction tf = new TridFunction(config.getSemillas().get(k), config.getDimension(),
                                rangoMin,rangoMax, config.getOptimos().get(7));
                        double vector[] = tf.vectorAleatorios();
                        sb.append("\nResultado TridFunction: " + tf.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector, tf,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector, tf,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "DixonPriceFunction":
                    rangoMin=config.getRangos().get(18);
                    rangoMax=config.getRangos().get(19);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        DixonPriceFunction df = new DixonPriceFunction(config.getSemillas().get(k), config.getDimension(),
                                rangoMin,rangoMax, config.getOptimos().get(8));
                        double vector[] = df.vectorAleatorios();
                        sb.append("\nResultado DixonPrice: " + df.ejecucion(vector)+'\n');
                        blk.busquedaMejor(config.getDimension(), false, 1000,
                                vector,df,sb);
                        blk.busquedaMejor(config.getDimension(), true, 1000,
                                vector, df,sb);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
            }
        }

    }

    public static void guardarArchivo(String ruta, String texto) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            File directorio = new File("log");
            directorio.mkdir();
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);

            pw.print(texto);
        } catch (IOException e) {
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {

            }
        }
    }

}

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
        BusquedaLocal bl3 = new BusquedaLocal();
        BusquedaLocalK blk = new BusquedaLocalK();
        StringBuilder sb = null;
        for (int i = 0; i < config.getFunciones().size(); i++) {

            switch (config.getFunciones().get(i)) {
                case "AckleyFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        AckleyFunction ac = new AckleyFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(0), config.getRangos().get(1), config.getOptimos().get(0));
                        double vector[] = ac.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                          vector, config.getRangos().get(0), config.getRangos().get(1), ac, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                          vector, config.getRangos().get(0), config.getRangos().get(1), ac, sb);
                        sb.append("Resultado Ackley sin mejora: " + ac.ejecucion(vector) + "\n");
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "GriewankFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        GriewankFunction gf = new GriewankFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(2), config.getRangos().get(3), config.getOptimos().get(1));
                        double vector[] = gf.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(2), config.getRangos().get(3), gf, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(2), config.getRangos().get(3), gf, sb);
                        sb.append("Resultado GriewankFunction sin mejora: " + gf.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "MichalewiczFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        MichalewiczFunction gf2 = new MichalewiczFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(4), config.getRangos().get(5), config.getOptimos().get(2));
                        double vector[] = gf2.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(4), config.getRangos().get(5), gf2, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(4), config.getRangos().get(5), gf2, sb);
                        sb.append("Resultado MichalewiczFunction sin mejora: " + gf2.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "PermFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        PermFunction pf = new PermFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(6), config.getRangos().get(7), config.getOptimos().get(2));
                        double vector[] = pf.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(6), config.getRangos().get(7), pf, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(6), config.getRangos().get(7), pf, sb);
                        sb.append("Resultado PermFunction: " + pf.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "RastringinFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        RastringinFunction rf = new RastringinFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(8), config.getRangos().get(9), config.getOptimos().get(3));
                        double vector[] = rf.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(8), config.getRangos().get(9), rf, sb);
                         blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(8), config.getRangos().get(9), rf, sb);
                        sb.append("Resultado RastringinFunction sin mejora: " + rf.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "RosenbrockFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        RosenbrockFunction rf = new RosenbrockFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(10), config.getRangos().get(11), config.getOptimos().get(4));
                        double vector[] = rf.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(10), config.getRangos().get(11), rf, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(11), config.getRangos().get(11), rf, sb);
                        sb.append("Resultado RosenbrockFunction: " + rf.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "RotatedHypeEllipsoid":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        RotatedHypeEllipsoid gf = new RotatedHypeEllipsoid(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(12), config.getRangos().get(13), config.getOptimos().get(5));
                        double vector[] = gf.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(12), config.getRangos().get(13), gf, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(12), config.getRangos().get(13), gf, sb);
                        sb.append("Resultado RotatedHypeEllipsoid: " + gf.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "SchewefelFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        SchewefelFunction sf = new SchewefelFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(14), config.getRangos().get(15), config.getOptimos().get(6));
                        double vector[] = sf.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(14), config.getRangos().get(15), sf, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(14), config.getRangos().get(15), sf, sb);
                        sb.append("Resultado Schewefel: " + sf.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());
                    }
                    break;
                case "TridFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        TridFunction tf = new TridFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(16), config.getRangos().get(17), config.getOptimos().get(7));
                        double vector[] = tf.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(16), config.getRangos().get(17), tf, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(16), config.getRangos().get(17), tf, sb);
                        sb.append("Resultado TridFunction: " + tf.ejecucion(vector));
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "DixonPriceFunction":
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        sb = new StringBuilder();
                        DixonPriceFunction df = new DixonPriceFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(18), config.getRangos().get(19), config.getOptimos().get(8));
                        double vector[] = df.vectorAleatorios();
                        bl3.busquedaMejor(config.getDimension(), 3, config.getIteraciones().get(0),
                                vector, config.getRangos().get(18), config.getRangos().get(19), df, sb);
                        blk.busquedaMejor(config.getDimension(), config.getIteraciones().get(0),
                                vector, config.getRangos().get(18), config.getRangos().get(19), df, sb);
                        sb.append("Resultado DixonPrice: " + df.ejecucion(vector));
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
            File directorio=new File("log");
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
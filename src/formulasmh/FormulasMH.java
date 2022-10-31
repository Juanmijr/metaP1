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
        VNS vns = new VNS();
        StringBuilder sb = null;
        double rangoMin = 0;
        double rangoMax = 0;
        int iteraciones = config.getIteraciones();
        int numRangos = config.getNumRangos();
        double porcentajeObstencionVecinos = config.getPorcentajeObstencionVecinos();
        double porcentajeRangoVecinos = config.getPorcentajeRangoVecinos();
        double porcentajeObscilacion = config.getPorcentajeObscilacion();
        int tamTabu = config.getTamTabu();
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
                        blk.busquedaMejor(config.getDimension(), false, iteraciones,vector, ac, sb, porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado Ackley sin mejora: " + ac.ejecucion(vector) + "\n");
                        blk.busquedaMejor(config.getDimension(), true, iteraciones,vector, ac, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado Ackley sin mejora: " + ac.ejecucion(vector) + "\n");
                        bt.BTabu(iteraciones, vector, tamTabu, ac, numRangos, sb, porcentajeObscilacion, porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado Ackley sin mejora: " + ac.ejecucion(vector) + "\n");
                        vns.ejecucion(iteraciones, vector, tamTabu, ac,numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "GriewankFunction":
                    rangoMin = config.getRangos().get(2);
                    rangoMax = config.getRangos().get(3);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        GriewankFunction gf = new GriewankFunction(config.getSemillas().get(k), config.getDimension(),
                                rangoMin, rangoMax, config.getOptimos().get(1));
                        double vector[] = gf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado GriewankFunction sin mejora: " + gf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones,vector, gf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado GriewankFunction sin mejora: " + gf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), true, iteraciones,vector, gf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado GriewankFunction sin mejora: " + gf.ejecucion(vector) + '\n');
                        bt.BTabu(iteraciones, vector, tamTabu, gf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado GriewankFunction sin mejora: " + gf.ejecucion(vector) + '\n');
                        vns.ejecucion(iteraciones, vector, tamTabu, gf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "MichakewiczFunction":
                    rangoMin = config.getRangos().get(4);
                    rangoMax = config.getRangos().get(5);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        MichalewiczFunction mf = new MichalewiczFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(4), config.getRangos().get(5), config.getOptimos().get(2));
                        double vector[] = mf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado GriewankFunction2 sin mejora: " + mf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones,vector, mf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado GriewankFunction2 sin mejora: " + mf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), true, iteraciones,vector, mf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado GriewankFunction2 sin mejora: " + mf.ejecucion(vector) + '\n');
                        bt.BTabu(iteraciones, vector, tamTabu, mf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        vns.ejecucion(iteraciones, vector, tamTabu, mf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "PermFunction":
                    rangoMin = config.getRangos().get(6);
                    rangoMax = config.getRangos().get(7);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        PermFunction pf = new PermFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(6), config.getRangos().get(7), config.getOptimos().get(2));
                        double vector[] = pf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado PermFunction: " + pf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones, vector, pf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado PermFunction: " + pf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), true, iteraciones, vector, pf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado PermFunction: " + pf.ejecucion(vector) + '\n');
                        bt.BTabu(iteraciones, vector, tamTabu, pf, numRangos, sb,porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado PermFunction: " + pf.ejecucion(vector) + '\n');
                        vns.ejecucion(iteraciones, vector, tamTabu, pf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "RastringinFunction":
                    rangoMin = config.getRangos().get(8);
                    rangoMax = config.getRangos().get(9);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RastringinFunction rf = new RastringinFunction(config.getSemillas().get(k), config.getDimension(),
                                rangoMin, rangoMax, config.getOptimos().get(3));
                        double vector[] = rf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado RastringinFunction sin mejora: " + rf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones, vector, rf, sb, porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado RastringinFunction sin mejora: " + rf.ejecucion(vector) + '\n');

                        blk.busquedaMejor(config.getDimension(), true, iteraciones, vector, rf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado RastringinFunction sin mejora: " + rf.ejecucion(vector) + '\n');

                        bt.BTabu(iteraciones, vector, tamTabu, rf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado RastringinFunction sin mejora: " + rf.ejecucion(vector) + '\n');

                        vns.ejecucion(iteraciones, vector, tamTabu, rf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "RosenbrockFunction":
                    rangoMin = config.getRangos().get(10);
                    rangoMax = config.getRangos().get(11);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RosenbrockFunction rf = new RosenbrockFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(10), config.getRangos().get(11), config.getOptimos().get(4));
                        double vector[] = rf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado RosenbrockFunction: " + rf.ejecucion(vector) + '\n');

                        blk.busquedaMejor(config.getDimension(), false, iteraciones, vector, rf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado RosenbrockFunction: " + rf.ejecucion(vector) + '\n');

                        blk.busquedaMejor(config.getDimension(), true, iteraciones, vector, rf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado RosenbrockFunction: " + rf.ejecucion(vector) + '\n');

                        bt.BTabu(iteraciones, vector, tamTabu, rf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado RosenbrockFunction: " + rf.ejecucion(vector) + '\n');
                        vns.ejecucion(iteraciones, vector, tamTabu, rf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "RotatedHypeEllipsoid":
                    rangoMin = config.getRangos().get(12);
                    rangoMax = config.getRangos().get(13);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        RotatedHypeEllipsoid gf = new RotatedHypeEllipsoid(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(12), config.getRangos().get(13), config.getOptimos().get(5));
                        double vector[] = gf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado RotatedHypeEllipsoid: " + gf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones, vector, gf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado RotatedHypeEllipsoid: " + gf.ejecucion(vector) + '\n');

                        blk.busquedaMejor(config.getDimension(), true, iteraciones, vector, gf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado RotatedHypeEllipsoid: " + gf.ejecucion(vector) + '\n');

                        bt.BTabu(iteraciones, vector, tamTabu, gf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado RotatedHypeEllipsoid: " + gf.ejecucion(vector) + '\n');
                        vns.ejecucion(iteraciones, vector, tamTabu, gf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);

                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "SchewefelFunction":
                    rangoMin = config.getRangos().get(14);
                    rangoMax = config.getRangos().get(15);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        SchewefelFunction sf = new SchewefelFunction(config.getSemillas().get(k), config.getDimension(),
                                config.getRangos().get(14), config.getRangos().get(15), config.getOptimos().get(6));
                        double vector[] = sf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado Schewefel: " + sf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones, vector, sf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado Schewefel: " + sf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), true, iteraciones, vector, sf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado Schewefel: " + sf.ejecucion(vector) + '\n');
                        bt.BTabu(iteraciones, vector, tamTabu, sf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado Schewefel: " + sf.ejecucion(vector) + '\n');
                        vns.ejecucion(iteraciones, vector, tamTabu, sf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "TridFunction":
                    rangoMin = config.getRangos().get(16);
                    rangoMax = config.getRangos().get(17);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        TridFunction tf = new TridFunction(config.getSemillas().get(k), config.getDimension(),
                                rangoMin, rangoMax, config.getOptimos().get(7));
                        double vector[] = tf.vectorAleatorios();
                        sb = new StringBuilder("\nResultado TridFunction: " + tf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones, vector, tf, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado TridFunction: " + tf.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), true, iteraciones, vector, tf, sb, porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());
                        sb = new StringBuilder("\nResultado TridFunction: " + tf.ejecucion(vector) + '\n');
                        bt.BTabu(iteraciones, vector, tamTabu, tf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado TridFunction: " + tf.ejecucion(vector) + '\n');
                        vns.ejecucion(iteraciones, vector, tamTabu, tf, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

                    }
                    break;
                case "DixonPriceFunction":
                    rangoMin = config.getRangos().get(18);
                    rangoMax = config.getRangos().get(19);
                    for (int k = 0; k < config.getSemillas().size(); k++) {
                        DixonPriceFunction df = new DixonPriceFunction(config.getSemillas().get(k), config.getDimension(),
                                rangoMin, rangoMax, config.getOptimos().get(8));
                        double vector[] = df.vectorAleatorios();
                        sb = new StringBuilder("\nResultado DixonPrice: " + df.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), false, iteraciones, vector, df, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BL3_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado DixonPrice: " + df.ejecucion(vector) + '\n');
                        blk.busquedaMejor(config.getDimension(), true, iteraciones, vector, df, sb,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BLK_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado DixonPrice: " + df.ejecucion(vector) + '\n');
                        bt.BTabu(iteraciones, vector, tamTabu, df, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_BTABU_" + config.getSemillas().get(k) + ".txt", sb.toString());

                        sb = new StringBuilder("\nResultado DixonPrice: " + df.ejecucion(vector) + '\n');
                        vns.ejecucion(iteraciones, vector, tamTabu, df, numRangos, sb, porcentajeObscilacion,porcentajeObstencionVecinos, porcentajeRangoVecinos);
                        guardarArchivo("log/" + config.getFunciones().get(i) + "_VNS_" + config.getSemillas().get(k) + ".txt", sb.toString());

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

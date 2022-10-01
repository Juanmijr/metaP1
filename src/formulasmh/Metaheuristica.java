package metaprimerapr;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvaro
 */
public class Metaheuristica implements Runnable{
    private Random aleatorio;
    private Archivodedatos archivo;
    private StringBuilder log;
    private CountDownLatch cdl;

    public Metaheuristica(Archivodedatos archivo, CountDownLatch cdl, Long semilla) {
        this.archivo = archivo;
        this.cdl = cdl;
        aleatorio = new Random(semilla);
        log = new StringBuilder();
    }
    
    
    @Override
    public void run() {
        //Inicializacion aleatorio de la solucion
        log.append("El coste de la solucion es X");
        long tiempoinicial = System.currentTimeMillis();
        
        //Ejecuci�n de la metaheuristica
        
        log.append("Iteraci�n Y \n Coste mejor X \n Se acepta solucion generada con coste XXX...");
        
        long tiempofin = System.currentTimeMillis() / 1000;
        //Finalizacion de la metaheuristica
        log.append("El costo final es X \n Duracion " + (tiempofin - tiempoinicial)/1000 + " segundos");
        cdl.countDown();
    }
    public String getLog()
    {
        return log.toString();
    }
    
}
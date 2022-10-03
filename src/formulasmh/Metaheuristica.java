package metaprimerapr;
import formulasmh.Configurador;
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
    int calidadSol(int sol)
    {
        //Valorar calidad
        return sol;
    }
    
    
    double busquedaMejor(int tam, int evaluaciones, double[] solActual, double rMin, double rMax, int numVecinos, int k, String funcion )
    {
        double[] solVecino;
        double[] mejorVecino;
        mejorVecino=solActual;
        double mejorCosteVecino = 999999;
        double mejorCoste = calculaCoste(solActual,funcion);
        boolean mejora = true;
        int i = 0;
        while( i < evaluaciones &&  mejora)
        {
            for( int j = 0; j < k; j++)
            {
                for(int z = 0; z < tam; z++)
                {
                    double uniforme = Math.random();
                    if(uniforme<=0.3)
                    {
                        double inf,sup;
                        inf=solActual[k]*0.9;
                                sup=solActual[k]*1.1;
                                if(inf < rMin)
                                {
                                    inf=rMin;
                                }
                                if(sup > rMax)
                                {
                                    sup=rMax;
                                }
                                solVecino[k]= (Math.random() * (sup + sup - inf)) + inf;
                    }
                    else
                    {
                        solVecino[k] = solActual[k];
                    } 
                    double costeVecino = calculaCoste(solVecino, funcion);
                    if (costeVecino < mejorCosteVecino){
                        mejorCosteVecino = costeVecino;
                        solActual[j] = solVecino[j];
                    }
                }
            }
        }
        return mejorCoste;
    }
    
}
//Que escribir en el log
/*main
vector solActual;
int tam,opcion;
string numFuncion[10]
timer t


for(int 0; i<3; i++)
{
    for(j<10){
    t.start()
    double c2= soluBLocal()tam,evaluaciones,solActual,rmin,rmax,i)
    t.stop()
    }
}

*/
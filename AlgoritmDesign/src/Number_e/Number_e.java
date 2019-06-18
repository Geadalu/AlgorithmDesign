/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Number_e;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author lcalzado
 */
public class Number_e {
    
    static Scanner read = new Scanner(System.in);
    static Random rand = new Random();
    
    private static void main (String [] args){
        int n, xIzq, xDer;
        int limI = 0;
        int limS = 1;
        
        System.out.println("Function f(x) = e^-2x");
        System.out.println("Tamaño de la muestra:");
        n = read.nextInt();
        
        System.out.println("Que vaya desde x = ");
        limI = read.nextInt();
        System.out.println("Hasta x = ");
        limS = read.nextInt();
        
        System.out.println("Area segun VM: "+areaVM(n, limI, limS));
        System.out.println("Area segun Probabilidad "+areaP(n, limI, limS));
        
        
    }
    
    private static double areaVM(int n, int xIzq, int xDer){
        int i;
        double randomX, randomY;
        double [] valores = new double [n];
        double m;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*xDer-xIzq;
            randomY = funcion(randomX);
            valores[i] = (xDer-xIzq) * randomY;
        }
        //System.out.println("v = "+valores[n-1]);
        m = media(valores);
        intervaloConfianza(n, valores, m);
        
        return m;
    }
    
    /**
     * No está bien
     * @param n
     * @param limI
     * @param limS
     * @return 
     */
    private static double areaP(int n, int limI, int limS) {
    	int i;
        int buenos = 0;
        double randomX, randomY;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*limS - limI;
            randomY = rand.nextDouble()*limS - limI;
            if (randomY < funcion(randomX)){
                buenos++;
            }
        }
        intervaloConfianzaP((double)buenos/(double)n, n);
        return (limS-limI)*funcion(limS)*(double)buenos/(double)n;
    }
    
    private static double funcion(double x){
        return Math.pow(Math.E, -2*x);
    }
    
    private static double media (double [] valores){
        int i;
        double total = 0.0;
        
        for(i=0; i<valores.length; i++){
            total += valores[i];
        }
        return total/valores.length;
    }
    
    private static void intervaloConfianza(int n, double [] valores, double media){
        double [] intervalos = new double [2];
        double cuasiV = cuasiVarianza(n, valores, media);
        intervalos[0] = (media - (1.96 * cuasiV/Math.sqrt(n)));
        intervalos[1] = (media + (1.96 * cuasiV/Math.sqrt(n)));
        System.out.println("Intervalo de confianza: ("+intervalos[0]+", "+intervalos[1]+")");
        
    }
    
    private static double cuasiVarianza(int n, double [] valores, double media){
        double cosa = 0.0;
        int i;
        
        for (i=0; i<valores.length; i++){
            cosa += Math.pow((valores[i] - media), 2);
        }
        
        return Math.sqrt(cosa/(n-1)); //desviacion tipica
    }
    
    private static void intervaloConfianzaP(double p, double n){
        double [] intervalos = new double [2];
        intervalos[0] = p - (1.96 * (Math.sqrt(p * (1-p)/n)));
        intervalos[1] = p + (1.96 * (Math.sqrt(p * (1-p)/n)));
        System.out.println("Intervalo de confianza Proporcion: ("+intervalos[0]+", "+intervalos[1]+")");
    }
}
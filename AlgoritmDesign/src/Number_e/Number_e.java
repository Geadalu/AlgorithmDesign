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
    
    public static void main (String [] args){
        int n;
        int limI, limS;
        
        System.out.println("Function f(x) = e^-2x");
        System.out.println("Tamaño de la muestra:");
        n = read.nextInt();
        //n = 900000;
        
        System.out.println("Que vaya desde x = ");
        limI = read.nextInt();
        System.out.println("Hasta x = ");
        limS = read.nextInt();
        
        System.out.println("Area segun VM: "+areaVM(n, limI, limS));
        System.out.println("Area segun Probabilidad "+areaP(n, limI, limS));
        
        
    }
    
    private static double areaVM(int n, int limI, int limS){
        int i;
        double randomX, randomY;
        double [] valores = new double [n];
        double m;
        double suma = 0.0;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*(limS-limI)+limI;
            randomY = funcion(randomX);
            valores[i] = (limS-limI) * randomY;
        }
        
        //System.out.println("v = "+valores[n-1]);
        m = media(valores);
        intervaloConfianza(n, valores, m);
        
        return m;
    }
    
    private static double areaP(int n, int limI, int limS) {
    	int i;
        int buenos = 0;
        double randomX, randomY;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*(limS - limI)+limI;
            randomY = rand.nextDouble()*(funcion(limI) - 0) + 0;
            //randomY = rand.nextDouble()*funcion(-2);
            //System.out.println("(randomX, randomY) = ("+randomX+", "+randomY+")");
            if (randomY < funcion(randomX)){
                buenos++;
            }
            
        }
        System.out.println("buenos = "+buenos);
        intervaloConfianzaP((double)buenos/(double)n, n, limS, limI);
        return (limS-limI)*funcion(limI)*(double)buenos/(double)n;
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
    
    private static void intervaloConfianzaP(double p, double n, int limS, int limI){
        double [] intervalos = new double [2];
        //System.out.println(p);
        intervalos[0] = p - (1.96 * (Math.sqrt(p * (1-p)/n)));
        intervalos[1] = p + (1.96 * (Math.sqrt(p * (1-p)/n)));
        System.out.println("Intervalo de confianza probabilidad: ("+intervalos[0]*funcion(limI)*(limS-limI)+", "+intervalos[1]*funcion(limI)*(limS-limI)+")");
    }
}
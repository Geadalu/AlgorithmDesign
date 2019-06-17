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
        int n, xIzq, xDer;
        
        System.out.println("Function f(x) = e^-2x");
        System.out.println("Tamaño de la muestra:");
        n = read.nextInt();
        
        System.out.println("Que vaya desde x = ");
        xIzq = read.nextInt();
        System.out.println("Hasta x = ");
        xDer = read.nextInt();
        
        
        
        
        
    }
    
    public static double areaVM(int n, int xIzq, int xDer){
        int i;
        double randomX, randomY;
        double [] valores = new double [n];
        double m;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*xDer-xIzq;
            randomY = funcion(randomX);
            valores[i] = (xDer-xIzq) * randomY;
        }
        m = media(valores);
        intervaloConfianza(n, valores, m);
        
        return m/n;
    }
    
    public static double funcion(double x){
        return Math.pow(Math.E, -2*x);
    }
    
    public static double media (double [] valores){
        int i;
        int total = 0;
        
        for(i=0; i<valores.length; i++){
            total += valores[i];
        }
        return total/valores.length;
    }
    
    public static void intervaloConfianza(int n, double [] valores, double media){
        double [] intervalos = new double [2];
        double cuasiV = cuasiVarianza(n, valores, media);
        intervalos[0] = (media - (1.96 * cuasiV/Math.sqrt(n)));
        intervalos[1] = (media + (1.96 * cuasiV/Math.sqrt(n)));
        
        
    }
    
    public static double cuasiVarianza(int n, double [] valores, double media){
        double cosa = 0.0;
        int i;
        
        for (i=0; i<valores.length; i++){
            cosa += Math.pow((valores[i] - media), 2);
        }
        
        return Math.sqrt(cosa/(n-1)); //desviacion tipica
    }
}

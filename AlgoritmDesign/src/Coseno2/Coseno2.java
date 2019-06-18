/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coseno2;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lcalzado
 */
public class Coseno2 {
    
    static Scanner read = new Scanner(System.in);
    static Random rand = new Random();
    
    public static void main (String [] args){
        int n, limI, limS;

        
        System.out.println("Function xcos(x)\nTamaño de la muestra:");
        n = read.nextInt();
        System.out.println("Que vaya desde (limI): ");
        limI = read.nextInt();
        System.out.println("Hasta (limS): ");
        limS = read.nextInt();

        System.out.println("Área según VM = "+areaVM(n, limI, limS));
        System.out.println("Área según Proporción = "+areaP(n, limI, limS));
        //System.out.println("Área real (mal calculada) = "+areaFuncion());
        
        
    }
    
    private static double areaVM(int n, int limI, int limS){
        int i;
        double [] valores = new double[n];
        double randomX, randomY, media;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*limS - limI;
            randomY = funcion(randomX);
            valores[i] = randomY * (limS-limI);
        }
        media = media(valores);
        intervaloConfianza(valores, media);
        
        return media;
        
    }
    
    private static double areaP(int n, int limI, int limS){
        int i;
        int buenos = 0;
        double randomX, randomY, p;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*limS - limI;
            randomY = rand.nextDouble()*limS - limI;
            if (randomY < funcion(randomX)){
                buenos++;
            }
        }
        p = (double)buenos/(double)n;
        intervaloConfianzaP(p, n);
        
        return (limS-limI)*Math.abs(funcion(limS))*p;
    }
    
    private static double funcion(double x){
        return Math.cos(x)*x;
    }
    
    private static double media (double [] valores){
       int i;
       double total = 0.0;
        
        for(i=0; i<valores.length; i++){
            total += valores[i];
        }
        return total/valores.length;
    }
     
    private static void intervaloConfianza(double [] valores, double media){
        double [] intervalo = new double[2];
        double cuasiV = cuasiV(valores, media);
        intervalo[0] = media - 1.96 * (cuasiV / Math.sqrt(valores.length));
        intervalo[1] = media + 1.96 * (cuasiV / Math.sqrt(valores.length));
        System.out.println("Intervalo de confianza: ("+intervalo[0]+", "+intervalo[1]+")");
         
         
    }
    
    private static void intervaloConfianzaP(double p, int n){
       double [] intervalo = new double[2];
       intervalo[0] = p - 1.96 * (Math.sqrt((p*(1-p)/n)));
       intervalo[1] = p + 1.96 * (Math.sqrt((p*(1-p)/n)));
       System.out.println("Intervalo de confianza proporciones: ("+intervalo[0]+", "+intervalo[1]+")");
    }
     
    private static double cuasiV (double [] valores, double media){
        int i;
        double cuasiV = 0.0;

        for (i=0; i<valores.length; i++){
            cuasiV += Math.pow((valores[i] - media), 2);
        }
        return (Math.sqrt(cuasiV/(valores.length-1)));
     }
}

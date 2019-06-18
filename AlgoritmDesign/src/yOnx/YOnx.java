/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yOnx;
import java.util.Scanner;
import java.util.Random;

public class YOnx {
    
    static Scanner read = new Scanner(System.in);
    static Random rand = new Random();
    
    public static void main (String [] args){
        int n, limS, limI;
        
        System.out.println("Function f(x) = 2^x\n");
        System.out.println("Introduce el tamaño de la muestra:");
        
        n = read.nextInt();
        
        System.out.println("Límite inferior limI:");
        limI = read.nextInt();
        System.out.println("Límite superior limS:");
        limS = read.nextInt();
        
        System.out.println("Área según el teorema del VM = "+areaVM(n, limI, limS));
        System.out.println("Área según probabilidad = "+areaP(n, limI, limS));
        System.out.println("Área real = "+areaFuncion(limI, limS));
        
        
    }
    
    private static double areaVM(int n, int limI, int limS){
        double [] resultados = new double [n];
        int i;
        double randomX, randomY, media;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*limS - limI;
            randomY = funcion(randomX);
            resultados[i] = randomX * randomY;
        }
        media = media(resultados);
        intervaloConfianza(media, resultados);
        return media;
    }
    
    /**
     * Siempre salen todos los puntos dentro
     * @param n
     * @param limS
     * @param limI
     * @return 
     */
    private static double areaP(int n, int limS, int limI){
        //double [] resultados = new double[n];
        int i;
        int buenos = 0;
        double randomX, randomY;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*limS - limI;
            randomY = rand.nextDouble()*limS - limI;
            //System.out.println("randomX = "+randomX+"\nrandomY = "+randomY);
            if (randomY < funcion(randomX)){
                buenos ++;
            }
        }
        System.out.println(buenos);
        intervaloConfianzaP((double)buenos/(double)n, n);
        
        return funcion(limS)*(limS-limI)*(double)buenos/n;
    }
    
    private static double areaFuncion(int limI, int limS){
        
        return 1.0;
    }
    
    private static double funcion (double x){
        return Math.pow(2, x);
    }
    
    private static double media (double [] matriz){
        int i;
        double result = 0.0;
        
        for (i=0; i<matriz.length; i++){
            result += matriz[i];
        }
        
        return result/matriz.length;
    }
    
    private static void intervaloConfianza(double media, double [] resultados){
        double [] intervalo = new double[2];
        double cuasiV = cuasiV(resultados);
        intervalo[0] = media - 1.96 * (cuasiV / Math.sqrt(resultados.length));
        intervalo[1] = media + 1.96 * (cuasiV / Math.sqrt(resultados.length));
        System.out.println("Intervalo de confianza: ("+intervalo[0]+", "+intervalo[1]+")");
    }
    
    private static void intervaloConfianzaP(double p, int n){
        double [] intervalo = new double[2];
        intervalo[0] = p - 1.96 * (Math.sqrt((1-p)/n));
        intervalo[1] = p + 1.96 * (Math.sqrt((1-p)/n));
        System.out.println("Intervalo de confianza proporciones: ("+intervalo[0]+", "+intervalo[1]+")");
    }
    
    private static double cuasiV (double [] resultados){
        int i;
        double media = media(resultados);
        double cuasiV = 0.0;
        
        for (i=0; i<resultados.length; i++){
            cuasiV += Math.pow(resultados[i] - media, 2);
        }
        return Math.sqrt(cuasiV/resultados.length-1);
    }
    
}

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
        System.out.println("Área según probabilidad = "+areaP(n));
        System.out.println("Área real = "+areaFuncion(limI, limS));
        
        
    }
    
    public static double areaVM(int n, int limI, int limS){
        double [] resultados = new double [n];
        int i;
        double randomX, randomY;
        
        for (i=0; i<n; i++){
            randomX = rand.nextDouble()*limS - limI;
            randomY = funcion(randomX);
            resultados[i] = randomX * randomY;
        }
        
    }
    
    
    public static double areaP(int n){
        
        
    }
    
    public static double areaFuncion(int limI, int limS){
        
        return 1.0;
    }
    
    public static double funcion (double x){
        return Math.pow(x, 2);
    }
    
}

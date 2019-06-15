package Ackermann;

import java.util.Scanner;

public class Ackermann {

	static Scanner read = new Scanner (System.in);
	static int [][] soluciones = new int [100][100];
	
	public static void main (String [] args) {
		int m, n, i, j;
		byte elecc;
		
		System.out.println("Función de Ackermann\nA(m, n) =\nn+1 si m=0\nA(m-1, 1) si m>0 y n=0\nA(m-1, A(m, n-1)) si m>0 y n>0");
		System.out.println("\nEsta función no admite m>2\nIntroduce la m:");
		m = read.nextInt();
		System.out.println("Introduce la n:");
		n = read.nextInt();
		System.out.println("1 backward\n2 forward");
		elecc = read.nextByte();
		
		if (elecc == 1) {
			for (i=0; i<100; i++) {
				for (j=0; j<100; j++) {
					soluciones[i][j] = 0;
				}
			}
			//printMatriz(soluciones);
			System.out.println("Resultado backward(): "+backward(m, n));
		} else if (elecc == 2) {
			forward(m, n);
		}
		
		System.out.println("Resultado funcion(): "+funcion(m, n));
	
		
		
	}
	
	
	public static int funcion (int m, int n) {
		
		if (m==0) {
			return n+1;
		} else if (m>0 && n==0) {
			return funcion(m-1, 1);
		} else if (m>0 && n>0) {
			return funcion(m-1, funcion(m, n-1));
		}
		return -1;
	}
	

	public static int backward(int m, int n) {
		//System.out.println("Soluciones = "+soluciones[m][n]);
		
		if (soluciones[m][n] == 0) {
			//System.out.println("m = "+m);
			//System.out.println("n = "+n+"\n");
					
			if (m==0) {
				soluciones[m][n] = n+1;
			} else if (m>0 && n==0) {
				soluciones[m][n] = backward(m-1, 1);
			} else if (m>0 && n>0) {
				soluciones[m][n] = backward(m-1, backward(m, n-1));
			}
		}
		return soluciones[m][n];
	}
	
 	
	public static void forward(int m, int n) {
		System.out.println("Error!");
	}
	
	  public static void printMatriz(int [][] matriz){
	        int i, j;
	        for (i=0; i<matriz.length; i++){
	            for (j=0; j<matriz[0].length; j++){
	                System.out.print(matriz[i][j]+ " ");
	            }
	            System.out.println("");
	        }
	    }
	
	
}

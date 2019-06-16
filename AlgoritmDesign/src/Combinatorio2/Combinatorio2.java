package Combinatorio2;
import java.util.Scanner;

public class Combinatorio2 {
	
	static Scanner read = new Scanner (System.in);
	static int [][] soluciones;
	
	public static void main (String [] args) {
		int n, k, i, j;	
		
		System.out.println("Calcula el número combinatorio\nn\nk\n");
		System.out.println("f(n, k) = f(n-1, k-1) + f(n-1, k)\nf(n, 0) = 1; f(0, k) = 0");
		//System.out.println("\nn! = n-1 * n-2 * n-3 * ... * n-(n-1)");
		System.out.println("Introduce n:");
		n = read.nextInt();
		System.out.println("Introduce k:");
		k = read.nextInt();
		

		soluciones = new int [n+1][k+1];

		//System.out.println(calcularFact(2));
		System.out.println("");
		inicializarMatriz();
		forward(n, k);
		
		inicializarMatriz();
		System.out.println("Backward(): "+backward(n, k));
		
		System.out.println("Funcion(): "+funcion(n, k));
		
	}
	
	public static int funcion (int n, int k) {
		if (n==0) {
			return 0;
		} else if (k==0 || k==n) {
			return 1;
		} else {
			return funcion(n-1, k-1) + funcion(n-1, k);
		}
	}
	
	public static void forward (int n, int k) {
		int i;
		int j;

		System.out.println("");
		for (i=1; i<soluciones.length; i++) {
			for (j=1; j<soluciones[0].length; j++) {
				if (soluciones[i][j] == -1) {
					soluciones[i][j] = soluciones[i-1][j-1] + soluciones[i-1][j];
				}
			}
		}
		printMatriz(soluciones);
		System.out.println("Forward(): " + soluciones[n][k]);
	}
	
	public static int backward (int n, int k) {
		if (soluciones[n][k] == -1) {
			soluciones[n][k] = backward(n-1, k-1) + backward(n-1, k);
		}
		return soluciones[n][k];
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
	 
	 public static void inicializarMatriz() {
		int i, j;
		 
		for (i=1; i<soluciones.length; i++) {
			for (j=1; j<soluciones[0].length; j++) {
				if (i==j) {
					soluciones[i][j] = 1;
				} else {
					soluciones[i][j] = -1;
				}
			}
		}
			
		for (j=1; j<soluciones.length; j++) {
			soluciones[j][0] = 1;
		}
			
		for (j=0; j<soluciones[0].length; j++) {
			soluciones[0][j] = 0;
		}
	 }

}

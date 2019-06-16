package Combinatorio;
import java.util.Scanner;

public class Combinatorio {
	
	static Scanner read = new Scanner (System.in);
	static int [] solucionesFact;
	static int [] solucionesComb;
	
	public static void main (String [] args) {
		int n, k, i;	
		
		System.out.println("Calcula el número combinatorio\nn\nk\n");
		System.out.println("       n!\n----------------\n k! * (n - k)!");
		//System.out.println("\nn! = n-1 * n-2 * n-3 * ... * n-(n-1)");
		System.out.println("Introduce n:");
		n = read.nextInt();
		System.out.println("Introduce k:");
		k = read.nextInt();
		
		//System.out.println("GetMax: "+getMax(n, k));
		solucionesFact = new int [getMax(n, k)+1];
		solucionesComb = new int [solucionesFact.length];
		
		for (i=2; i<solucionesFact.length; i++) {
			solucionesFact[i] = -1;
			solucionesComb[i] = -1;
		}
		solucionesFact[0] = 1;
		solucionesFact[1] = 1;
	
		//System.out.println(calcularFact(2));
		System.out.println("");
		forward(n, k);
		
		for (i=2; i<solucionesFact.length; i++) {
			solucionesFact[i] = -1;
			solucionesComb[i] = -1;
		}
		
		System.out.println("Backward(): "+backward(n) / (solucionesFact[k] * solucionesFact[n-k]));
		
		System.out.println("Funcion(): "+funcion(n, k));
		
	}
	
	public static int funcion (int n, int k) {
		return calcularFact(n) / (calcularFact(k) * calcularFact(n-k));
	}
	
	
	public static void forward(int n, int k) {
		int i;
		
		for (i=2; i<solucionesFact.length; i++) {
			solucionesFact[i] = solucionesFact[i-1] * i;
		}
		
		System.out.println("Forward(): "+solucionesFact[n] / (solucionesFact[k] * solucionesFact[n-k]));
	}
	
	public static int backward(int n) {
		
		if (solucionesFact[n] == -1) { //no está calculado
			
			solucionesFact[n] = backward(n-1) * n;
		}
		return solucionesFact[n];
	}
	
	
	public static int getMax(int i, int j) {
		if (i>j) {
			return i;
		} else {
			return j;
		}
	}
	
	public static int calcularFact (int num) {
		int i;
		int sol = 1;
		
		for (i=2; i<num+1; i++) {
			sol *= i;
		}
		return sol;
	
	}
	
	public static void printArray1D (int [] array) {
		
	}
}

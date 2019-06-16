package Fibonacci;
import java.util.Scanner;

public class Fibonacci {
	
	static Scanner read = new Scanner(System.in);
	static int [] resultado = new int [100];
	
	public static void main (String [] args) {
		int n, i;
		
		for (i=0; i<100; i++) {
			resultado[i] = -1;
		}
		
		System.out.println("Función de Fibonacci\nf(0)=0\nf(1)=1\nf(n)=f(n-1)+f(n-2)\n");
		System.out.println("Introduce la n:");
		n = read.nextInt();
		
		forward(n);
		for (i=0; i<100; i++) {
			resultado[i] = -1;
		}
		System.out.println("Backward(): "+backward(n));
		
		System.out.println("Funcion(): "+funcion(n));
		System.out.println("ok bye");
		
	}
	
	
	public static int funcion(int n) {
		if (n==0) {
			return 0;
		} else if (n==1) {
			return 1;
		} else {
			return funcion(n-1)+funcion(n-2);
		}
	}
	
	public static void forward(int n) {
		int i, j;
		resultado[0] = 0;
		resultado[1] = 1;
		
		for(i=2; i<100; i++) {
			resultado[i] = resultado[i-1] + resultado[i-2];
		}
		System.out.println("Forward(): "+resultado[n]);
	}
	
	public static int backward (int n) {
		if (resultado[n] == -1) {
			if (n==0) {
				resultado[n] = 0;
			} else if (n==1) {
				resultado[n] = 1;
			} else {
				resultado[n] = backward(n-1)+backward(n-2);
			}	
		}
		return resultado[n];
	}

}

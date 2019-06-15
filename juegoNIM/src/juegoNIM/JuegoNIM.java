package juegoNIM;

import java.util.Random;

public class JuegoNIM {
    static Random rnd = new Random();
    
    public static void main(String[] args) {
        crearTablero();
    }
    
    
    public static void crearTablero(){
        int filas;
        int columnas;
        int i, j;
        
        filas = rnd.nextInt() * 10 + 1;
        columnas = rnd.nextInt() * 10 + 1;
        System.out.println("Filas = "+filas);
        System.out.println("Columnas = "+columnas);
        
        
        int [][] tablero = new int [filas][columnas];
        
        for (i=0; i<filas; i++){
            for (j=0; j<columnas; j++){
                if (ponerAqui()){
                    tablero[i][j] = 1;
                } else {
                    tablero[i][j] = 0;
                }
            }
        }
        
        
        
    }
    
    public static boolean ponerAqui(){
        int rand;
        
        rand = (int) Math.random()*2 - 1;
        
        switch(rand){
            case 1:
                return true;
            case 2:
                return false;
            default:
                return false;
        }
    }
    
    public static void printMatriz(int [][] matriz){
        int i, j;
        for (i=0; i<matriz.length; i++){
            for (j=0; j<matriz[0].length; j++){
                System.out.print(matriz[i][j]+ " ");
                System.out.println("");
            }
        }
    }
}

package coseno;
import java.util.Random;
import java.util.Scanner;

public class Coseno {

    static Random rand = new Random();
    static Scanner read = new Scanner(System.in);
      
    public static void main(String[] args) {
        int k;
        double limI = 0.0;
        double limS = Math.PI/2;
        
        System.out.println("Calcular el área de 3cos(x) por debajo entre:\nx = 0\nx = pi/2\n");
        System.out.println("Introduce el tamaño de la muestra:");
        k = read.nextInt();
        
        System.out.println("Área según VM = "+calcularAreaVM(k, limI, limS));
        System.out.println("Área según Proporción = "+calcularAreaP(k, limI, limS));
        //System.out.println("Área real (mal calculada) = "+areaFuncion());
        
    }
    
    private static double calcularAreaVM(int k, double limI, double limS){
        double [] valores = new double[k];
        double randomX;
        double randomY;
        int i;
        double total = 0;
        
        for (i=0; i<k; i++){
           randomX = rand.nextDouble()*(limS - limI)+limI;
           randomY = funcion(randomX);
           valores[i] = Math.PI/2 * randomY;
           total += valores[i];
        }
        intervaloConfianza(valores);
        return total/k;
    }
    
    /**
     * Está mal
     * @param k
     * @return 
     */
    private static double calcularAreaP(int k, double limI, double limS){
        int i;
        double randomX, randomY;
        int validos = 0;
        
        for (i=0; i<k; i++){
            randomX = rand.nextDouble()*(limS - limI)+limI;
            randomY = rand.nextDouble()*(3 - 0)+0;
            if (randomY < funcion(randomX)){
                validos++;
            }
        }
        System.out.println("validos = "+validos);
        
        double areaCuadrado = (limS - limI)*3;
        intervaloConfianzaP((double)validos/k, k, areaCuadrado);
        return 3*(Math.PI/2-0)*validos/k;
    }
    
    private static double funcion(double x){
        return Math.cos(x)*3;
    }
    
    /*private static double areaFuncion(){
        return 3*(Math.sin(limS)) - 3*(Math.sin(limI));
    }*/
    
    private static void intervaloConfianza(double [] valores){
        double [] intervalos = new double [2];
        double media = media(valores);
        double cuasiVarianza = cuasiV(valores, media);
        
        intervalos[0] = media - (1.96*(cuasiVarianza/Math.sqrt(valores.length)));
        intervalos[1] = media + (1.96*(cuasiVarianza/Math.sqrt(valores.length)));
        
        System.out.println("Intervalo de confianza: ("+intervalos[0]+", "+intervalos[1]+")");
    }
    
    private static void intervaloConfianzaP(double p, int n, double area){
        double [] intervalos = new double [2];
        System.out.println("p = "+p);
        intervalos[0] = p - (1.96 * Math.sqrt((p*(1-p))/n));
        intervalos[1] = p + (1.96 * Math.sqrt((p*(1-p))/n));
        System.out.println("Intervalo de confianza con Proporción: ("+intervalos[0]*area+", "+intervalos[1]*area+")");
    }
    
    private static double media (double [] valores){
        int i;
        double media = 0.0;
        
        for (i=0; i<valores.length; i++){
            media += valores[i];
        }
        return media/valores.length;
    }
    
    private static double cuasiV(double [] valores, double media){
        double cuasiVarianza = 0.0;
        int i;
        
        for (i=0; i<valores.length; i++){
            cuasiVarianza += Math.pow((valores[i] - media), 2);
        }
        //System.out.println("Casi cuasivarianza = "+cuasiVarianza+"\n");
        System.out.println(valores.length);
        return Math.sqrt(cuasiVarianza/(valores.length-1));
    }

}

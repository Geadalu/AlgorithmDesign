package coseno;
import java.util.Random;
import java.util.Scanner;

public class Coseno {

    static Random rand = new Random();
    static Scanner read = new Scanner(System.in);
    static int limS = 3;
    static int limI = 0;

  
    public static void main(String[] args) {
        int k;
        
        System.out.println("Calcular el área de 3cos(x) por debajo entre:\nx = 0\nx = pi/2\n");
        System.out.println("Introduce el tamaño de la muestra:");
        k = read.nextInt();
        double [] valores = new double[k];
        
        System.out.println("Área según VM = "+calcularAreaVM(valores, k));
        System.out.println("Área según Proporción = "+calcularAreaP(k));
        System.out.println("Área real (mal calculada) = "+areaFuncion());
        
        
    }
    
    private static double calcularAreaVM(double [] valores, int k){
        double randomX;
        double randomY;
        int i;
        double total = 0;
        
        for (i=0; i<k; i++){
           randomX = rand.nextDouble()*Math.PI/2 - 0;
           randomY = funcion(randomX);
           valores[i] = randomX * randomY;
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
    private static double calcularAreaP(int k){
        int i;
        double randomX, randomY;
        int validos = 0;
        
        for (i=0; i<k; i++){
            randomX = rand.nextDouble()*Math.PI/2 - 0;
            randomY = rand.nextDouble()*limS - limI;
            if (randomY < funcion(randomX)){
                validos++;
            }
        }
        //System.out.println("validos/k = "+((double)validos/k));
        intervaloConfianzaP((double)validos/k, k);
        return limS*Math.PI/2*validos/k;
    }
    
    private static double funcion(double x){
        return Math.cos(x)*3;
    }
    
    private static double areaFuncion(){
        return 3*(Math.sin(limS)) - 3*(Math.sin(limI));
    }
    
    private static void intervaloConfianza(double [] valores){
        double [] intervalos = new double [2];
        double media = media(valores);
        double cuasiVarianza = cuasiV(valores, media);
        
        intervalos[0] = media - (1.96*(cuasiVarianza/Math.sqrt(valores.length)));
        intervalos[1] = media + (1.96*(cuasiVarianza/Math.sqrt(valores.length)));
        
        System.out.println("Intervalo de confianza: ("+intervalos[0]+", "+intervalos[1]+")");
    }
    
    private static void intervaloConfianzaP(double p, int n){
        double [] intervalos = new double [2];
        //System.out.println("p = "+p);
        intervalos[0] = p - (1.96 * Math.sqrt(p*(1-p)/(double)n));
        intervalos[1] = p + (1.96 * Math.sqrt(p*(1-p)/(double)n));
        System.out.println("Intervalo de confianza con Proporción: ("+intervalos[0]+", "+intervalos[1]+")");
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

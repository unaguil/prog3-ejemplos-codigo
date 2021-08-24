import java.util.Scanner;

public class Practica0 {
   
   public static void main(String[] args) {
	int[] numeros = new int[10];

	Scanner sc = new Scanner(System.in);
   	for (int i = 0; i < 10; i++) {
	   System.out.print(String.format("Número %d/10: ", i + 1));
	   numeros[i] = sc.nextInt(); 	   
	}
	sc.close();
        
	float media = 0;
	for (int i = 0; i < numeros.length; i++) {
	    media += numeros[i];
	}

	media /= numeros.length;
	
	System.out.println(String.format("Media de los números: %.2f", media));
   }
}
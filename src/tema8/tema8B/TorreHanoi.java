package tema8.tema8B;

// Este programa resuelve el problema de la Torre de Hanoi
// de manera recursiva utilizando recursividad múltiple.

public class TorreHanoi {

    private static void hanoiRec(int size, char source, char destiny, char auxiliary) {
		if (size == 1) {  // Caso base
			System.out.println("Mover disco 1 de " + source + " a " + destiny);
		} else {
			// Caso recursivo
			hanoiRec(size - 1, source, auxiliary, destiny);
			System.out.println("Mover disco " + size + " de " + source + " a " + destiny);
			hanoiRec(size - 1, auxiliary, destiny, source);
		}
	}
    
    public static void main(String[] args) {
        hanoiRec(3, 'A', 'C', 'B');
    }
}

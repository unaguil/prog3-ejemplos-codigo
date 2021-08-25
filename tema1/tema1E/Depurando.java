
public class Depurando {

	// Programa que debe imprimir la lista de números
	// entre "start" y "end", ambos incluidos.
	// Los valores de start y end son obtenidos como argumentos
	// del programa
	public static void main(String[] args) {
		int start = Integer.parseInt(args[0]);
		int end = Integer.parseInt(args[1]);
		
		System.out.println(String.format("Contando desde %d a %d", start, end));
		int[] numbers = countArray(start, end);
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}
	
	// Este método devuelve un array con los números entre "start" y "end",
	// ambos incluidos
	public static int[] countArray(int start, int end) {
		int[] numbers = new int[end - start + 1];
	
		int n = start;
		while (n <= end) {
			numbers[n - start] = n;
			n++;
		}
		
		return numbers;
	}
}

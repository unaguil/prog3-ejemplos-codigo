package practica0.estadisticas;

public class Media {

    public float calcularMedia(int[] numeros) {
	int media = 0;
	for (int i = 0; i < numeros.length; i++) {
	    media += numeros[i];
	}
        return media / (float) numeros.length;
    }
}
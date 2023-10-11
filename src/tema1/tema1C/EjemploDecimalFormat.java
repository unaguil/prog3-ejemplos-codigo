package tema1.tema1C;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


// Este ejemplo muestra las clases de utilidad para 
// realizar el formateo adecuado en distintos datos según
// la localización.

public class EjemploDecimalFormat {

    public static void main(String[] args) {
        double d = 1_234_567.89;  // Los _ se pueden usar para leer mejor los números largos
		
		DecimalFormat dfLocale = new DecimalFormat();
		NumberFormat nfUS = DecimalFormat.getNumberInstance(Locale.US);
		DecimalFormat dfManual = new DecimalFormat("0");
		DecimalFormat dfManualConDecimales = new DecimalFormat("0.000");
		
		System.out.println( "Formato local: " + dfLocale.format( d ));
		System.out.println( "Formato US: " + nfUS.format( d ));
		System.out.println( "Formato adhoc entero: " + dfManual.format( d ));
		System.out.println( "Formato adhoc real: " + dfManualConDecimales.format( d ));
		// Ver documentación de java.util.Formatter
		System.out.println( "Formato a través de " +
			String.format( "String.format(): #%1$5d# vs. #%2$,12.1f#", 123, d )
		);
    }
}
package es.deusto.prog3.cap05.map;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;

// Este ejemplo muestra cómo utilizar un Map
// que guarda relaciones clave-valor.

public class EjemploMap {
    
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("John", 20);
        map.put("Jenny", 24);

        System.out.println(map.get("John"));

        // se itera sobre las claves
        for (String s : map.keySet()) {
            System.out.println(s);
        }

        // se itera sobre los valores
        for (Integer i : map.values()) {
            System.out.println(i);
        }

        // se itera sobre la clave-valor directamente
        for (Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // determinar si una clave está en el mapa
        System.out.println(map.containsKey("John"));

        // determinar si un valor está en el mapa
        System.out.println(map.containsValue(20));

        // borrado del mapa
        map.clear();
    }
}

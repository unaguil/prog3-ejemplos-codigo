package tema4.tema4A.map;

// Esta clase representa un número de teléfono
// con un nombre asociado. Se ha determinado que
// dos números de teléfono son iguales si los son
// su area, prefijo y línea, independientemente del
// nombre asociado al mismo.

public class PhoneNumber {
    
    private String area;
    private String prefix;
    private String lineNum;

    private String name;

    public PhoneNumber(String area, String prefix, String lineNum, String name) {
        this.area = area;
        this.prefix = prefix;
        this.lineNum = lineNum;
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLineNum() {
        return lineNum;
    }

    public String getName() {
        return name;
    }

    // Este equals determina que dos instancias
    // de PhoneNumber son iguales si su área,
    // prefijo y línea son iguales, independientemente
    // de su nombre.
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhoneNumber)) {
            return false;
        }

        PhoneNumber phoneNumber = (PhoneNumber) o;
        return this.area.equals(phoneNumber.area) &&
            this.prefix.equals(phoneNumber.prefix) &&
            this.lineNum.equals(phoneNumber.lineNum);        
    }

    // Además, al implementar el equals se debe implementar
    // el método hashCode para indicar cómo se calcula para
    // esta clase. Solamente participan en el cálculo los
    // atributos utilizados para el método equals().
    @Override
    public int hashCode() {
        int result = area.hashCode();
        result += 31 * result + prefix.hashCode();
        result += 31 * result + lineNum.hashCode();
        return result;
    }
}

package es.deusto.prog3;

public class Student extends Person {

    private int numSubjects;

    public Student(String name, String surname, int numSubjects) {
        super(name, surname);
        this.numSubjects = numSubjects;
    }

    public int getNumSubjects() {
        return numSubjects;
    }    

    @Override
    public String toString() {
        return String.format(
            "%s, %s está matriculado en %d asignaturas", 
            surname, 
            name, 
            numSubjects
        );
    }
}

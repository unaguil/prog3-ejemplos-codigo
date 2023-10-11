package tema4.tema4A.set.tree;


public class Person implements Comparable<Person> {
    
    private int id;
    private String name;
    private String surname;

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullname() {
        return String.format("%s, %s", surname, name);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", id, name, surname);
    }

    @Override
    public int compareTo(Person p) {
        return this.id - p.id;
    }
}

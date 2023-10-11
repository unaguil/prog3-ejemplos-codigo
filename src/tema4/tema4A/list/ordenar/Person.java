package tema4.tema4A.list.ordenar;

public class Person implements Comparable<Person> {
    
    private String id;
    private String name;
    private String surname;

    public Person(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", id, name, surname);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person))
            return false;

        Person p = (Person) o;
        return p.id.equals(this.id);
    }

    @Override
    public int compareTo(Person p) {
        return this.getId().compareTo(p.getId());
    }

}

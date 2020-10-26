package es.deusto.prog3.cap05.contains;

public class Person {
    
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
    public boolean equals(Object o) {
        if (!(o instanceof Person))
            return false;

        Person p = (Person) o;
        return p.id.equals(this.id);
    }
}

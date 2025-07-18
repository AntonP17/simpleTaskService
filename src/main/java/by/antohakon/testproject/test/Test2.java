package by.antohakon.testproject.test;

import java.util.Objects;

public class Test2 {

    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test2 test2 = (Test2) o;
        return id == test2.id && Objects.equals(name, test2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

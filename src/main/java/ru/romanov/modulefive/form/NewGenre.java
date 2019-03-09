package ru.romanov.modulefive.form;

import java.util.Objects;

public class NewGenre {

    private String name;

    public NewGenre() {
    }

    public NewGenre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewGenre newGenre = (NewGenre) o;
        return Objects.equals(name, newGenre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

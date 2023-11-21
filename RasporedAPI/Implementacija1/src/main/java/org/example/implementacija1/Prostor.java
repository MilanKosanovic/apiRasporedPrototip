package org.example.implementacija1;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Prostor {

    private String ime;
    private int kapacitet;
    private List<String> oprema;

    public Prostor(String ime, int kapacitet) {
        this.ime = ime;
        this.kapacitet = kapacitet;
    }

    public Prostor(String ime, int kapacitet, List<String> oprema) {
        this.ime = ime;
        this.kapacitet = kapacitet;
        this.oprema = new ArrayList<>();
    }

    public Prostor() {
        this.oprema = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Prostor{" +
                "ime='" + ime + '\'' +
                ", kapacitet=" + kapacitet +
                ", oprema=" + oprema +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prostor prostor = (Prostor) o;
        return kapacitet == prostor.kapacitet && Objects.equals(ime, prostor.ime) && Objects.equals(oprema, prostor.oprema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, kapacitet, oprema);
    }
}

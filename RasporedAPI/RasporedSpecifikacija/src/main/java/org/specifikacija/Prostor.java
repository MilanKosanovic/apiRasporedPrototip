package org.specifikacija;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
        this.oprema = oprema;
    }

    public Prostor() {
    }

    @Override
    public String toString() {
        return "Prostor{" +
                "ime='" + ime + '\'' +
                ", kapacitet=" + kapacitet +
                ", oprema=" + oprema +
                '}';
    }
}

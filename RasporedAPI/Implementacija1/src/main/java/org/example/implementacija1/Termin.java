package org.example.implementacija1;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Termin {

    private String dan;
    private String datum;
    private String vreme;
    private String organizator;
    private String grupa;
    Prostor prostorije;

    public Termin() {
    }

    public Termin(String dan, String datum, String vreme, String organizator, Prostor prostorije, String grupa) {
        this.dan = dan;
        this.datum = datum;
        this.vreme = vreme;
        this.organizator = organizator;
        this.prostorije = prostorije;
        this.grupa = grupa;
    }

    @Override
    public String toString() {
        return "Termin{" +
                "dan='" + dan + '\'' +
                ", datum='" + datum + '\'' +
                ", vreme='" + vreme + '\'' +
                ", organizator='" + organizator + '\'' +
                ", grupa='" + grupa + '\'' +
                ", prostorije=" + prostorije +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Termin termin = (Termin) o;
        return Objects.equals(dan, termin.dan) && Objects.equals(datum, termin.datum) && Objects.equals(vreme, termin.vreme) && Objects.equals(organizator, termin.organizator) && Objects.equals(grupa, termin.grupa) && Objects.equals(prostorije, termin.prostorije);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dan, datum, vreme, organizator, grupa, prostorije);
    }
}

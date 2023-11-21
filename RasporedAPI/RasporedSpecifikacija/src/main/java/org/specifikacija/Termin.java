package org.specifikacija;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
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
}

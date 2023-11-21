package org.example.implementacija1;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class PraviTermin {

   private String dan;
   private LocalDate datum;
   private LocalDate krajnjiDatum;
   private String pocetak;
   private String kraj;
   private String grupa;
   private String organizator;
   private Prostor prostor;

    public PraviTermin() {
    }

//    public PraviTermin(String dan, LocalDate datum, String pocetak, String kraj, String grupa, String organizator, Prostor prostor) {
//        this.dan = dan;
//        this.datum = datum;
//        this.pocetak = pocetak;
//        this.kraj = kraj;
//        this.grupa = grupa;
//        this.organizator = organizator;
//        this.prostor = prostor;
//    }

    public PraviTermin(String dan, LocalDate datum, LocalDate krajnjiDatum, String pocetak, String kraj, String grupa, String organizator, Prostor prostor) {
        this.dan = dan;
        this.datum = datum;
        this.krajnjiDatum = krajnjiDatum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.grupa = grupa;
        this.organizator = organizator;
        this.prostor = prostor;
    }

    @Override
    public String toString() {
        return "PraviTermin{" +
                "dan='" + dan + '\'' +
                ", datum=" + datum +
                ", krajnjiDatum=" + krajnjiDatum +
                ", pocetak='" + pocetak + '\'' +
                ", kraj='" + kraj + '\'' +
                ", grupa='" + grupa + '\'' +
                ", organizator='" + organizator + '\'' +
                ", prostor=" + prostor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PraviTermin that = (PraviTermin) o;
        return Objects.equals(dan, that.dan) && Objects.equals(datum, that.datum) && Objects.equals(krajnjiDatum, that.krajnjiDatum) && Objects.equals(pocetak, that.pocetak) && Objects.equals(kraj, that.kraj) && Objects.equals(grupa, that.grupa) && Objects.equals(organizator, that.organizator) && Objects.equals(prostor, that.prostor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dan, datum, krajnjiDatum, pocetak, kraj, grupa, organizator, prostor);
    }
}

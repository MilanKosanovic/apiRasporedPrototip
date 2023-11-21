package org.specifikacija;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Raspored {

    private List<Termin> termini;

    public static void main(String[] args) {

    }
    public abstract boolean ucitajPodatke(String configPath, String path);


    public abstract boolean sacuvajPodakte(String configPath, String path);
    public abstract boolean napraviTermin(List<Termin> termini, Termin termin);
    public abstract boolean izmeniTermin(List<Termin> termini, Termin termin);
    public abstract boolean izbrsiTermin(List<Termin> termini, Termin termin);


}

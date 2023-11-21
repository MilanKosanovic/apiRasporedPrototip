package org.example.implementacija1;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.Setter;
import org.specifikacija.Raspored;


import javax.sound.midi.Soundbank;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Setter
public class Implementacija1{

    static List<PraviTermin> zauzetiTermini = new ArrayList<>();


    public static void main(String[] args) {

        //Implementacija1 implementacija1 = new Implementacija1();

        String configPath = "C:\\Users\\milan\\Desktop\\tim_SofijaSavic_MilanKosanovic\\Implementacija1\\config.txt";
        String inputPath = "C:\\Users\\milan\\Desktop\\tim_SofijaSavic_MilanKosanovic\\Implementacija1\\input.csv";

        try {

            Map<String, ConfigInfo> columnConfig = readConfig(configPath);
            List<Map<String, String>> data = readCsvFile(inputPath,columnConfig);

            // Obrada podataka
            for(Map<String,String> mapa : data){
                System.out.println("\n Ovo je jedna mapa u data: \n");
                for(String key : mapa.keySet()){
                    System.out.println("kljuc: "+key+ " vrednost: " + mapa.get(key));
                }
            }

            zauzetiTermini = popuniTermine(data);
            System.out.println("USESNO SAM POPUNIO TERMINE:\n");
            for(PraviTermin termin : zauzetiTermini){
                System.out.println(termin);
            }
                List <String> lista = new ArrayList<>();
            lista.add("cao");
            Termin termin = new Termin();
            Date datum = new Date();

            ///NAPRAVLJEN JE TERMIN CISTO DA SE UBACUJE U OVU POSTOJECU LISTU TERMINA AKO PRODJE PROVERU

            String datumString = "2001-10-12";

            // Definisanje formatera
            DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Parsiranje stringa u LocalDate
            LocalDate datum2 = LocalDate.parse(datumString, formater);

            String datumString1 = "2001-10-30";

            // Definisanje formatera
            DateTimeFormatter formater1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Parsiranje stringa u LocalDate
            LocalDate datum3 = LocalDate.parse(datumString1, formater1);

            LocalDate datum4 = LocalDate.parse("2020-10-12", formater1);



            //boolean c = dodajTermin(new Termin("ponedeljak", "09/12/2002","13:30"," String organizator",new Prostor("raf2",2,lista),"307a"));
           // boolean a = dodajTermin(new PraviTermin("Petak",datum2,datum3,"12:30","13:15","Druga grupa","Ana",new Prostor("K2",2,lista)));



            ///OVDE SE VRSI TA PROVERA TERMINA I DODAVANJE AKO MOZE DA SE DODA
            PraviTermin termin1 =new PraviTermin("Petak",datum2,datum3,"09:00","12:00","Druga grupa","Ana",new Prostor("K3",2,lista));
            PraviTermin terminBrisanje =new PraviTermin("Petak",datum4,datum3,"09:00","12:00","Druga grupa","Ana",new Prostor("K3",2,lista));
            if(dodajTermin(termin1)){
                System.out.println("Uspesno ste dodali termin");
                zauzetiTermini.add(termin1);
            }else{
                System.out.println("NIste bas");
            }
            ///OVDE GA ISPISE DA SE STVARNO TO VIDI
            for(PraviTermin ter : zauzetiTermini){
                System.out.println(ter);
            }
                ///provera da li radi ubacivanje sad kad smo napravili onu novu proveru zavrsnuProveru i radi sve!
            if(dodajTermin(terminBrisanje)){
                System.out.println("Uspesno ste dodali termin");
                zauzetiTermini.add(terminBrisanje);
            }else{
                System.out.println("NIste bas");
            }

            for(PraviTermin ter : zauzetiTermini){
                System.out.println(ter);
            }
            //SAD CEMO DA PROBAMO DA BRISEMO TERMIN KOJI POSTOJI U LISTI

           // obrisiTermin(terminBrisanje);

            ///OVDE SE VIDI DA JE USPESNO OBRISAO TERMIN

            ///izmena za vreme radi , izmena za prostoriju radi,

            izmeniTerminPoDatumu(termin1,LocalDate.parse("2020-05-12", formater1),LocalDate.parse("2020-05-30", formater1));

            for(PraviTermin ter : zauzetiTermini){
                System.out.println(ter);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("PRETRAZUJEMO PO PROSTORIJI");

        for(PraviTermin t : pretragaPoProstoriji("K4",zauzetiTermini)){
            System.out.println(t);
        }
        System.out.println("PRETRAZUJEMO PO ORGANIZATORU");

        for(PraviTermin t : pretragaPoOrganizatoru("Milica",zauzetiTermini)){
            System.out.println(t);
        }
        System.out.println("PRETRAZUJEMO PO VREMENU POCETKA");

        for(PraviTermin t : pretragaPoVremenuPocetka("12:00",zauzetiTermini)){
            System.out.println(t);
        }
        System.out.println("PRETRAZUJEMO PO VREMENU ZAVRSETKA");

        for(PraviTermin t : pretragaPoVremenuZavrsetka("13:30",zauzetiTermini)){
            System.out.println(t);
        }
        System.out.println("PRETRAZUJEMO PO DATUMU");
        DateTimeFormatter formaterrr = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dat = LocalDate.parse("2020-10-12", formaterrr);

        for(PraviTermin t : pretragaPoDatumu(dat,zauzetiTermini)){
            System.out.println(t);
        }
        System.out.println("PRETRAZUJEMO PO DANU U NEDELJI");

        for(PraviTermin t : pretragaPoDanu("Utorak",zauzetiTermini)){
            System.out.println(t);
        }

    }



    public static Map<String, ConfigInfo> readConfig(String path) throws IOException {

        Map<String, ConfigInfo> columnConfig = new HashMap<>();
        List<ConfigInfo> lista = new ArrayList<>();
        List<String> configLines = Files.readAllLines(Paths.get(path));

        for(String line : configLines){

            String[] deo = line.split("=");

            String imeKolone = deo[0];
            String[] info = deo[1].split("\\|");
            String tip = info[0];
            String cond = info[1];
            ConfigInfo configInfo = new ConfigInfo(cond,tip);
            lista.add(configInfo);
            columnConfig.put(imeKolone,configInfo);


        }


        return columnConfig;
    }
    public static List<Map<String, String>> readCsvFile(String csvFilePath, Map<String, ConfigInfo> columnConfig) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();


        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] header = reader.readNext();


            String[] line;
            while ((line = reader.readNext()) != null) {
                Map<String, String> rowData = new HashMap<>();

                int i=0;
                while(i != line.length) {

                    String columnName;
                    if(i>=header.length) {
                         columnName = header[header.length-1];
                    }else {
                         columnName = header[i];
                    }
                    String value = line[i];

                    ConfigInfo configInfo = columnConfig.get(columnName);

//                    // Provera da li je kolona obavezna ili opcionalna
                    if (configInfo.getCondition().equalsIgnoreCase("required")) {
                        rowData.put(columnName,parseRequiredValue(value,configInfo));
                    } else if (configInfo.getCondition().equalsIgnoreCase("optional")) {
                        rowData.put(columnName,parseOptionalValue(value,configInfo));
                    }



                    i++;
                }
//

                data.add(rowData);


            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return data;
        }


    private static String parseRequiredValue(String value, ConfigInfo configInfo) throws Exception {
        // Logika parsiranja za obavezne vrednosti

        if(value.equals("")){
            System.out.println("Required deo ne moze biti prazan !!!");
            throw new  Exception("NE MOZE BRE");
        }
        // za sad ova dva nista!!!
        return value;
    }

    private static String parseOptionalValue(String value, ConfigInfo configInfo) {
        // Logika parsiranja za opcionalne vrednosti

        return value;
    }


    public static List<PraviTermin> popuniTermine(List<Map<String,String>> data){

        List<PraviTermin> kreiraniTermini = new ArrayList<>();

        for(Map<String,String> mapa : data){

            PraviTermin terminTmp = new PraviTermin();
            Prostor prostor = new Prostor();

            for(String key : mapa.keySet()){

                switch (key){
                    case "dan":
                        terminTmp.setDan(mapa.get(key));
                        break;
                    case "vreme":

                        String[] vreme = mapa.get(key).split("-");
                        terminTmp.setPocetak(vreme[0].trim());
                        if(vreme[1].contains(":")){
                            terminTmp.setKraj(vreme[1].trim());
                        }else {
                            LocalTime vremePocetka = LocalTime.parse(vreme[0].trim());

                            LocalTime vremeKraja = vremePocetka.plusMinutes(Integer.parseInt(vreme[1].trim()));

                            terminTmp.setKraj(vremeKraja.toString());
                        }

                        break;
                    case "datum":

                        String datum = mapa.get(key);

                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        if(!datum.contains(" ")){
                            terminTmp.setDatum(LocalDate.parse(datum,format));
                            terminTmp.setKrajnjiDatum(LocalDate.parse(datum,format));

                        }else{
                            String[] pocKr = datum.split(" ");
                            terminTmp.setDatum(LocalDate.parse(pocKr[0],format));
                            terminTmp.setKrajnjiDatum(LocalDate.parse(pocKr[1],format));

                        }
                        break;
                    case "organizator":
                        terminTmp.setOrganizator(mapa.get(key));
                        break;
                    case "grupe":
                        terminTmp.setGrupa(mapa.get(key));
                        break;
                    case "prostorija":
                        prostor.setIme(mapa.get(key));
                        break;
                    case "kapacitet":
                        prostor.setKapacitet(Integer.parseInt(mapa.get(key)));
                        break;
                    case "oprema":
                        prostor.getOprema().add(mapa.get(key));
                        break;
                    default:
                        break;
                }

            }
                terminTmp.setProstor(prostor);

                kreiraniTermini.add(terminTmp);
        }

        return kreiraniTermini;

    }

    public static boolean dodajTermin(PraviTermin termin){

        for (PraviTermin termin1 : zauzetiTermini){
            if(!zavrsnaProvera(termin1,termin)){
                return false;
            }
        }
        return  true;
    }
    public static void izmeniTerminPoVremenu(PraviTermin termin,String pocetak,String kraj){

        if(!zauzetiTermini.contains(termin)){
            System.out.println("Izabrali ste ne postojeci termin");
        }

        PraviTermin novi = new PraviTermin(termin.getDan(),termin.getDatum(),termin.getKrajnjiDatum(),pocetak,kraj,termin.getGrupa(),termin.getOrganizator(),termin.getProstor());

        novi.setPocetak(pocetak);
        novi.setKraj(kraj);

        ///korsiti istu metodu da proveri dal moze taj termin da se
        // izmeni na taj nacin u odnosu na ostale termine
        // pre toga brisemo ovaj postojeci koji hocemo da izmenimo
        // da se ne zbuni i onda ako ne moze da ubaci novi vrati nazad onaj stari

        zauzetiTermini.remove(termin);

        if(dodajTermin(novi)){
            zauzetiTermini.add(novi);
            System.out.println("Uspesno ste izmenili termin");
        }else{
            System.out.println("NE moze za ovo vreme da se izmeni termin jer je zauzeto vreme!" + termin.getKraj());

            zauzetiTermini.add(termin);
        }



    }

    public static void izmeniTerminPoDatumu(PraviTermin termin,LocalDate datum,LocalDate kraj){

        PraviTermin novi = new PraviTermin(datum.getDayOfWeek().toString(),datum,kraj,termin.getPocetak(),termin.getKraj(),termin.getGrupa(),termin.getOrganizator(),termin.getProstor());

        if(novi.getDatum().isAfter(novi.getKrajnjiDatum())){
            System.out.println("NE VALJA VAM DATUM");
        }

        if(!zauzetiTermini.contains(termin)){
            System.out.println("Izabrali ste ne postojeci termin");
        }

        zauzetiTermini.remove(termin);

        if(dodajTermin(novi)){
            zauzetiTermini.add(novi);
            System.out.println("Uspesno ste izmenili termin");
        }else{
            System.out.println("NE moze za ovo  jer je zauzeto za taj Datum!" + termin.getKraj());

            zauzetiTermini.add(termin);
        }


    }

    public static void izmeniTerminPoProstoriji(PraviTermin termin,Prostor prostor){

        PraviTermin novi = new PraviTermin(termin.getDan(),termin.getDatum(),termin.getKrajnjiDatum(),termin.getPocetak(),termin.getKraj(),termin.getGrupa(),termin.getOrganizator(),prostor);


        if(!zauzetiTermini.contains(termin)){
            System.out.println("Izabrali ste ne postojeci termin");
        }

        zauzetiTermini.remove(termin);

        if(dodajTermin(novi)){
            zauzetiTermini.add(novi);
            System.out.println("Uspesno ste izmenili termin");
        }else{
            System.out.println("NE moze da se izmeni termin jer je Prostor zauzet u to vreme!" + termin.getKraj());

            zauzetiTermini.add(termin);
        }

    }
    ///Koristi se zavrsnaProvera metoda jer ona gleda ako su i prostojija i vreme isto
    // da proveri da li je mozda razlicit dan ili datum
    public static boolean zavrsnaProvera(PraviTermin prvi,PraviTermin drugi){

        if(proveriTermine(prvi,drugi)){
            return true;
        }

        if(drugi.getDan().equals(prvi.getDan())){

            if(drugi.getDatum().equals(prvi.getDatum())){
                return false;
            }
        }

        return true;
    }

    public static boolean proveriTermine(PraviTermin prvi,PraviTermin drugi){

            LocalTime poc1 = LocalTime.parse( prvi.getPocetak());
            LocalTime kraj1 = LocalTime.parse(prvi.getKraj());

            LocalTime poc2 = LocalTime.parse(drugi.getPocetak());
            LocalTime kraj2 = LocalTime.parse(drugi.getKraj());



            if(prvi.getProstor().getIme().equals(drugi.getProstor().getIme())){
                if(poc2.isAfter(kraj1) && kraj2.isAfter(poc2)){
                        return true;
                }else if(kraj2.isBefore(kraj1) && kraj2.isAfter(poc1)){
                    return false;
                }
                else if(poc2.isBefore(poc1) && kraj2.isBefore(poc1) && kraj2.isAfter(poc2)){
                    return true;
                }else if(poc2.isBefore(kraj2)){
                    return true;
                }
            }else{
                return true;
            }

        return false;
    }

    public static void obrisiTermin(PraviTermin termin){
        PraviTermin obrisani = null;
        for (PraviTermin termin1 : zauzetiTermini){
            if(termin1.equals(termin)){
                System.out.println("USOA");
                obrisani = termin1;
            }
        }

        zauzetiTermini.remove(obrisani);


    }

    public static List<PraviTermin> pretragaPoProstoriji(String imeProstorije,List<PraviTermin> zauzetiTermini){
        List<PraviTermin> rezultat = new ArrayList<>();

        for(PraviTermin termin : zauzetiTermini){
            if(termin.getProstor().getIme().equals(imeProstorije)){
                rezultat.add(termin);
            }
        }
        return rezultat;
    }

    public static List<PraviTermin> pretragaPoOrganizatoru(String organizator,List<PraviTermin> zauzetiTermini){
        List<PraviTermin> rezultat = new ArrayList<>();

        for(PraviTermin termin : zauzetiTermini){
            if(termin.getOrganizator().equals(organizator)){
                rezultat.add(termin);
            }
        }

        if(rezultat.isEmpty()){
            System.out.println("NEMA TERMINA ZA ZELJENU PRETRAGU");
        }
        return rezultat;
    }
    public static List<PraviTermin> pretragaPoVremenuPocetka(String pocetak,List<PraviTermin> zauzetiTermini){
        List<PraviTermin> rezultat = new ArrayList<>();

        for(PraviTermin termin : zauzetiTermini){
            if(termin.getPocetak().equals(pocetak)){
                rezultat.add(termin);
            }
        }
        if(rezultat.isEmpty()){
            System.out.println("NEMA TERMINA ZA ZELJENU PRETRAGU");
        }
        return rezultat;
    }
    public static List<PraviTermin> pretragaPoVremenuZavrsetka(String kraj,List<PraviTermin> zauzetiTermini){
        List<PraviTermin> rezultat = new ArrayList<>();

        for(PraviTermin termin : zauzetiTermini){
            if(termin.getKraj().equals(kraj)){
                rezultat.add(termin);
            }
        }
        if(rezultat.isEmpty()){
            System.out.println("NEMA TERMINA ZA ZELJENU PRETRAGU");
        }
        return rezultat;
    }
    public static List<PraviTermin> pretragaPoDatumu(LocalDate datum,List<PraviTermin> zauzetiTermini){
        List<PraviTermin> rezultat = new ArrayList<>();

        for(PraviTermin termin : zauzetiTermini){
            if(termin.getDatum().equals(datum)){
                rezultat.add(termin);
            }
        }
        if(rezultat.isEmpty()){
            System.out.println("NEMA TERMINA ZA ZELJENU PRETRAGU");
        }
        return rezultat;
    }

    public static List<PraviTermin> pretragaPoDanu(String dan,List<PraviTermin> zauzetiTermini){
        List<PraviTermin> rezultat = new ArrayList<>();

        for(PraviTermin termin : zauzetiTermini){
            if(termin.getDan().equals(dan)){
                rezultat.add(termin);
            }
        }
        if(rezultat.isEmpty()){
            System.out.println("NEMA TERMINA ZA ZELJENU PRETRAGU");
        }
        return rezultat;
    }





}

package refactoring;


import java.text.SimpleDateFormat;
import java.util.Vector;

import sun.util.calendar.*;

public class Client {
    private String nif;
    private String nom;
    private String telefon;
    private Vector <Lloguer> lloguers;
    private String prueba;

    public Client(String nif, String nom, String telefon) {
        this.nif = nif;
        this.nom = nom;
        this.telefon = telefon;
        this.lloguers = new Vector<Lloguer>();
    }
    public static void main(String [] args) {
        Client c = new Client("3454545","Eduard","45454545");
        
        Vehicle v1 = new Vehicle("Tata", "Vista", Vehicle.BASIC);
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        Date date = dateFormat.parse("2/8/2013");
        Lloguer ll1 = new Lloguer(date, 2, v1);
        c.afegeix(ll1);     

        Vehicle v2 = new Vehicle("Hyundai", "Atos", Vehicle.LUXE);
        date = dateFormat.parse("2/10/2017");
        Lloguer ll2 = new Lloguer(date, 1, v2);
        c.afegeix(ll2);
        
        Vehicle v3 = new Vehicle("Seat", "Cupra", Vehicle.GENERAL);
        date = dateFormat.parse("2/8/2020");
        Lloguer ll3 = new Lloguer(date, 5, v3);
        c.afegeix(ll3);
        
        System.out.print(historialClient(c));
    }

    public String getNif()     { return nif;     }
    public String getNom()     { return nom;     }
    public String getTelefon() { return telefon; }

    public void setNif(String nif) { this.nif = nif; }
    public void setNom(String nom) { this.nom = nom; }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public void afegeix(Lloguer lloguer) {
        if (! lloguers.contains(lloguer) ) {
            lloguers.add(lloguer);
        }
    }
    public void elimina(Lloguer lloguer) {
        if (lloguers.contains(lloguer) ) {
            lloguers.remove(lloguer);
        }
    }
    public boolean conte(Lloguer lloguer) {
        return lloguers.contains(lloguer);
    }

    public String informe() {
        double total = 0;
        int bonificacions = 0;
        String resultat = "Informe de lloguers del client " +
            getNom() +
            " (" + getNif() + ")\n";
        for (Lloguer lloguer: lloguers) {
            double quantitat = 0;
            switch (lloguer.getVehicle().getCategoria()) {
                case Vehicle.BASIC:
                    quantitat += 3;
                    if (lloguer.getDies() > 3) {
                        quantitat += (lloguer.getDies() - 3) * 1.5;
                    }
                    break;
                case Vehicle.GENERAL:
                    quantitat += 4;
                    if (lloguer.getDies() > 2) {
                        quantitat += (lloguer.getDies() - 2) * 2.5;
                    }
                    break;
                case Vehicle.LUXE:
                    quantitat += lloguer.getDies() * 6;
                    break;
            }

            // afegeix lloguers freqüents
            bonificacions ++;

            // afegeix bonificació per dos dies de lloguer de Luxe
            if (lloguer.getVehicle().getCategoria() == Vehicle.LUXE &&
                    lloguer.getDies()>1 ) {
                bonificacions ++;
            }

            // composa els resultats d'aquest lloguer
            resultat += "\t" +
                lloguer.getVehicle().getMarca() +
                " " +
                lloguer.getVehicle().getModel() + ": " +
                (quantitat * 30) + "€" + "\n";
            total += quantitat * 30;
        }

        // afegeix informació final
        resultat += "Import a pagar: " + total + "€\n" +
            "Punts guanyats: " + bonificacions + "\n";
        return resultat;
    }
}

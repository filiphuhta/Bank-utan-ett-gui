/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filiphuhta
 */
public class Bankkund {

    /*  kund object */
    static class kund {

        String personnummer;
        String fornamn;
        String efternamn;
        String Lösenord;
    }

    /* Konto object */
    static class konto {

        String personnummer;
        String kontonummer;
        double saldo = 0.0;
        double ranta = 0.0;
    }

    static List<kund> kunder = new ArrayList<kund>();
    static List<konto> konton = new ArrayList<konto>();

    /**
     * @param args the command line arguments
     */
    public static void laggTillKund(String persnummer, String fornamn, String efternamn) {
        kund en_kund = new kund();
        en_kund.fornamn = fornamn;
        en_kund.efternamn = efternamn;
        en_kund.personnummer = persnummer;
        kunder.add(en_kund);

    }

    public static void laggTillKonto(String pnummer, String konto_nummer, double saldo) {
        konto ett_konto = new konto();
        ett_konto.personnummer = pnummer;
        ett_konto.kontonummer = konto_nummer;
        ett_konto.saldo = saldo;
        konton.add(ett_konto);
    }

    public static kund sok_kund(String pnummer) {
        for (kund tmp_kund : kunder) {
            if (tmp_kund.personnummer.equals(pnummer)) {
                return tmp_kund;
            }
        }
        return null;
    }

    public static void listaKontoTillKund(String pnummer) {
        for (konto tmp_konto : konton) {
            if (tmp_konto.personnummer.equals(pnummer)) {
                System.out.println("Kontonummer:" + tmp_konto.kontonummer + " saldo:" + tmp_konto.saldo);
            }

        }
    }

    public static void addMoney(String kontonummer, double pengar) {
        for (konto tmp_konto : konton) {
            if (tmp_konto.kontonummer.equals(kontonummer)) {
                tmp_konto.saldo += pengar;
            }

        }
    }

    public static void skriv_ut_kund(kund kunden) {
        System.out.println("Personnummer:" + kunden.personnummer);
        System.out.println("Namn:" + kunden.fornamn + " " + kunden.efternamn);
        System.out.println("------------- konton ----------------");
        listaKontoTillKund(kunden.personnummer);
        System.out.println("-----------------------------");
    }

    public static void sok_och_skriv_ut_kund(String pnummer) {
        kund en_liten_kund = sok_kund(pnummer);
        if (en_liten_kund != null) {
            skriv_ut_kund(en_liten_kund);
        }
    }

   

}

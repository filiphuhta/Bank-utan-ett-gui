/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;




import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Iterator;




public class Bank {
    
    public static void Meny2() {
     System.out.println("\nAnge siffra till nästa ärende."+
                 "\n 1. Meny \n 2. Skapa kund " +
                "\n 3. Skapa konto \n 4. Lägg till pengar\n 5. Ta ut pengar "
             + "\n 6. Överför pengar till ett annat konto \n 7. Visa kund + tillgångar \n 8. Tabort kund + tillgångar "
             + "\n 9. Tabort konto \n 10. Visa alla kunder  \n 11. Avsluta ");
}
    
    
    
    static class kund { // all kund data

        String personnummer;
        String fornamn;
        String efternamn;
        
    }

    /* Konto object */
    static class konto { // all konto data

        String personnummer;
        String kontonummer;
        double saldo = 0.0;
        
    }
   
    static ArrayList<kund> kunder = new ArrayList<kund>(); // Listor som lagrar konto och kundata.
static ArrayList<konto> konton= new ArrayList<konto>();
    
    public static void CreateAccount() { // Metod som skapar ett konto med hjälp av att man ger den ett existerande personnummer.
       
        boolean konto1 = false;
        do {
        System.out.println("Skriv in personnummret på den kontot ska skapas till");
        Scanner tmp_prn = new Scanner(System.in);
        String personnummer  = tmp_prn.next();
       
        
        if ( sok_kund(personnummer) ) {
            /* fråga efter data */
            konto tmp_konto = new konto();
            tmp_konto.personnummer = personnummer;
            int antal_konton = kontoCounter(personnummer);
            tmp_konto.kontonummer = personnummer + "-" +antal_konton;
            tmp_konto.saldo = 0.0;
            konton.add(tmp_konto);
            System.out.println("Ett konto är skapat till personen med personnummret: "+ personnummer + "\n Med Kontonummret: " + personnummer + "-" + antal_konton +"\n Med saldot:"+ tmp_konto.saldo );
        konto1 = true;
        Meny2();
        }
        
        else {
            System.out.print("\nFelaktigt personnummer Försök igen. \n\n");
        }
        } while(!konto1);
    }
    
    public static int kontoCounter(String pnummer) { // Tar fram sista siffran i kontonummret som gör det unikt.
        int antal = 0;
        /*  2 till sätt att göra iterationen på nedaför på.
        
        Iterator <konto>it = konton.iterator();
        while(it.hasNext()){
            konto tmp_konton = it.next();
            if (tmp_konton.personnummer.equals(pnummer)) {
              antal++;
            }
        }        
        for(int i=0;i< konton.size();i++){
            konto tmp_konton = konton.get(i);
            if (tmp_konton.personnummer.equals(pnummer)) {
              antal++;
            }
        }
        
        */ 
        
        for (konto tmp_konton : konton)  {
            System.out.println(tmp_konton.personnummer);
            if (tmp_konton.personnummer.equals(pnummer)) {
              antal++;
            }
        
       
        }
         return antal;
    }
    
     public static void listaKontoTillKund(String pnummer) {  // Skriver ut konton med saldo för respektive kund.
         for (konto tmp_konto : konton) {
            if (tmp_konto.personnummer.equals(pnummer)) {
                System.out.println("Kontonummer:" + tmp_konto.kontonummer + " saldo:" + tmp_konto.saldo+ "Kr");
            }

        }
    }
     
     public static void listaKunder (){ // Skriver ut alla existerande kunder med tillhörande konton.
         System.out.println("******Alla kunder på banken****");
       if(kunder.size() > 0) {
         for  (kund tmp_kunder : kunder) {
            System.out.println(tmp_kunder.fornamn +" "+ tmp_kunder.efternamn +" "+ tmp_kunder.personnummer);
         listaKontoTillKund(tmp_kunder.personnummer);
         
        }
       } else {
           System.out.println("Hittade inga kunder!");
       }
        Meny2();
     }
    
     public static void addMoney() { // Lägger till pengar på ett konto.
        boolean foundkonto = false;
         Scanner addkonto = new Scanner(System.in);
         System.out.println("Ange vilket konto pengarna ska sättas in på");
         String tmp_kontonummer = addkonto.nextLine();
         System.out.println("Ange hur mycket pengar som ska sättas in");
         String tmp_money = addkonto.nextLine();
         double pengar = Double.parseDouble(tmp_money);
        for (konto tmp_konto : konton) {
            if (tmp_konto.kontonummer.equals(tmp_kontonummer)) {
                tmp_konto.saldo += pengar;
                System.out.println("Insättningen genomförd!\n");
                listaKontoTillKund(tmp_konto.personnummer);
                foundkonto = true;
                
            }

        }
       if (foundkonto == false){
           System.out.println("Hittade inget konto till det nummret\n");
       }
        Meny2();
    }
    
     public static void deleateMoney() { // tar bort pengar från ett konto. Exempel: när man tar ut pengar.
        boolean foundkonto = false;
         Scanner addkonto = new Scanner(System.in);
         System.out.println("Ange vilket konto pengarna ska tas ut ifrån");
         String tmp_kontonummer = addkonto.nextLine();
         System.out.println("Ange hur mycket pengar som ska tas ut");
         String tmp_money = addkonto.nextLine();
         double pengar = Double.parseDouble(tmp_money);
        for (konto tmp_konto : konton) {
            if (tmp_konto.kontonummer.equals(tmp_kontonummer)) {
        if (tmp_konto.saldo < pengar) {
            System.out.println("För lite pengar på kontot, Vänligen försök igen.");
            
        } else {
             tmp_konto.saldo -= pengar;
                System.out.println("Uttag genomfört!\n");
                listaKontoTillKund(tmp_konto.personnummer);
        }
               
                foundkonto = true;
                
            }

        }
       if (foundkonto == false){
           System.out.println("Hittade inget konto till det nummret\n");
       }
        Meny2();
    }
     
     public static void overforPengar () { // Överför pengar från ett konto till ett annat oberonde av ifall det är samma kund som äger kontona.
    boolean foundpengar = false;
         Scanner overforing = new Scanner(System.in);
         System.out.println("Skriv in vilket konto du ska föra över pengar ifrån");
         String kontopengar = overforing.nextLine();
         System.out.println("Skriv in hur mycket pengar som ska överföras");
         String tmp_money = overforing.nextLine();
         System.out.println("Skriv in vilket konto pengarna ska föras över till");
         String kontopengar2 = overforing.nextLine();
          double pengar = Double.parseDouble(tmp_money);
        for (konto tmp_konto : konton) {
            if (tmp_konto.kontonummer.equals(kontopengar)) {
        if (tmp_konto.saldo < pengar) {
            System.out.println("För lite pengar på kontot, Vänligen försök igen.");
            
        } else {
             tmp_konto.saldo -= pengar;
               for(konto tmp_konto2 : konton) { if (tmp_konto2.kontonummer.equals(kontopengar2)) {
            tmp_konto2.saldo += pengar;
            
                   System.out.println("Överföring genomfört!\n");
                listaKontoTillKund(tmp_konto.personnummer);
                listaKontoTillKund(tmp_konto2.personnummer);
               }
               }
        }
               
                foundpengar = true;
                
            }

        }
       if (foundpengar == false){
           System.out.println("Hittade inget konto till det nummret\n");
           
    }
       Meny2();
}
    
     
     public static boolean sok_kund (String pnummer) { // Metod som söker igenom hela listan med kunder.
         boolean foundkund = false;
          for (kund tmp_kund : kunder) {
            if (tmp_kund.personnummer.equals(pnummer)) {
             foundkund = true;
            }
            }
          return foundkund;
     }
    
    
     public static void sok_kund_skrivut() { //Söker och skriver ut kunders uppgifter.
       Scanner sökkund = new Scanner(System.in);
       System.out.println("Ange personnummret på kunden");
       String tmp_sökkund = sökkund.nextLine();
         boolean kund_ejhittad = false;
         for (kund tmp_kund : kunder) {
            if (tmp_kund.personnummer.equals(tmp_sökkund)) {
                System.out.println("**Kund hittad med dessa uppgifter!**\n");
                skriv_ut_kund(tmp_kund);
                 kund_ejhittad = true;
            }
        }
         if(kund_ejhittad == false){
             System.out.println("Hittar ej anged kund\n");
         }
         
         Meny2();
         
        
        
    }
    
     public static void skriv_ut_kund(kund kunden) { // Skriver ut kundens uppgifter.
        System.out.println("Personnummer:" + kunden.personnummer);
        System.out.println("Namn:" + kunden.fornamn + " " + kunden.efternamn);
        System.out.println("------------- konton ----------------");
       listaKontoTillKund(kunden.personnummer);
        System.out.println("-----------------------------");
    }
    
   
  
    public static void tabortKund () { // Tar bort kunden.
       
        Scanner tmp_tabortKund = new Scanner(System.in);
        System.out.println("Skriv in personnummret på den kund du vill tabort");
        String persnmr = tmp_tabortKund.nextLine();
        for(int i=0;i< kunder.size();i++){
            kund tmp_kund = kunder.get(i);
            if (tmp_kund.personnummer.equals(persnmr)) {
              
                 kunder.remove(i);
                
            }
            }
        Meny2();
    }
              
    public static void tabortKonto () { // Tar bort konto.
         Scanner tmp_tabortKund = new Scanner(System.in);
        System.out.println("Skriv in kontonummret på den kontot du vill tabort");
        String kontonmr = tmp_tabortKund.nextLine();
        for(int i=0;i< konton.size();i++){
            konto tmp_konto = konton.get(i);
            if (tmp_konto.kontonummer.equals(kontonmr)) {
              System.out.println("**Kontot bortaget!**");
                 konton.remove(i);
                
            }
            }
        Meny2();
    }
            
    
    
    public static void laggTillKund (String fornamn, String efternamn,String personnummer) { // Lägger in värden i listan kunder.
        kund nyKund = new kund();
        nyKund.fornamn = fornamn;
        nyKund.efternamn = efternamn;
        nyKund.personnummer = personnummer;
        kunder.add(nyKund);
    
}



    public static void CreateCustomer() { //Skapar en kund och verifierar att personnummret är riktigt.
            boolean yn = false;

            do {
try {
                String answer;
                

                String fornamn;
                System.out.println("Skriv in ditt Förnamn");
                Scanner first = new Scanner(in);
                fornamn = first.next();

                String efternamn;
                System.out.println("Skriv in ditt Efternamn");
                Scanner last = new Scanner(in);
                efternamn = last.next();

              
                System.out.println("Skriv in ditt personnummer");
                Scanner person = new Scanner(in);
               String personnummer = person.nextLine();
               
               
int x = personnummer.length() - 10; // Kollar så att personnummret är äkta.
  if (x < 0) {
    System.out.println("Number length must be at least 10 characters!");
  }
 personnummer = personnummer.substring(x, 10 + x);
  int sum = 0;
  for (int i = 0; i < personnummer.length(); i++){
    char tmp = personnummer.charAt(i);
    int num = tmp - '0';
    int product;
    if (i % 2 != 0){
      product = num * 1;
    }
    else{
      product = num * 2;
    }
    if (product > 9)
      product -= 9;
    sum+= product;              
  }
  boolean valid = (sum % 10 == 0);
  if (valid){
    
     System.out.print("Informationen du skrivit in: \n Förnamn: " + fornamn + "\n Efternamn: " + efternamn
                        + "\n Personnummer: " + personnummer);

                System.out.println("\n Stämmer uppgifterna?" + "y/n");
                Scanner yesno = new Scanner(System.in);
                answer = yesno.next();
                
                 if (answer.equals("y")) {
                    yn = true;
                    laggTillKund(fornamn, efternamn, personnummer);
                    System.out.println("Kunden "+ fornamn + " " + efternamn +"\nHar Skapats med kundnummret: " + personnummer);   
                  Meny2();
                   }
                
                   
                 
  }
  
  else{
    System.out.println("Invalid number please try again!");
    
    
    
  }
 
           
              }
              catch (StringIndexOutOfBoundsException e) {
                       
                       }
            }while (!yn);

         
        }

    }


	





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.Scanner;

import static java.lang.System.in;
import java.util.InputMismatchException;


public class Banksystem {

    public static void main(String[] arg) {

        ShowMenu(); // Anropar menyn.
    }

    public static void Meny() { 
     System.out.println("** Skriv in de numret du vill använda dig av **" +
                "\n 1. Meny \n 2. Skapa kund " +
                "\n 3. Skapa konto \n 4. Lägg till pengar\n 5. Ta ut pengar "
             + "\n 6. Överför pengar till ett annat kontor \n 7. Visa kund + tillgångar \n 8. Tabort kund + tillgångar "
             + "\n 9. Tabort konto \n 10. Visa alla kunder \n 11. Avsluta ");
}
    
    public static void ShowMenu() { // Metod för menyn.
        boolean a = false;
        do {
try {
        Scanner scan = new Scanner(in);
       Meny();
        while (true) {
            int selectMenu = scan.nextInt();

            switch (selectMenu) {
                case 1: {
                    ShowMenu(); 
                    break;
                }

                case 2: {
                    Bank BankObject = new Bank();
                    BankObject.CreateCustomer();
                    break;

                }

                case 3: {
Bank BankAccount = new Bank();
BankAccount.CreateAccount();
                    
                    break;
                }

                case 4: {
Bank BankAddM = new Bank();
BankAddM.addMoney();
                    
                    break;

                }
                
                case 5: {
                   
                    Bank tautpengar = new Bank();
                     tautpengar.deleateMoney(); // Anropar metoden från klassen Bank.
                    
                    break;
                }
                
                case 6: {

              
                    Bank överförpengar = new Bank();
                    överförpengar.overforPengar();
                     break;
                }
                
                case 7: {
                    
                    
                    Bank sökkund = new Bank();
                    sökkund.sok_kund_skrivut();
                    
                    break;
                }
                
                case 8: {
                  
                    
                      Bank tabortKunden = new Bank();
                    tabortKunden.tabortKund();
                    
                    break;
                }
                case 9: {

                   Bank tabortKonton = new Bank();
                    tabortKonton.tabortKonto();
                    break;
                }

                case 10: {
                   Bank listakund = new Bank();
                    listakund.listaKunder();
                    
                    break;
                }
                case 11: {

                    System.exit(0); //Avsluta systemet.
                    break;
                }
                default: {

                    System.out.println("Ogiltigt menyval");
                    ShowMenu();
                    break;
                }
            }
        }

    } catch (InputMismatchException e) { // Catch som fångar upp icke acceptabelt värde.
        System.out.print("***Du angav ingen siffra försök igen!!***\n");
    
}
        } while(!a);
}
}


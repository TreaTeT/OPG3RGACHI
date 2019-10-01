package aaa;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Spust {

    public static void main(String[] args) {



        System.out.println("Zadaj");
        Scanner sc = new Scanner(System.in);
        switch (sc.nextInt()) {

            case (1) {
                System.out.println("ID:");
                int ID = sc.nextInt();
                System.out.println("Meno");
                String meno = sc.nextLine();
                System.out.println("Priezvisko");
                String priezvisko = sc.nextLine();
                System.out.println("Adresa");
                String adresa = sc.nextLine();
                System.out.println("Cislo obcianskeho preukazu");
                String COP = sc.nextLine();
                Klient a = new Klient(ID, meno, priezvisko, adresa, COP);
            }
            case ()
        }

    }

}

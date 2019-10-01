package aaa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Spust {

    public static void main(String[] args) throws IOException {



        System.out.println("Zadaj");
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("ucty.txt"));
        Klient a = null;
        while(true) {
            System.out.println("1 - vytvor klienta");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("ID:");
                    int ID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Meno");
                    String meno = sc.nextLine();
                    System.out.println("Priezvisko");
                    String priezvisko = sc.nextLine();
                    System.out.println("Adresa");
                    String adresa = sc.nextLine();
                    System.out.println("Cislo obcianskeho preukazu");
                    String COP = sc.nextLine();
                    a = new Klient(ID, meno, priezvisko, adresa, COP);

                default:
                    break;
            }

            out.write(Integer.toString(a.getID()) + " " + a.getMeno() + " " + a.getPriezvisko() + " " + a.getAdresa() + " " + a.getCOP());
            break;
            }
            out.close();
        }

    }


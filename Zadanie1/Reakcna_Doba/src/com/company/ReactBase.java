package com.company;
/*
    Doplňte funkcionalitu pre aplikáciu Reakčná doba

    Princíp hry

    Hráč po zadaní svojho mena spustí hru.
    Na otázku "Pripraveny ?" odpovedá stlačením ENTER
    Objaví sa hlásenie "Pozooor ..."
    a po náhodne dlhej dobe v intervale 0.5 - 3 sekundy sa objaví povel "START !!!"
    Cieľom hráča je v najrýchlejšom možnom čase opäť stlačiť ENTER.
    Program vypíše čas v milisekundách, ktorý uplynul od zobrazenia povelu START po stlačenie ENTER
    a zaradí ho do usporiadanej tabuľky výkonov (Meno hráča + výkon)
    Na obrazovku vypíše, kde sa daný výkon v tabuľke nachádza a to tak, že vypíše
    5 bezprostredne predchádzajúcich výkonov
    aktuálny výkon
    5 bezprostredne nasledujúcich výkonov
    To všetko v tvare Poradové číslo v tabuľke výkonov Tab6 Meno hráča Tab25 výkon
    Celú tabuľku s novým záznamom zapíše do textového súboru na disk, každý riadok v tvare MenoHraca:vykon

    Hra po spustení načíta zo súboru aktuálnu tabuľku výkonov a požiada hráča o prihlásenie (zadanie mena)
    Potom zobrazí MENU s položkami
    1 - Spusť hru
    2 - Zmena hráča
    3 - TOP 10
    4 - Koniec
    A reaguje podľa výberu

    Hru naprogramujte ako konzolovú aplikáciu aj ako aplikáciu s GUI. Využite pritom MVC.
    Pre meranie času využite funkciu System.currentTimeMillis();
    Hra musí ošetriť aj predčasné stlačenie pred zobrazením START ako chybu a potrestať ju (spôsob trestu je na vás)

    filip = newPlayer
    erik  = import&save records
    kiko  = menu
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReactBase {
    final int CM_PLAY = 1;
    final int CM_CHANGE_PLAYER = 2;
    final int CM_TOP10 = 3;
    final int CM_QUIT = 4;
    String Player;
    BufferedReader inputstr = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        boolean gameOn;
	    ReactBase Game = new ReactBase();
	    do
            gameOn =  Game.Run();
        while (gameOn);
    }

    public ReactBase() throws IOException{
        ImportRecords();
        NewPlayer();
    }

    public boolean Run() throws IOException{
        switch(Menu()){
            case CM_CHANGE_PLAYER:
                NewPlayer();
                return true;
            case CM_PLAY:
                int LastTime = Play(Player);
                Sort(Player, LastTime);
                ShowRecords(Player, LastTime);
                SaveRecords();
                return true;
            case CM_TOP10:
                ShowRecords("", 0);
                return true;
            case CM_QUIT:
                return false;
        }
        return true;
    }

    public void ImportRecords() throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("records.txt"));
        Map<String,Float > map = new HashMap<String,Float>();
        String riadok;
        String pom [];
        while((riadok=in.readLine()) != null){
            pom  = in.readLine().split(":");


            if(map.containsKey(pom[0])){
                if(map.get(pom[0]) < Float.parseFloat(pom[1])){
                    map.put(pom[0] , Float.parseFloat(pom[1]));
                }

            }
            else {
                map.put(pom[0], Float.parseFloat(pom[1]));
            }

        }
        in.close();
    }

    public void NewPlayer() throws IOException{
        do {
            System.out.println("Zadaj svoje meno");
            Player = inputstr.readLine().trim();
        }while(Player.equals(""));
    }

    public int Menu() throws IOException {

        System.out.println("Pick one");
        System.out.println("1. PLAY");
        System.out.println("2. CHANGE");
        System.out.println("3. TOP 10");
        System.out.println("4. END");

        char c = inputstr.readLine().charAt(0);
        int option = Character.getNumericValue(c);
        System.out.println(c);
        switch(option){
            case 1 :
                return CM_PLAY;

            case 2 :
                return CM_CHANGE_PLAYER;

            case 3:
                return  CM_TOP10;

            case 4:
                return CM_QUIT;

            default:
                System.out.println("Wrong option!");
                return CM_QUIT;
        }
        //return CM_QUIT;
    }

    public int Play(String who){

        return Integer.MAX_VALUE;
    }

    public void Sort(String who, int record){}

    public void ShowRecords(String who, int record){}

    public void SaveRecords(){}

}

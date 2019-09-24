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
    TODO Na obrazovku vypíše, kde sa daný výkon v tabuľke nachádza a to tak, že vypíše 5 bezprostredne predchádzajúcich výkonov aktuálny výkon 5 bezprostredne nasledujúcich výkonov
   `TODO To všetko v tvare Poradové číslo v tabuľke výkonov Tab6 Meno hráča Tab25 výkon
    Celú tabuľku s novým záznamom zapíše do textového súboru na disk, každý riadok v tvare MenoHraca:vykon -> I guess in the file there is no poradove cislo

    Hra po spustení načíta zo súboru aktuálnu tabuľku výkonov a požiada hráča o prihlásenie (zadanie mena)
    Potom zobrazí MENU s položkami
    1 - Spusť hru
    2 - Zmena hráča
    3 - TOP 10
    4 - Koniec
    A reaguje podľa výberu

    Hru naprogramujte ako konzolovú aplikáciu aj ako aplikáciu s GUI. Využite pritom MVC.
    Pre meranie času využite funkciu System.currentTimeMillis();
    TODO Hra musí ošetriť aj predčasné stlačenie pred zobrazením START ako chybu a potrestať ju (spôsob trestu je na vás)

    filip = newPlayer
    erik  = import&save records
    kiko  = menu


*/

import java.io.*;
import java.util.*;

public class ReactBase {
    final int CM_PLAY = 1;
    final int CM_CHANGE_PLAYER = 2;
    final int CM_TOP10 = 3;
    final int CM_QUIT = 4;
    String Player;
    List<Player> Players = new ArrayList<Player>();
    BufferedReader inputstr = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException , InterruptedException{
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

    public boolean Run() throws IOException , InterruptedException{
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

        String line ;
        String pom [] ;
        while((line = in.readLine()) != null){
            pom = line.split(":");
            Players.add(new Player(pom[0] ,Integer.parseInt(pom[1])));
        }
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

    }

    public int Play(String who) throws InterruptedException , IOException{
        Random r = new Random();
        System.out.println("Pripraveny ?");
        String enter  = inputstr.readLine();
        System.out.println("Pozooor!");
        int result = r.nextInt(6000-500) + 500;
        Thread.sleep(result);
        System.out.println("Start");
        long start = System.currentTimeMillis(); // start timer
        // wait for user to type enter
         // wait for input
        enter  = inputstr.readLine();
        long end  = System.currentTimeMillis(); // end timer

        long userTime  = end - start ; // calculate time in millis

        // idea is that we will call sleep() with random time in miliseconds that will range from 500 to 6000
        // then we will sout START and check what is the current time in millis
        // then we will wait for user to press enter again and check what is the current time in millis
        // then we will substract first time from the second and that should get us reaction time in millis and we need to convert that to seconds

        return (int)userTime;
    }

    public void Sort(String who, int record){
        // who  => name
        // record => time
        boolean check = false; // check if there was player in the list with name of player currently playing
        for( Player pl : Players){ // loop through players
            if(pl.getName().equals(who)){ // if there already is player with that name
                check = true;
                if(pl.getTime() > record){ // and if his reaction time is worse than the last one
                    pl.setTime(record); // set his time in record to this new time
                }
            }else {
                continue;
            }
        }
        if(check == false){ // if there is no player with that name in list
            Players.add(new Player(who , record)); // add a new player into the lsit
        }
        // SORTING PART
        Collections.sort(Players); // Sort them with use of comparable
    }

    public void ShowRecords(String who, int record){
        // if there are parameters than i need to show +-5
        // if params are empty we are doing top10
        int index  = 0 ;
        if(who.length() == 0){ // means that params are empty
            for(int i = 0 ; i < 10 ; i++ ){
               Player pl = Players.get(i);
               System.out.println(i+1 + " " + pl.getName() + ":" + pl.getTime());
            }
        }else{
            for(Player pl : Players) {
                if (pl.getName().equals(who)) {
                    index = Players.indexOf(pl);
                    break;
                }
            }
            for(int i = 0 ; i < Players.size() ; i++){
                if(i == (index-5)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index-4)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index-3)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index-2)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index-1)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index+1)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index+2)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index+3)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index+4)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
                if(i == (index+5)){ System.out.println(i+1 + " " + Players.get(i).getName() + ":" + Players.get(i).getTime() ); }
            }
         }
    }

    public void SaveRecords() throws IOException{
        BufferedWriter output = new BufferedWriter(new FileWriter("records.txt"));
        for(Player pl : Players){
            output.write(pl.getName() + ":" + pl.getTime());
            output.newLine();
        }
        output.close();

    }
}




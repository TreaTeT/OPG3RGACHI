package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.*;

import java.util.Random;

public class Controller {
    long zostavaCas = 300;
    long casDoZobrazenia = 100;
    long casDoZmiznutia = 100;
    long startTime;
    long pomocStart;
    boolean siModry;
    boolean local = true;

    @FXML TextArea cas;
    @FXML TextArea skore;
    @FXML Circle p1;
    @FXML Circle p2;
    @FXML AnchorPane plocha;
    @FXML Slider lvl;
    int skoreP1 = 0;
    int skoreP2 = 0;

    int pls;
    Random x = new Random();
    public double nahoda1;
    public double nahoda2;
    public int dobaZobrazenia = 2;

    public boolean prvyStart = true;

    public void odosielanie(String mojaSprava) throws IOException {

        int port = 8000;
        InetAddress adresa = InetAddress.getByName("192.168.10.1");
        DatagramSocket ds = new DatagramSocket();
        UDPEchoReader reader = new UDPEchoReader(ds);
        reader.setDaemon(true);
        DatagramPacket dp = new DatagramPacket(mojaSprava.getBytes(), mojaSprava.length(), adresa, port);
        ds.send(dp);

    }


    public void newGame() throws IOException {
        System.out.println(plocha.getWidth());
        System.out.println(plocha.getHeight());
        if (prvyStart) {
            cas.setText("120");
            skore.setText("0:0");
            nahoda1 = x.nextDouble();
            nahoda2 = x.nextDouble();
            //odosielanie("C " + nahoda1 + " " + nahoda2);
            if (nahoda1 > nahoda2){
                siModry = true;
                p2.setVisible(false);
                odosielanie("C 1");
            }
            else {
                siModry = false;
                p1.setVisible(false);
                odosielanie("C 2");
            }

            prvyStart = false;
            startTime = System.currentTimeMillis();
            pls = x.nextInt(11);
            odosielanie("D " + dobaZobrazenia + " " + lvl.getValue());
            odpocet.start();
            pomocStart = System.currentTimeMillis();
            zobrazenie.start();
            lvl.setDisable(true);


        }else{
            startTime = System.currentTimeMillis();
            odosielanie("D " + dobaZobrazenia + " " + lvl.getValue());
            odpocet.start();
            pomocStart = System.currentTimeMillis();
            zobrazenie.start();
            lvl.setDisable(true);
            if(siModry){
                p2.setVisible(false);
            }else{
                p1.setVisible(false);
            }
        }



    }
    public void botPohyb(){
        Random r = new Random();
        // vygenerovat 2 cisla a pacnut tam tu gulu
        int x =  r.nextInt((600 - 0) + 1) + 0;
        int y =  r.nextInt((600 - 0) + 1) + 0;

        if(x < p1.getRadius()*2){
            x = x + ( (int)  (p1.getRadius()*2) - x);
        }
        if(x>(plocha.getWidth()-p1.getRadius()*2)){
            x = 600 - (int) p1.getRadius()*2;
        }
        if(y < p1.getRadius()*2){
            y = y + ( (int)  (p1.getRadius()*2) - y);
        }
        if(y>(plocha.getHeight()-p1.getRadius()*2)){
            y = 600 - (int) p1.getRadius()*2;
        }

        if(siModry) {
            p2.setTranslateX(x);
            p2.setTranslateY(y);

        }else{
            p1.setTranslateX(x);
            p1.setTranslateY(y);
        }

    }

    public void botKlik(){
        Random r = new Random();
        int ciselko = r.nextInt((20 - 0) + 1) + 0;

        if(ciselko == 10){
            if(siModry){
                skoreP1++;
            }else{
                skoreP2++;
            }
        }

    }


    public void difficulty(){
        dobaZobrazenia = 11 - ((int)lvl.getValue() / 10) ;
    }

    AnimationTimer zobrazenie = new AnimationTimer() {
        @Override
        public void handle(long now) {
            casDoZobrazenia =  (x.nextInt(dobaZobrazenia) +5) - (int) ((System.currentTimeMillis() - pomocStart) / 1000);
            botKlik();
            if(casDoZobrazenia == 0){
                this.stop();
                pomocStart = System.currentTimeMillis();
                if (siModry) {
                    p2.setVisible(true);
                }
                else {
                    p1.setVisible(true);
                }

                zmiznutie.start();
            }
        }
    };
    AnimationTimer zmiznutie = new AnimationTimer() {
        @Override
        public void handle(long now) {
            casDoZmiznutia = dobaZobrazenia - (int) ((System.currentTimeMillis() - pomocStart) / 1000);
            if(casDoZmiznutia == 0){
                this.stop();
                pls = x.nextInt(11);
                pomocStart = System.currentTimeMillis();
                if (siModry) {
                    p2.setVisible(false);
                }
                else {
                    p1.setVisible(false);
                }
                if(local){
                    botPohyb();
                }
                zobrazenie.start();
            }
        }
    };




    AnimationTimer odpocet = new AnimationTimer() {
        @Override
        public void handle(long now) {
            zostavaCas = 120 - (int) ((System.currentTimeMillis() - startTime) / 1000);

            cas.setText((zostavaCas / 60) + ":" + ((zostavaCas - ((zostavaCas / 60) * 60))));
            if(zostavaCas == 0){
                this.stop();
                zobrazenie.stop();
                zmiznutie.stop();

                System.out.println("Hrac 1 mal" + skoreP1);
                System.out.println("Hrac 2 ma " + skoreP2);


                System.out.println(lvl.getValue());

                resetHry();

            }
        }
    };

    public void resetHry(){
        // zviditelnym oba
        p1.setVisible(true);
        p2.setVisible(true);
        // dam ich na default pozicie
        p1.setTranslateX(200);
        p1.setTranslateY(200);
        p2.setTranslateX(300);
        p2.setTranslateY(200);
        // inputy hore
        cas.setText("120");
        skore.setText("0:0");
        // resetujem skore
        skoreP1 = 0;
        skoreP2  = 0;

        // difficulty
        lvl.setDisable(false);

    }

    public void zasahP1() throws IOException {

        if (!siModry){
            p1.setVisible(false);

            pomocStart = System.currentTimeMillis();
            zobrazenie.start();
            skoreP2++;
            skore.setText(skoreP1 + ":" + skoreP2);
            odosielanie("S2 " + skoreP2);
        }
    }

    public void zasahP2() throws IOException {

        if (siModry){
            p2.setVisible(false);

            pomocStart = System.currentTimeMillis();
            zobrazenie.start();
            skoreP1++;
            skore.setText(skoreP1 + ":" + skoreP2);
            odosielanie("S1 " + skoreP1);
        }
    }




    public static class UDPEchoReader extends Thread{
        public boolean active;
        public DatagramSocket ds;

        public UDPEchoReader(DatagramSocket ds){
            this.ds = ds;
            active = true;
        }
        @Override
        public void run(){
            byte bufer[] = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bufer, bufer.length);
            while (active){
                try{
                    ds.receive(dp);
                }catch (IOException e){e.printStackTrace(); active=false;}
                String prijataSprava = new String(dp.getData(),0,dp.getLength());
                System.out.println("Prijate zo servera: " + prijataSprava);
            }
        }
    }

    @FXML
    void initialize(){
        p1.setOnMouseDragged(event -> {
            try {
                drag(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        p2.setOnMouseDragged(event -> {
            try {
                drag(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        p1.setTranslateX(200);
        p1.setTranslateY(200);
        p2.setTranslateX(300);
        p2.setTranslateY(200);
        server localServer = new server();
        localServer.start();


    }

    public void drag(MouseEvent event) throws IOException {
        Node n = (Node)event.getSource();
        if(plocha.getWidth() - 76 > (n.getTranslateX() + event.getX()) && (n.getTranslateX() + event.getX()) > 0){
            n.setTranslateX(n.getTranslateX() + event.getX());
        }
        if(plocha.getHeight() -76 > (n.getTranslateY() + event.getY()) && (n.getTranslateY() + event.getY()) > 0) {
            n.setTranslateY(n.getTranslateY() + event.getY());
        }

        odosielanie("P1 " + p1.getTranslateX() + " " + p1.getTranslateY());
        odosielanie("P2 " + p2.getTranslateX() + " " + p2.getTranslateY());

    }

    protected class server extends Thread {
        @Override
        public void run(){
            int port = 8000;
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(port);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            System.out.println("Kanál na porte " + port + " otvorený");
            byte buffer[] = new byte[1024];

            DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
            while (true) {
                try {
                    ds.receive(dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String sprava = new String(dp.getData(), 0, dp.getLength());
                System.out.println(sprava);
                if (sprava.startsWith("C")){
                    String [] arr = sprava.split(" ");
                    if (arr[1].compareTo("1") == 1){
                        p2.setVisible(false);
                        siModry = true;
                    }
                    else {
                        p1.setVisible(false);
                        siModry = false;
                    }
                }

                if(!local) {
                    if (sprava.startsWith("P1")) {
                        String helparr[];
                        helparr = sprava.split(" ");
                        p1.setTranslateX(Double.parseDouble(helparr[1]));
                        p1.setTranslateY(Double.parseDouble(helparr[2]));
                    }
                    if (sprava.startsWith("P2")) {
                        String helparr[];
                        helparr = sprava.split(" ");
                        p2.setTranslateX(Double.parseDouble(helparr[1]));
                        p2.setTranslateY(Double.parseDouble(helparr[2]));
                    }
                    if (sprava.startsWith("S1")) {
                        String helparr[];
                        helparr = sprava.split(" ");
                        skoreP1 = Integer.parseInt(helparr[1]);
                    }
                    if (sprava.startsWith("S2")) {
                        String helparr[];
                        helparr = sprava.split(" ");
                        skoreP2 = Integer.parseInt(helparr[1]);
                    }
                    if (sprava.startsWith("D")) {
                        String helparr[];
                        helparr = sprava.split(" ");
                        dobaZobrazenia = Integer.parseInt(helparr[1]);
                        lvl.setValue(Integer.parseInt(helparr[2]));
                        lvl.setDisable(true);
                    }
                }


            }
        }
    }
}


package sample;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.*;

public class Controller {
    @FXML TextField pocetKociek;
    @FXML TextField pocetHodov;
    @FXML Button hod;
    @FXML AreaChart chart;
    @FXML Button gulicky;
    @FXML TextField pocetHladin;
    @FXML TextField  pocetGuliciek;

    Random random = new Random();
    
    public int galtonBoard(int pozicia, int hladina){
        if (hladina == 0) return pozicia;
        else if (random.nextInt(2)==1) return galtonBoard(pozicia+1,hladina-1);
        return galtonBoard(pozicia-1,hladina-1);
    }

    public void Gulickuj() {
        // reset
        chart.getData().removeAll(chart.getData());

        int max = Integer.parseInt(pocetHladin.getText())*2;
        int pocet[] = new int[max+1];
        for (int i = 0; i < pocet.length; i++) {
            pocet[i] = 0;
        }
        for (int i = 0; i < Integer.parseInt(pocetGuliciek.getText()); i++) {
            int hod = galtonBoard(Integer.parseInt(pocetHladin.getText()),Integer.parseInt(pocetHladin.getText()));
            pocet[hod]++;
        }

        XYChart.Series series = new XYChart.Series();
        series.setName("Data");
        for(int i = 1;i<pocet.length;i=i+1){
            series.getData().add(new XYChart.Data(Integer.toString(i),pocet[i]));
        }
        chart.getData().add(series);

    }
    public void rucaj() {
        // reset
        chart.getData().removeAll(chart.getData());
        int ph = Integer.parseInt(pocetHodov.getText());
        int pk = Integer.parseInt(pocetKociek.getText());

        HashMap<Integer, Integer> hody = new HashMap<Integer, Integer>(); // key = hod , value = pocet hodov

        for(int i =  0 ; i < ph ; i++){
            int sum = 0;
            for(int j = 0; j < pk; j++){
                int randomNum = random.nextInt(6) + 1;
                sum += randomNum;
                System.out.println("sum je "  + sum);
            }

            if(hody.containsKey(sum)){
               hody.put(sum ,  hody.get(sum)+1);
            }else{
                hody.put(sum , 1);
            }
        }
        XYChart.Series series = new XYChart.Series();

        Map<Integer, Integer> sortedhody = new TreeMap<Integer, Integer>(hody);
        for (Map.Entry<Integer,Integer> entry : sortedhody.entrySet()){
            series.setName("Data");
            series.getData().add(new XYChart.Data(Integer.toString(entry.getKey()), entry.getValue()));
        }
        chart.getData().add(series);
    }
}

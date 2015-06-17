package sample;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.StreamEmgType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import myo.EmgDataCollector;
import myo.utils.EmgUtils;

import java.util.ArrayList;

/**
 * Created by Alex on 14.06.2015.
 */
public class Controller {
    public BarChart<String, Double> chart;
    private Hub hub;
    private EmgDataCollector dataCollector;
    private XYChart.Data<String, Double>[] seriesData;

    public void reset(ActionEvent actionEvent) {
    }

    public void initialize() {
        initMyo();
        XYChart.Series<String, Double> series = new XYChart.Series();
        seriesData = new XYChart.Data[8];
        for (int i = 0; i < 8; i++) {
            seriesData[i] = new XYChart.Data<>(String.valueOf(i), 1.0);
            series.getData().add(seriesData[i]);
        }
        chart.getData().add(series);
    }

    public void start() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    hub.run(1000 / 3);
                    ArrayList<byte[]> emgDatas = dataCollector.getAllEmgData();
                    double[] standardDeviations = EmgUtils.calculateStandardDeviation(emgDatas);

                    for (double dub : standardDeviations) {
                        System.out.print(String.format("%.2f", dub) + " ");
                    }
                    System.out.println();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < standardDeviations.length; i++) {
                                seriesData[i].setYValue(standardDeviations[i]);
                            }
                        }
                    });
                }
            }
        };
        thread.start();
    }

    public void initMyo() {
        try {
            hub = new Hub("com.example.emg-data-sample");

            System.out.println("Attempting to find a Myo...");
            Myo myo = hub.waitForMyo(10000);

            if (myo == null) {
                throw new RuntimeException("Unable to find a Myo!");
            }

            System.out.println("Connected to a Myo armband!");
            myo.setStreamEmg(StreamEmgType.STREAM_EMG_ENABLED);
            dataCollector = new EmgDataCollector();
            hub.addListener(dataCollector);
        } catch (Exception e) {
            System.err.println("Error: ");
            e.printStackTrace();
            System.exit(1);
        }
    }

}

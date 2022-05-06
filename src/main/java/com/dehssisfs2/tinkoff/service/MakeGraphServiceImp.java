package com.dehssisfs2.tinkoff.service;

import com.dehssisfs2.tinkoff.model.Model;
import com.dehssisfs2.tinkoff.model.ModelRatesForGraph;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
public class MakeGraphServiceImp implements MakeGraphService {

    @Autowired
    ModelServiceImp modelService;

    public String makeGraph(int code, long period) {


        List<Date> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();
        List<Double> zData = new ArrayList<>();

        List<ModelRatesForGraph> list = modelService.getRatesByData(code, period);

        Model model = MainService.getСurrentRate(code);
        if (list.isEmpty()){
            list.add(new ModelRatesForGraph(new Date(new Date().getTime()-period),model.getRateRUB(), model.getRateVal()));
//            list.add(new ModelRatesForGraph(new Date(), model.getRateRUB(), model.getRateVal()));

        }

        ModelRatesForGraph mg = new ModelRatesForGraph(new Date(), model.getRateRUB(), model.getRateVal());
        list.add(mg);

        // сортируем по дате
        list.sort(ModelRatesForGraph::compareTo);

        // чистим от данных (пиков)
//        List<ModelRatesForGraph> list2 = new ArrayList<>();
//        double lastBuy = list.get(0).getRateBuy();
//        double lastSell = list.get(0).getRateSell();
//
//        for (ModelRatesForGraph el : list) {
//            if ((el.getRateSell() - lastSell) < (lastSell * 0.01)) {
//                list2.add(el);
//            }
//        }

        // чистим от данных (пиков)
//        List<ModelRatesForGraph> list2= new ArrayList<>();
//        double lastBuy=list.get(0).getRateBuy();
//        double lastSell=list.get(0).getRateSell();
//
//        for ( ModelRatesForGraph el: list) {
//            if ((el.getRateSell()-lastSell)<(lastSell*0.01)){
//                list2.add(el);
//            }
//        }

        for (ModelRatesForGraph el : list) {
            xData.add(el.getDate());
            yData.add(el.getRateBuy());
            zData.add(el.getRateSell());
        }

        XYChart chart = new XYChartBuilder()
                .width(1280)
                .height(720)
                .title(modelService.toStringByCode(code))
                .theme(Styler.ChartTheme.Matlab)
                .build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setCursorLineWidth(2f);
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);

//        chart.getStyler().setDatePattern("dd-MMM");

//        XYSeries seriesSell = chart.addSeries(modelService.toStringByCode(code) + " продажа", xData, zData);
//        XYSeries seriesBuy = chart.addSeries(modelService.toStringByCode(code) + " покупка", xData, yData);

        XYSeries seriesSell = chart.addSeries("продажа", xData, zData);
        XYSeries seriesBuy = chart.addSeries("покупка", xData, yData);
        seriesBuy.setMarker(SeriesMarkers.NONE);
        seriesBuy.setLineColor(Color.BLUE);
        seriesSell.setLineColor(Color.RED);
//        seriesBuy.setShowInLegend(true);
        seriesSell.setMarker(SeriesMarkers.NONE);
//        seriesSell.setShowInLegend(true);

        String fileName = givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(6) + "_" + code + "_150DPI";

        try {
            byte[] bitmapBytes = BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.JPG);

//            Files.write(Paths.get("txt.jpg"), bitmapBytes);
            BitmapEncoder.saveBitmapWithDPI(chart, fileName, BitmapEncoder.BitmapFormat.JPG, 150);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
//        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }
}

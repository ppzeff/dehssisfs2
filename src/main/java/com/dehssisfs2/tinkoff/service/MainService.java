package com.dehssisfs2.tinkoff.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dehssisfs2.tinkoff.model.JsonModel;
import com.dehssisfs2.tinkoff.model.Model;
import com.dehssisfs2.tinkoff.model.Payload;
import com.dehssisfs2.tinkoff.model.Rates;
import com.dehssisfs2.tinkoff.repo.JsonModelRepo;
import com.dehssisfs2.tinkoff.repo.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class MainService {

    static int code_RUR = 643;
    static int code_USD = 840;
    static int code_EUR = 978;
    static int code_GBP = 826;


    @Autowired
    InitDAOImp initDAOImp;

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    JsonModelRepo jsonModelRepo;

  public static Map<Integer, Model> cashModelMap = new HashMap<>();


    public List<Model> getRateByCode(List<Integer> codeList) throws IOException {

        if (cashModelMap.isEmpty()) {
            System.out.println("cashModelMap.isEmpty()");
            cashModelMap = initDAOImp.initMap();
        }


        Payload payload = jsonModelRepo.getJsonModel().getPayload();

        System.out.println("MainService.getRateByCode start: " + new Date(payload.getLastUpdate().getMilliseconds()));

        List<Model> modelList = new ArrayList<>();

        for (Integer elCode : codeList) {
            Model model = new Model();
            Rates rates = getRatesDebitCardsTransfers(payload, elCode);

            if (!(rates.getBuy() == 0 || rates.getSell() == 0) && (cashModelMap.get(elCode).getRateRUB() != rates.getBuy() && cashModelMap.get(elCode).getRateVal() != rates.getSell())) {
//            }
//            else if (cashModelMap.get(elCode).getRateRUB() != rates.getBuy() && cashModelMap.get(elCode).getRateVal() != rates.getSell()) {

                model.setRateRUB(rates.getBuy());
                model.setRateVal(rates.getSell());
                model.setCode(elCode);
                model.setLastUpdate(payload.getLastUpdate().getMilliseconds());
                cashModelMap.put(elCode, model);
                modelList.add(model);
            }

        }

        return modelList;
    }


    public void newService() throws IOException {

        URL url = new URL("https://api.tinkoff.ru/v1/currency_rates/");
        URL url2 = new URL("https://www.tinkoff.ru/api/v1/currency_rates");


        ObjectMapper objectMapper = new ObjectMapper();
        //       com.ppzeff.rates.RatesModel ratesModel =  objectMapper.readValue(new File("src/main/resources/com.ppzeff.rates.json"), com.ppzeff.rates.RatesModel.class);
        JsonModel jsonModel = objectMapper.readValue(url2, JsonModel.class);

        Model model = new Model();

        Rates ratesUSD = getRatesDebitCardsTransfersUSD(jsonModel.getPayload());
        Rates ratesEUR = getRatesDebitCardsTransfersEUR(jsonModel.getPayload());
        Rates ratesGBP = getRatesDebitCardsTransfersGBP(jsonModel.getPayload());


        System.out.println("DebitCardsTransfers: \n" + "Buy (USD to RUB) " + ratesUSD.getBuy() + "\nSell (RUB to USD) " + ratesUSD.getSell());
        System.out.println("DebitCardsTransfers: \n" + "Buy (EUR to RUB) " + ratesEUR.getBuy() + "\nSell (RUB to EUR) " + ratesEUR.getSell());
        System.out.println("DebitCardsTransfers: \n" + "Buy (GBP to RUB) " + ratesGBP.getBuy() + "\nSell (RUB to GBP) " + ratesGBP.getSell());

    }

    public static Rates getRatesDebitCardsTransfers(Payload payload, int code) {
        ArrayList<Rates> arrayList = payload.getRates();
        for (Rates rates : arrayList) {
            if (rates.getCategory().equals("DebitCardsTransfers") && rates.getFromCurrency().getCode() == code && rates.getToCurrency().getCode() == code_RUR) {
                return rates;
            }
        }
        return null;
    }

    public static Rates getRatesDebitCardsTransfersGBP(Payload payload) {
        ArrayList<Rates> arrayList = payload.getRates();
        for (Rates rates : arrayList) {
            if (rates.getCategory().equals("DebitCardsTransfers") && rates.getFromCurrency().getCode() == code_GBP && rates.getToCurrency().getCode() == code_RUR) {
                return rates;
            }
        }
        return null;
    }

    public static Rates getRatesDebitCardsTransfersEUR(Payload payload) {
        ArrayList<Rates> arrayList = payload.getRates();
        for (Rates rates : arrayList) {
            if (rates.getCategory().equals("DebitCardsTransfers") && rates.getFromCurrency().getCode() == code_EUR && rates.getToCurrency().getCode() == code_RUR) {
                return rates;
            }
        }
        return null;
    }

    public static Rates getRatesDebitCardsTransfersUSD(Payload payload) {
        ArrayList<Rates> arrayList = payload.getRates();
        for (Rates rates : arrayList) {
            if (rates.getCategory().equals("DebitCardsTransfers") && rates.getFromCurrency().getCode() == code_USD && rates.getToCurrency().getCode() == code_RUR) {
                return rates;
            }
        }
        return null;
    }

    public static Model getСurrentRate(int code) {
        return cashModelMap.get(code);
    }
}

package com.dehssisfs2.tinkoff.service;

import com.dehssisfs2.tinkoff.model.Model;
import com.dehssisfs2.tinkoff.model.ModelRatesForGraph;
import com.dehssisfs2.tinkoff.repo.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ModelServiceImp implements ModelService {

    @Autowired
    ModelRepo modelRepo;

    public final static long ONE_SECOND = 1000;
    public final static long SECONDS = 60;

    public final static long ONE_MINUTE = ONE_SECOND * SECONDS;
    public final static long MINUTES = 60;

    public final static long ONE_HOUR = ONE_MINUTE * MINUTES;
    public final static long HOURS = 24;

    public final static long ONE_DAY = ONE_HOUR * HOURS;
    public final static long WEEKS = 7;

    public final static long ONE_WEEK = ONE_DAY * WEEKS;

    public List<ModelRatesForGraph> getRatesByData(int code, long period) {
//        List<Model> allByCode = modelRepo.findAllByCode(code);
        Date now = new Date();
        List<Model> allByCode = modelRepo.findByPeriod(code, now.getTime() - period, now.getTime());

        List<ModelRatesForGraph> list = new ArrayList<>();
        Date date = new Date();
        for (Model elModel : allByCode) {
//            if (elModel.getLastUpdate() > date.getTime() - ONE_DAY  &&
//            elModel.getLastUpdate() < date.getTime()) {
            list.add(new ModelRatesForGraph(new Date(elModel.getLastUpdate()), elModel.getRateRUB(), elModel.getRateVal()));
//            map.put(new Date(elModel.getLastUpdate()), elModel.getRateRUB());
//            }
        }
        return list;
    }

    public String toStringByCode(int code) {
        String codeToStr = "";
        switch (code) {
            case 840:
                codeToStr = "$ USD (доллар)";
                break;
            case 978:
                codeToStr = "€ EUR (евро)";
                break;
            case 826:
                codeToStr = "£ GBP (фунт)";
                break;
        }

        return codeToStr;
    }

    //    1636117182322
//    1636050370845
}

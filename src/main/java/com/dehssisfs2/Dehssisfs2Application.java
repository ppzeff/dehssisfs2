package com.dehssisfs2;

import com.dehssisfs2.LOTO.property.FileStorageProperties;
import com.dehssisfs2.tinkoff.model.Model;
import com.dehssisfs2.tinkoff.repo.ModelRepo;
import com.dehssisfs2.tinkoff.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Dehssisfs2Application {

    public static void main(String[] args) {
        SpringApplication.run(Dehssisfs2Application.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        System.out.println(new Date());
        System.out.println("!!!!!!!!!!!!");
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
        System.out.println(new Date());
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Autowired
    ModelRepo modelRepo;

    @Autowired
    MainService mainService;

    @Scheduled(fixedDelay = 10 * 1000L, initialDelay = 0)
    public void scheduled1() throws IOException {
        List<Integer> listAllCode = modelRepo.findAllCode(); // new ArrayList<>();

        List<Model> modelList = mainService.getRateByCode(listAllCode);

        for (Model elModel : modelList) {
            modelRepo.save(elModel);
            System.out.println(
                    new Date(elModel.getLastUpdate()) + " SAVE " +
                            elModel.toString());
        }
    }
}

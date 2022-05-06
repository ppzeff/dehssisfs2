package com.dehssisfs2.LOTO.controllers;

import com.dehssisfs2.LOTO.models.LOTONdModel;
import com.dehssisfs2.LOTO.models.WorkSitesModel;
import com.dehssisfs2.LOTO.payload.UploadFileResponse;
import com.dehssisfs2.LOTO.repository.LOTONdRepository;
import com.dehssisfs2.LOTO.repository.WorkSitesRepository;
import com.dehssisfs2.LOTO.service.FileStorageService;
import com.dehssisfs2.LOTO.service.SendEmailServiceThymeleafImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class LotoController {

    @Autowired
    private LOTONdRepository lotoNdRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private SendEmailServiceThymeleafImp emailSender;
    @Autowired
    private WorkSitesRepository workSitesRepository;

//    @Autowired
//    private sendThymeleafEmailService emailSender;

    @GetMapping("/workpermit/loto")
    public String home(Model model) {
        model.addAttribute("title", "Safety_LOTO_ND");
        Iterable<WorkSitesModel> workSites = workSitesRepository.findAll();
        model.addAttribute("workSites", workSites);

        return "lototemplate";
    }

    @GetMapping("/workpermit/loto/creatSites")
    public String creatSites(Model model) {
        model.addAttribute("title", "Safety_LOTO_ND");
        workSitesRepository.save(new WorkSitesModel("Фасовка"));
        workSitesRepository.save(new WorkSitesModel("Процесс"));
        workSitesRepository.save(new WorkSitesModel("Склад ЗЧ"));
        workSitesRepository.save(new WorkSitesModel("Электрика"));//
        workSitesRepository.save(new WorkSitesModel("Территория"));
        workSitesRepository.save(new WorkSitesModel("ССиМ"));
        workSitesRepository.save(new WorkSitesModel("СГП"));
        workSitesRepository.save(new WorkSitesModel("Пилотный завод"));//
        workSitesRepository.save(new WorkSitesModel("Очистные сооружения"));
        workSitesRepository.save(new WorkSitesModel("Лаборатория физхим"));
        workSitesRepository.save(new WorkSitesModel("Копакер"));
        workSitesRepository.save(new WorkSitesModel("Лаборатория микроб"));
        workSitesRepository.save(new WorkSitesModel("Котельная"));
        workSitesRepository.save(new WorkSitesModel("АХУ"));
        return "lototemplate";
    }

    @PostMapping("/workpermit/loto/regLOTO")
    public String addAudit1(Model model,
                            @RequestParam String specialistFIO,
                            boolean complexBlocking,
                            String workSite,
                            String dataStart,
                            @RequestParam("file") MultipartFile file
    ) throws ParseException {

        if (!file.getContentType().contains("image") || !(file.getSize() > 100)) {
            model.addAttribute("error", "приложенный файл не являеться фото");
            return "lotondregfalse";
        }

        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());

        System.out.println(fileDownloadUri);

        LOTONdModel lotoNdModel = new LOTONdModel(specialistFIO, complexBlocking, workSite, fileName, new SimpleDateFormat("yyyy-MM-dd").parse(dataStart));
        lotoNdRepository.save(lotoNdModel);

        try {
            emailSender.sendEmail(lotoNdModel, fileStorageService.loadFileAsResource(fileName).getFile().getAbsolutePath());
//            emailSender.sendThymeleafEmail(lotoNd,fileStorageService.loadFileAsResource(fileName).getFile().getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("lotoNdModel", lotoNdModel);
        model.addAttribute("title", "Safety_LOTO_ND");
        model.addAttribute("id", lotoNdModel.getId());
        model.addAttribute("fileDownloadUri", fileDownloadUri);

        return "lotondregcomplite";
    }

    @GetMapping("/workpermit/loto/allview")
    public String allLOTOND(Model model) {

        Iterable<LOTONdModel> lotoNds = lotoNdRepository.findAll();

        model.addAttribute("title", "Safety_LOTO_ND");
        model.addAttribute("LOTONds", lotoNds);
        return "all_LOTOND_view";
    }

}

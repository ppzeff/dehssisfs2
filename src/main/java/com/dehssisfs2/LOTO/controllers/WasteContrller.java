package com.dehssisfs2.LOTO.controllers;

import com.dehssisfs2.LOTO.models.WasteShiftReportModel;
import com.dehssisfs2.LOTO.models.WasteWorkersModel;
import com.dehssisfs2.LOTO.payload.UploadFileResponse;
import com.dehssisfs2.LOTO.repository.WasteShiftReportRepository;
import com.dehssisfs2.LOTO.repository.WasteWorkersRepository;
import com.dehssisfs2.LOTO.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class WasteContrller {

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private WasteShiftReportRepository wasteShiftReportRepository;
    @Autowired
    private WasteWorkersRepository wasteWorkersRepository;


    @GetMapping("/waste/shiftreport/")
    public String home(Model model) {

        Iterable<WasteWorkersModel> wasteWorkers = wasteWorkersRepository.findAll();
        model.addAttribute("title", "Environmental_WASTE_REPORT");
        model.addAttribute("wasteWorkers", wasteWorkers);
        return "wasteshiftreporttemplate";
    }


    @PostMapping("/waste/shiftreport/regreport")
    public String addAudit1(Model model,
                            @RequestParam
                                    String firstFIO,
                            String secondFIO,
                            int numberWagons,
                            int numberBoxes,
                            int numberPallets,
                            boolean shiftDay,
                            @RequestParam("firstFoto") MultipartFile firstFoto,
                            @RequestParam("secondFoto") MultipartFile secondFoto,
                            @RequestParam("thirdFoto") MultipartFile thirdFoto,
                            @RequestParam("fourthFoto") MultipartFile fourthFoto
    ) {

        if ((!firstFoto.getContentType().contains("image") || !(firstFoto.getSize() > 100)) ||
                (!secondFoto.getContentType().contains("image") || !(secondFoto.getSize() > 100)) ||
                (!thirdFoto.getContentType().contains("image") || !(thirdFoto.getSize() > 100)) ||
                (!fourthFoto.getContentType().contains("image") || !(fourthFoto.getSize() > 100))) {
            model.addAttribute("error", "приложенный файл не являеться фото");
            return "wasteregfalse";
        }

        String fileName1 = fileStorageService.storeFile(firstFoto);
        String fileName2 = fileStorageService.storeFile(secondFoto);
        String fileName3 = fileStorageService.storeFile(thirdFoto);
        String fileName4 = fileStorageService.storeFile(fourthFoto);

        String fileDownloadUri1 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName1)
                .toUriString();
        String fileDownloadUri2 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName2)
                .toUriString();
        String fileDownloadUri3 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName3)
                .toUriString();
        String fileDownloadUri4 = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName4)
                .toUriString();

        new UploadFileResponse(fileName1, fileDownloadUri1,
                firstFoto.getContentType(), firstFoto.getSize());
        new UploadFileResponse(fileName2, fileDownloadUri2,
                secondFoto.getContentType(), secondFoto.getSize());
        new UploadFileResponse(fileName3, fileDownloadUri3,
                thirdFoto.getContentType(), thirdFoto.getSize());
        new UploadFileResponse(fileName4, fileDownloadUri4,
                fourthFoto.getContentType(), fourthFoto.getSize());

        WasteShiftReportModel wasteShiftReportModel = new WasteShiftReportModel(firstFIO, secondFIO, numberWagons, numberBoxes,
                numberPallets, "comment", fileName1, fileName2, fileName3, fileName4, shiftDay);
        wasteShiftReportRepository.save(wasteShiftReportModel);

        System.out.println(firstFIO);
        System.out.println(secondFIO);
        System.out.println(numberWagons);
        System.out.println(numberBoxes);
        System.out.println(numberPallets);
        System.out.println(fileStorageService.storeFile(firstFoto));
        System.out.println(fileStorageService.storeFile(secondFoto));
        System.out.println(fileStorageService.storeFile(thirdFoto));
        System.out.println(fileStorageService.storeFile(fourthFoto));

        model.addAttribute("title", "Environmental_WASTE_REPORT");
//        model.addAttribute("id", wasteShiftReportModel.getId());
        model.addAttribute("wasteShiftReport", wasteShiftReportModel);

        return "wasteregcomplite";
    }
}

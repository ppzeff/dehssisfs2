package com.dehssisfs2.LOTO.service;

import com.dehssisfs2.LOTO.models.LOTONdModel;
import com.dehssisfs2.LOTO.property.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendEmailServiceThymeleafImp implements sendEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(LOTONdModel lotoNdModel, String photoName) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        try {

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                MimeMessageHelper.MULTIPART_MODE_MIXED,
                    StandardCharsets.UTF_8.name());

//        String html = getHtmlContent(mail);
            Context context = new Context();
            Map<String, Object> map = new HashMap<>();
            map.put("lotoND", lotoNdModel);
            map.put("dateStart", new SimpleDateFormat("dd.MMM.yyyy").format(lotoNdModel.getDataStart()));
            map.put("dateRegistration", formatter.format(lotoNdModel.getSomeDate()));
            context.setVariables(map);
            System.out.println(lotoNdModel.getFotoName());

            String html = templateEngine.process("mailTemplate", context);

            InternetAddress[] addresses = new InternetAddress[2];
            addresses[0] = new InternetAddress(MyConstants.EMAIL_1);
            addresses[1] = new InternetAddress(MyConstants.EMAIL_2);

            helper.setTo(addresses);
            helper.setFrom("DEHSSISFS<" + MyConstants.MY_EMAIL + ">");
            helper.setSubject("Зарегистрирован LOTO наряд №" + lotoNdModel.getId() + ", " + formatter.format(lotoNdModel.getSomeDate()));
            helper.setText(html, true);
            System.out.println(lotoNdModel.getFotoName());

//        helper.addAttachment(lotoNd.getFotoName(), new ClassPathResource("upload/"+lotoNd.getFotoName()));
//        helper.addInline(lotoNd.getFotoName(), new ClassPathResource("upload/"+lotoNd.getFotoName()), "image/png");
            helper.addInline(lotoNdModel.getFotoName(), new FileSystemResource(
                    new File(photoName)));

            emailSender.send(message);
            System.out.println(new Date() + " email send");
//            System.out.println("file " + photoName + " delete: " + new File(photoName).delete());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    private String getHtmlContent(Mail mail) {
//        Context context = new Context();
//        context.setVariables(mail.getHtmlTemplate().getProps());
//        return templateEngine.process(mail.getHtmlTemplate().getTemplate(), context);
//    }
}
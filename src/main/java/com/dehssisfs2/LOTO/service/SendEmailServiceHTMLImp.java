package com.dehssisfs2.LOTO.service;

import com.dehssisfs2.LOTO.models.LOTONdModel;
import com.dehssisfs2.LOTO.property.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SendEmailServiceHTMLImp implements sendEmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void sendEmail(LOTONdModel lotoNdModel, String photoName)  {
        Date date = lotoNdModel.getSomeDate();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        MimeMessage message = emailSender.createMimeMessage();

        try {
            message.setFrom("DEHSSISFS<" + MyConstants.MY_EMAIL + ">");

            Address[] addresses = new Address[2];
            addresses[0] = new InternetAddress(MyConstants.EMAIL_1);
            addresses[1] = new InternetAddress(MyConstants.EMAIL_2);


            message.addRecipients(Message.RecipientType.TO, addresses);
            message.setSubject("Зарегистрировано LOTO разрешение №" + lotoNdModel.getId() + ", " + formatter.format(date));

        //Певый кусочек - html
        MimeBodyPart part1 = new MimeBodyPart();
        part1.addHeader("Content-Type", "text/plain; charset=UTF-8");
        String htmlText = "<html><body>" + "\n" +

                "<p>Зарегистрировано LOTO разрешение № <span style=\"color: #ff0000;\" data-darkreader-inline-color=\"\"><strong>" +
                lotoNdModel.getId() + ", в " + formatter.format(date) + "</strong></span></p>\n" +
                "<p>ФИО LOTO специалиста: <strong>" + lotoNdModel.getSpecialistFIO() + "</strong></p>\n" +
                "<p>Дата начала работ: <strong>" + new SimpleDateFormat("dd-MM-yyyy").format(lotoNdModel.getDataStart()) + "</strong></p>\n" +
                "<p>Комплексная блокировка?: <strong>" + lotoNdModel.isComplexBlocking() + "</strong></p>\n" +
                "<p>Участок проведения работ: <strong>" + lotoNdModel.getWorkSite() + "</strong></p>\n" +
                "<p>Фото титульного листа LOTO разрешения: </p>" +
                "<br><img src=\"cid:" + lotoNdModel.getFotoName() +
                "\"></body></html>";

        part1.setDataHandler(new DataHandler(htmlText, "text/html; charset=\"utf-8\""));

        //Второй кусочек - файл
/*
        MimeBodyPart part2 = new MimeBodyPart();
        part2.setFileName(MimeUtility.encodeWord(file.getName()));
        part2.setDataHandler(new DataHandler(new FileDataSource(file)));
        multipart.addBodyPart(part2);
 */
        // Третий кусочек - вложение фото
        MimeBodyPart imagePart = new MimeBodyPart();
        System.out.println(photoName);
        imagePart.attachFile(photoName);
        String cid = lotoNdModel.getFotoName();
        System.out.println("cid: " + cid);
        imagePart.setContentID("<" + cid + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);

        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(imagePart);
        mimeMultipart.addBodyPart(part1);

        message.setContent(mimeMultipart);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.emailSender.send(message);
    }
}

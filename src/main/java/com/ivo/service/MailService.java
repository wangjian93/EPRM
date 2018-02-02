package com.ivo.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MailService {

    @Autowired
    private JavaMailSender jms;

    private ExecutorService pool = Executors.newFixedThreadPool(5);

    public void sendSimpleMail(String from, String to, String subject,
                               String text) {
        Assert.hasText(to);
        String[] tos = new String[1];
        tos[0] = to;
        sendSimpleMails(from, tos, subject, text);
    }

    public void sendSimpleMails(String from, String[] tos, String subject,
                                String text) {
        sendSimpleMails(from, tos, null, subject, text);
    }

    public void sendSimpleMails(final String from, final String[] tos,
                                final String[] ccs, final String subject, final String text) {
        Assert.hasText(from);
        Assert.notEmpty(tos);
        Assert.hasText(subject);
        Assert.hasText(text);
        pool.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setFrom(from);
                mail.setTo(tos);
                if (ccs != null && ccs.length > 0)
                    mail.setCc(ccs);
                mail.setSubject(subject);
                mail.setText(text);
                jms.send(mail);
            }
        }));
    }

    public void sendHtmlMail(String from, String to, String subject, String html) {
        Assert.hasText(to);
        String[] tos = new String[1];
        tos[0] = to;
        sendHtmlMails(from, tos, subject, html);
    }

    public void sendHtmlMails(String from, String[] tos, String subject,
                              String html) {
        sendHtmlMails(from, tos, null, subject, html);
    }

    public void sendHtmlMails(final String from, final String[] tos,
                              final String[] ccs, final String subject, final String html) {
        Assert.hasText(from);
        Assert.notEmpty(tos);
        Assert.hasText(subject);
        Assert.hasText(html);
        pool.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MimeMessage mail = jms.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(
                            mail, true, "UTF-8");
                    messageHelper.setFrom(from);
                    messageHelper.setTo(tos);
                    if (ccs != null && ccs.length > 0)
                        messageHelper.setCc(ccs);
                    messageHelper.setSubject(subject);
                    messageHelper.setText(html, true);
                    jms.send(mail);
                } catch (Exception e) {
                    Log logger = LogFactory.getLog("Mail Service");
                    logger.error(e + ":" + e.getMessage(), e);
                }
            }
        }));
    }

}

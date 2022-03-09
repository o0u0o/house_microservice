package com.o0u0o.house.hsrv.service.impl;

import com.o0u0o.house.hsrv.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * <h1>邮箱业务实现类</h1>
 * @author o0u0o
 * @date 2022/3/9 9:05 PM
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单邮箱
     * @param subject
     * @param content
     * @param toEmail
     */
    @Override
    public void sendSimpleMail(String subject, String content, String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}

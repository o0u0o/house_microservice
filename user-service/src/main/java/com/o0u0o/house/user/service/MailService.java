package com.o0u0o.house.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author aiuiot
 * @Date 2020/1/13 10:34 上午
 * @Descripton: 邮件服务
 **/
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    //创建 发送邮件的来源 (在配置文件中)
    @Value("${spring.mail.username}")
    private String from;	//邮件来源


    public void sendSimpleMail(String subject,String content,String toEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

}

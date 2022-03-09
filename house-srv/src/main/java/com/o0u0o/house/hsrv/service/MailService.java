package com.o0u0o.house.hsrv.service;

/**
 * <h1>邮箱业务接口</h1>
 * @author o0u0o
 * @date 2022/3/9 9:03 PM
 */
public interface MailService {
    /**
     * 发送简单邮箱
     * @param subject
     * @param content
     * @param toEmail
     */
    void sendSimpleMail(String subject,String content,String toEmail);

}

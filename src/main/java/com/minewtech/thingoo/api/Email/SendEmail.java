package com.minewtech.thingoo.api.Email;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class SendEmail {
    public static final String HOST = "smtp.googlemail.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 465;
    public static final String FROM = "patienttracking23@gmail.com";//发件人的email
    public static final String PWD = "Agk_19614";//发件人密码

    /**
     * 发送邮件
     *
     * @param toEmail
     * @param content
     */
    public static void send(String toEmail, String content) throws GeneralSecurityException, MessagingException {
        Properties props = new Properties();
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", HOST);
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", PROTOCOL);

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        Message msg = new MimeMessage(session);
        msg.setSubject("Account activation email in the cloud");
        msg.setSentDate(new Date());
        msg.setText(content);
        msg.setFrom(new InternetAddress(FROM));

        Transport transport = session.getTransport();
        transport.connect(HOST, FROM, PWD);

        transport.sendMessage(msg, new Address[]{new InternetAddress(toEmail)});
        transport.close();
    }
}
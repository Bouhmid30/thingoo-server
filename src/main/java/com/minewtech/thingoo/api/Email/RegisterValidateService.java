package com.minewtech.thingoo.api.Email;

import com.minewtech.thingoo.util.Md5Util;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

public class RegisterValidateService {
    public static void  processregister(String email) {

        ///如果处于安全，可以将激活码处理的更复杂点，这里我稍做简单处理
        //user.setValidateCode(MD5Tool.MD5Encrypt(email));
        String validateCode = Md5Util.encode2hex(email);
        ///邮件的内容
        StringBuffer sb = new StringBuffer("Click the link below to activate the account, effective for 48 hours, otherwise re-register the account, the link can only be used once, please activate as soon as possible!：");
        sb.append("\r\n");
        sb.append("http://web-patienttracking.rizal23.com/#/activate?email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(validateCode);
        //发送邮件
        try {
            SendEmail.send(email, sb.toString());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("发送邮件");
    }
}

package cn.zxlee.znotifyapi.utils.mail.impl;

import cn.zxlee.znotifyapi.utils.mail.IMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @program: z-notify-api
 * @description: 邮件工具类
 * @author: zxlee
 * @create: 2022-12-01 22:04
 **/

@Component
public class MailUtilsImpl implements IMailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public boolean sendMail(String Subject, String content, String toMail) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 设置邮件标题
            mimeMessageHelper.setSubject(Subject);
            // 设置邮件内容
            mimeMessageHelper.setText(content, true);
            // 设置源邮箱
            mimeMessageHelper.setFrom(fromMail);
            // 设置目的邮箱
            mimeMessageHelper.setTo(toMail);
            // 发送邮件
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sendCheckCode(String checkCode, String toMail) {
        // 创建邮件正文
        Context context = new Context();
        context.setVariable("checkCode", checkCode);
        // 将模块引擎内容解析成html字符串
        String emailContent = templateEngine.process("checkCodeMail", context);
        return sendMail("Z-Notify验证码", emailContent, toMail);
    }
}

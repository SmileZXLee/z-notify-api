package cn.zxlee.znotifyapi.utils.mail;

public interface IMailUtils {
    boolean sendMail(String Subject, String content, String toMail);
    boolean sendCheckCode(String checkCode, String toMail);
}

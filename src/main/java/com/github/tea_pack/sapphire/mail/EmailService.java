package com.github.tea_pack.sapphire.mail;

public interface EmailService {

    String sendSimpleEmail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}

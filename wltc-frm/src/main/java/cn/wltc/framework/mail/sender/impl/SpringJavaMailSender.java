/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-6-28
 */
package cn.wltc.framework.mail.sender.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import cn.wltc.framework.mail.context.MailContext;
import cn.wltc.framework.mail.sender.AbstractMailSender;

/**
 * ����Java Mail��Spring�ʼ������ࡣ
 * 
 * <p>����Spring��Java Mail API��ʹ��ǰ��������Springע�� {@link org.springframework.mail.javamail.JavaMailSender}��
 * 
 * @author zhengdd
 * @version $Id: SpringJavaMailSender.java,v 0.1 2010-6-28 ����07:45:42 zhengdd Exp $
 */
public class SpringJavaMailSender extends AbstractMailSender {

    private static final String HTML_MAIL = "text/html";
    private JavaMailSender javaMailSender;

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    protected void doSend(MailContext mail) {
        if (mail == null) {
            throw new IllegalArgumentException("parameter mail can't be null");
        }
        if (getJavaMailSender() == null) {
            throw new IllegalArgumentException("javaMailSender can't be null");
        }

        MimeMessage message = createMimeMessage(mail);
        getJavaMailSender().send(message);
    }

    private MimeMessage createMimeMessage(MailContext mail) {
        MimeMessage message = getJavaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            if (mail.getFrom() != null) {
                helper.setFrom(mail.getFrom());
            }
            if (mail.getReplyTo() != null) {
                helper.setReplyTo(mail.getReplyTo());
            }
            helper.setTo(mail.getTo());
            if (mail.getCc() != null) {
                helper.setCc(mail.getCc());
            }
            if (mail.getBcc() != null) {
                helper.setBcc(mail.getBcc());
            }
            if (mail.getSubject() != null) {
                helper.setSubject(mail.getSubject());
            }
            if (mail.getText() != null) {
                if (isHtmlMail(mail.getContentType())) {
                    helper.setText(mail.getText(), true);
                } else {
                    helper.setText(mail.getText());
                }
            }
            if (mail.getSentDate() != null) {
                helper.setSentDate(mail.getSentDate());
            }
        } catch (MessagingException e) {
            logger.error("", e);
        }
        return message;
    }

    private boolean isHtmlMail(String contentType) {
        return HTML_MAIL.equalsIgnoreCase(contentType);
    }

}

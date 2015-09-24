/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-6-28
 */
package cn.wltc.framework.mail.sender;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wltc.framework.mail.context.MailContext;
import cn.wltc.framework.mail.render.MailRender;
import cn.wltc.framework.mail.render.impl.SimpleMailRender;

public abstract class AbstractMailSender implements MailSender {
    
    private static final Executor DEFAULT_EXECUTOR = Executors.newSingleThreadExecutor();
    
    private static final MailRender DEFAULT_MAIL_RENDER = new SimpleMailRender();
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    private boolean isAsynchronous = false;
    
    private Executor executor = DEFAULT_EXECUTOR;
    
    private MailRender mailRender = DEFAULT_MAIL_RENDER;
    
    protected boolean isAsynchronous() {
        return isAsynchronous;
    }

    public void setAsynchronous(boolean isAsynchronous) {
        this.isAsynchronous = isAsynchronous;
    }

    protected Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    protected MailRender getMailRender() {
        return mailRender;
    }

    public void setMailRender(MailRender mailRender) {
        this.mailRender = mailRender;
    }

    public void send(MailContext mail) {
        if (mail == null) {
            throw new IllegalArgumentException("parameter mail can't be null");
        }
        final MailContext renderedMail = doRender(mail);
        checkMail(mail);
        if (isAsynchronous()) {
            executor.execute(new Runnable() {
                public void run() {
                    doSend(renderedMail);
                }
            });
        } else {
            doSend(renderedMail);
        }
    }

    public void send(MailContext[] mails) {
        if (mails == null || mails.length == 0) {
            throw new IllegalArgumentException("parameter mail array can't be null or empty");
        }
        for (MailContext mail : mails) {
            send(mail);
        }
    }
    
    protected MailContext doRender(MailContext mail) {
        if (getMailRender() == null) {
            return mail;
        }
        MailContext renderedMail = mailRender.render(mail);
        return renderedMail;
    }
    
    protected void checkMail(MailContext mail) {
        if (mail != null && (mail.getTo() == null || mail.getTo().length == 0)) {
            throw new IllegalArgumentException("parameter mail to can't be null");
        }
    }

    protected abstract void doSend(MailContext mail);

}

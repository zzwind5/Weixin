/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-6-28
 */
package cn.wltc.framework.mail.sender;

import cn.wltc.framework.mail.context.MailContext;
import cn.wltc.framework.mail.exception.MailException;

public interface MailSender {
    
    public void send(MailContext mail) throws MailException;
    
    public void send(MailContext[] mails) throws MailException;

}

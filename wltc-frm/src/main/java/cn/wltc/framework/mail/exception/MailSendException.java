/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-7-6
 */
package cn.wltc.framework.mail.exception;

/**
 * �ʼ������쳣���̳��� {@link MailException} ��
 * 
 * <p>
 * ����Ϊ�ʼ������в�����쳣�ṩͳһ�Ľӿڡ�
 * 
 * @author zhengdd
 * @version $Id: MailSendException.java,v 0.1 2010-7-6 ����04:29:56 zhengdd Exp $
 */
public class MailSendException extends MailException {

    private static final long serialVersionUID = 4735425732029974285L;

    public MailSendException() {
        super();
    }

    public MailSendException(String message, Throwable cause) {
        super("send mail error, " + message, cause);
    }

    public MailSendException(String message) {
        super("send mail error, " + message);
    }

    public MailSendException(Throwable cause) {
        super(cause);
    }

}

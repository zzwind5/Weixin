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
 * �ʼ���Ⱦ�쳣���̳��� {@link MailException} ��
 * 
 * <p>
 * ����Ϊ�ʼ���Ⱦ�����������õ��ʼ�������ģ����Ⱦ���в�����쳣�ṩͳһ�ӿڡ�
 * 
 * @author zhengdd
 * @version $Id: MailRenderException.java,v 0.1 2010-7-6 ����04:24:33 zhengdd Exp $
 */
public class MailRenderException extends MailException {

    private static final long serialVersionUID = -3393220743503681700L;

    public MailRenderException() {
        super();
    }

    public MailRenderException(String message, Throwable cause) {
        super("render mail error, " + message, cause);
    }

    public MailRenderException(String message) {
        super("render mail error, " + message);
    }

    public MailRenderException(Throwable cause) {
        super(cause);
    }

}

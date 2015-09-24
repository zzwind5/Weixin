/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-7-5
 */
package cn.wltc.framework.mail.exception;

/**
 * �ʼ��쳣���̳��� {@link java.lang.RuntimeException} ��
 * 
 * <p>
 * ����Ϊ�ʼ����͡���Ⱦ�ṩ��ͳһ���쳣�ӿڣ������ʼ����͡���Ⱦ������׳��ĵײ��쳣��
 * Ӧ��ת��Ϊ������������࣬���ϲ���ýӿھ�����δ�������쳣��
 * 
 * @author zhengdd
 * @version $Id: MailException.java,v 0.1 2010-7-5 ����04:21:36 zhengdd Exp $
 */
public class MailException extends RuntimeException {

    private static final long serialVersionUID = -6167680496070147103L;

    public MailException() {
        super();
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailException(Throwable cause) {
        super(cause);
    }

}

/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-6-29
 */
package cn.wltc.framework.mail.template;

import java.util.Locale;

/**
 * �ʼ�ģ������ӿڣ�����ģ����Ϊĳ�������ʼ�ģ�塣
 * 
 * <p>�ýӿڽ�����ģ���������ʼ�ģ�����Ⱦ��
 * 
 * <p>������ģ��������Ǳ������У�Locale-Sensitive���ģ���ָ����ĳһģ�����ڲ�ͬ��
 * ���Ի����£���Ӧ��ͬ���԰汾��ģ�塣����Ӧ�þ����ܵ�ʵ�ִ˹��ܡ�
 * 
 * @author zhengdd
 * @version $Id: MailTemplateResolver.java,v 0.1 2010-6-29 ����09:40:03 zhengdd Exp $
 */
public interface MailTemplateResolver {
    
    /**
     * �����ʼ�ģ����ƣ����ģ����ͱ�����Ϣ��ȡĳ���ʼ�ģ���ʵ�֡�
     * @param templateName �ʼ�ģ�����
     * @param locale ������Ϣ
     * @return MailTemplate �ʼ�ģ��
     * @throws Exception �����в�����쳣
     */
    public MailTemplate resolveTemplateName(String templateName, Locale locale) throws Exception;

}

/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-6-28
 */
package cn.wltc.framework.mail.template;

import java.io.Writer;
import java.util.Map;

/**
 * �ʼ�ģ��ӿڣ��ṩ�ʼ�ģ�����Ⱦ��
 * 
 * <p>�ʼ�ģ��Ӧ�ð���ģ����ģ���ڲ�ʵ�ֺͱ�������ԣ���Щ������Ӧ�������а�
 * 
 * @author zhengdd
 * @version $Id: MailTemplate.java,v 0.1 2010-6-28 ����03:04:58 zhengdd Exp $
 */
public interface MailTemplate {
    
    /**
     * ��ݴ�������ģ�������ݣ���Ⱦ�ʼ�ģ�壬������Ⱦ���д���ַ�������С�
     * <p>�ַ�������ڴ󲿷������Ϊһ��{@link java.io.StringWriter}������Ϳ���ͨ��
     * {@link java.io.StringWriter#toString()}���������Ⱦ����ַ����ݡ�
     * @param model ���ģ��
     * @param writer �ַ������
     * @throws Exception ��Ⱦʱ���쳣
     */
    public void render(Map<String, ?> model, Writer writer) throws Exception;

}

/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-6-28
 */
package cn.wltc.framework.mail.render;

import cn.wltc.framework.mail.context.MailContext;

/**
 * �ʼ���Ⱦ�ӿڣ��ṩ��ݴ�����ʼ������ģ���Ⱦ�����ʼ�����������ġ�
 * 
 * <p>
 * �ýӿڵ����ʵ����ʽΪ��ʵ�֣���������ʼ��������Ѿ������з����ʼ��������Ϣ��
 * ��ӵ�ʵ��Ϊ������ʼ������Ľ���һ����ʶId��������Ϣ��Ϊ�������
 * 
 * <p>
 * ��ͨ�ʼ��ͻ��˿��Խ������ʼ�����һ��Ϊ���ı���HTML������HTML���ݣ�һ��ʹ��ģ���š�
 * ���⣬ģ���п��ܰ�һЩ��̬��ʾ�����ݣ���Ҫ��Ӧ��ҵ�����Ԥ�Ƚ���̬��ʾ���������
 * �����ʼ��������С�������������Ҫ���ʺϵ��ʼ�ģ�����������������ģ�����ݡ�
 * 
 * @author zhengdd
 * @version $Id: MailRender.java,v 0.1 2010-6-28 ����02:44:57 zhengdd Exp $
 */
public interface MailRender {
    
    /**
     * ��Ⱦ�ʼ������ġ�
     * @param mail �ʼ�������
     * @return MailContext ��Ⱦ����ʼ�������
     */
    public MailContext render(MailContext context);

}

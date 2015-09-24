package cn.wltc.framework.mail.render.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.wltc.framework.mail.context.MailContext;
import cn.wltc.framework.mail.exception.MailRenderException;
import cn.wltc.framework.mail.render.AbstractMailRender;
import cn.wltc.framework.mail.template.MailTemplate;

/**
 * Spring Bean�ʼ���Ⱦ�࣬
 * 
 * @author zhengdd
 * @version $Id: SpringBeanMailRender.java,v 0.1 2010-7-6 ����04:50:24 zhengdd Exp $
 */
public class SpringBeanMailRender extends AbstractMailRender implements ApplicationContextAware {

    /** �ʼ������ĳ����� */
    private final Map<String, MailContext> mailContextHolder = new HashMap<String, MailContext>();

    /** Spring��Ӧ�������� */
    private ApplicationContext             applicationContext;

    /** �Ƿ��ϸ�Ҫ���ʼ������ı���ΪSpring Bean���� */
    private boolean                        strict            = false;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initMailContexts();
    }

    /**
     * ��ʼ����������ΪSpring Bean���ʼ������ġ�
     */
    protected void initMailContexts() {
        Map<String, MailContext> mailContextBeans = applicationContext.getBeansOfType(MailContext.class);
        for (Entry<String, MailContext> mail : mailContextBeans.entrySet()) {
            mailContextHolder.put(mail.getKey(), mail.getValue());
        }
    }

    /**
     * �����Ƿ��ϸ�Ҫ���ʼ������ı���ΪSpring Bean���ã�Ĭ�ϡ�false����Ϊ���ϸ�Ҫ��
     * <p>
     * �������Ϊ��true��������ÿ���ʼ��������Ƿ�����ΪSpring Bean��
     * <p>
     * ��һЩ�����У���Ҫ��Ӧ�������е��ʼ�����ͳһ���ù��?��������Ϊ��true����
     */
    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    /**
     * ��ȡ�Ƿ��ϸ�Ҫ���ʼ������ı���ΪSpring Bean����
     */
    protected boolean isStrict() {
        return strict;
    }

    @Override
    protected MailContext doRender(MailContext mail) {
        if (isStrict()) {
            if (mail.getId() == null) {
                throw new MailRenderException("mail context id can't be null");
            }
            if (!mailContextHolder.containsKey(mail.getId())) {
                throw new MailRenderException("can't find the mail context bean, bean id: " + mail.getId());
            }
            mail = mailContextHolder.get(mail.getId());
        }

        if (StringUtils.isNotBlank(mail.getTemplate())) {
            if (getTemplateResolver() == null) {
                throw new MailRenderException("mail template resolver can't be null");
            }
            StringWriter sw = new StringWriter();
            try {
                MailTemplate template = getTemplateResolver().resolveTemplateName(mail.getTemplate(), null);
                template.render(mail.getModel(), sw);
            } catch (Exception e) {
                logger.error("", e);
            }
            mail.setText(sw.toString());
        }
        
        return mail;
    }

}

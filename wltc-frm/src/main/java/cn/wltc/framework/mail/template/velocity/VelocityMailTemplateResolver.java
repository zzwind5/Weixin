/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-7-1
 */
package cn.wltc.framework.mail.template.velocity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import cn.wltc.framework.mail.template.AbstractCachedMailTemplateResolver;
import cn.wltc.framework.mail.template.MailTemplate;

/**
 * ����Velocity���ʼ�ģ������࣬��һ��Velocity���ʼ�ģ��ʵ���ࡣ
 * 
 * <p>
 * ����ʵ����Velocity���ʼ�ģ���������Ҫ����Velocity Engine���ڽ�����
 * 
 * @author zhengdd
 * @version $Id: VelocityMailTemplateResolver.java,v 0.1 2010-7-1 ����08:48:25
 *          zhengdd Exp $
 */
public class VelocityMailTemplateResolver extends AbstractCachedMailTemplateResolver {
    
    private static final String DEFAULT_VELOCITY_PROPERTIES_PATH = "velocity.properties";
    
    /**
     * Ĭ�ϵ� {@link #setVelocityEngine(VelocityEngine) Velocity Engine}��
     */
    private static final VelocityEngine DEFAULT_VELOCITY_ENGINE;

    static {
        Properties pros = new Properties();
        try {
            InputStream is = VelocityMailTemplateResolver.class.getResourceAsStream(DEFAULT_VELOCITY_PROPERTIES_PATH);
            pros.load(is);
        } catch (IOException e) {
            throw new IllegalStateException("Could not load 'velocity.properties'", e);
        }
        if (pros.isEmpty()) {
            throw new IllegalStateException("velocity.properties can't be empty");
        }
        VelocityEngine engine;
        try {
            engine = new VelocityEngine(pros);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
        DEFAULT_VELOCITY_ENGINE = engine;
    }

    /** Velocity Engine */
    private VelocityEngine velocityEngine = DEFAULT_VELOCITY_ENGINE;

    /**
     * ����Velocity Engine��
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /**
     * ��ȡVelocity Engine��
     */
    protected VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    @Override
    protected MailTemplate loadTemplate(String templateName, Locale locale) throws Exception {
        VelocityMailTemplate mailTemplate = new VelocityMailTemplate(templateName);
        // TODO; ���������ʼ������
        return mailTemplate;
    }

    /**
     * ����Velocity���ʼ�ģ��ʵ���ࡣ
     */
    protected class VelocityMailTemplate extends AbstractMailTemplate {

        private Template template;

        public VelocityMailTemplate(String templateName) {
            this.name = templateName;
        }

        protected Template getTemplate() {
            Template template = null;
            try {
                template = getVelocityEngine().getTemplate(name);
            } catch (ResourceNotFoundException e) {
                logger.error("", e);
            } catch (ParseErrorException e) {
                logger.error("", e);
            } catch (Exception e) {
                logger.error("", e);
            }
            return template;
        }

        protected Template getTemplate(String name) {
            Template template = null;
            try {
                template = getVelocityEngine().getTemplate(name);
            } catch (ResourceNotFoundException e) {
                logger.error("", e);
            } catch (ParseErrorException e) {
                logger.error("", e);
            } catch (Exception e) {
                logger.error("", e);
            }
            return template;
        }

        protected Context createVelocityContext(Map<String, Object> model) throws Exception {
            return new VelocityContext(model);
        }

        protected void mergeTemplate(Context context, Writer writer) throws Exception {
            if (template == null) {
                template = getTemplate();
            }
            try {
                template.merge(context, writer);
            } catch (Exception ex) {
                // TODO;
                throw new Exception();
            }
        }

        @Override
        protected void mergeTemplateModel(Map<String, Object> model, Writer writer) throws Exception {
            Context velocityContext = createVelocityContext(model);
            mergeTemplate(velocityContext, writer);
        }

    }

}

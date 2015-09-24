/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-7-1
 */
package cn.wltc.framework.mail.template.velocity;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

import cn.wltc.framework.mail.template.MailTemplate;

/**
 * ����Velocity Layout/Screen�ṹ���ʼ�ģ������࣬��һ��Velocity Layout/Screen
 * �ṹ���ʼ�ģ��ʵ���ࡣ
 * 
 * @author zhengdd
 * @version $Id: VelocityLayoutMailTemplateResolver.java,v 0.1 2010-7-1 ����09:35:44 zhengdd Exp $
 */
public class VelocityLayoutMailTemplateResolver extends VelocityMailTemplateResolver {

    /** 
     * Ĭ�ϵ� {@link #setLayoutUrl(String) layout url}�� 
     */
    private static final String DEFAULT_LAYOUT_URL         = "layout.vm";

    /** 
     * Ĭ�ϵ� {@link #setLayoutKey(String) layout key}�� 
     */
    private static final String DEFAULT_LAYOUT_KEY         = "layout";

    /** 
     * Ĭ�ϵ� {@link #setScreenContentKey(String) screen content key}�� 
     */
    private static final String DEFAULT_SCREEN_CONTENT_KEY = "screen_content";

    private static final String DEFAULT_LAYOUT_PREFIX      = "layout/";

    private static final String DEFAULT_SCREEN_PREFIX      = "screen/";

    /** layout url */
    private String              layoutUrl                  = DEFAULT_LAYOUT_URL;

    private String              layoutKey                  = DEFAULT_LAYOUT_KEY;

    private String              screenContentKey           = DEFAULT_SCREEN_CONTENT_KEY;

    private String              layoutPrefix               = DEFAULT_LAYOUT_PREFIX;

    private String              screeanPrefix              = DEFAULT_SCREEN_PREFIX;

    /**
     * ����layoutģ��. Ĭ��Ϊ {@link #DEFAULT_LAYOUT_URL "layout.vm"}.
     * @param layoutUrl ģ���·������ģ��ĸ�·����
     */
    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
    }

    protected String getLayoutUrl() {
        return layoutUrl;
    }

    /**
     * Set the context key used to specify an alternate layout to be used instead
     * of the default layout. Screen content templates can override the layout
     * template that they wish to be wrapped with by setting this value in the
     * template, for example:<br>
     * <code>#set( $layout = "MyLayout.vm" )</code>
     * <p>Default key is {@link #DEFAULT_LAYOUT_KEY "layout"}, as illustrated above.
     * @param layoutKey the name of the key you wish to use in your
     * screen content templates to override the layout template
     */
    public void setLayoutKey(String layoutKey) {
        this.layoutKey = layoutKey;
    }

    protected String getLayoutKey() {
        return layoutKey;
    }

    /**
     * Set the name of the context key that will hold the content of
     * the screen within the layout template. This key must be present
     * in the layout template for the current screen to be rendered.
     * <p>Default is {@link #DEFAULT_SCREEN_CONTENT_KEY "screen_content"}:
     * accessed in VTL as <code>$screen_content</code>.
     * @param screenContentKey the name of the screen content key to use
     */
    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
    }

    protected String getScreenContentKey() {
        return screenContentKey;
    }

    protected String getLayoutPrefix() {
        return layoutPrefix;
    }

    public void setLayoutPrefix(String layoutPrefix) {
        this.layoutPrefix = layoutPrefix;
    }

    protected String getScreeanPrefix() {
        return screeanPrefix;
    }

    public void setScreeanPrefix(String screeanPrefix) {
        this.screeanPrefix = screeanPrefix;
    }
    
    @Override
    protected MailTemplate loadTemplate(String templateName, Locale locale) throws Exception {
        VelocityLayoutMailTemplate mailTemplate = new VelocityLayoutMailTemplate(templateName);
        return mailTemplate;
    }

    /**
     * ����layout/screen��ʽ��Velocity�ʼ�ģ��ʵ���ࡣ
     */
    protected class VelocityLayoutMailTemplate extends VelocityMailTemplate {

        public VelocityLayoutMailTemplate(String templateName) {
            super(getScreeanPrefix() + templateName);
        }
        
        @Override
        protected void mergeTemplate(Context context, Writer writer) throws Exception {
            renderScreenContent(context);

            // Velocity context now includes any mappings that were defined
            // (via #set) in screen content template.
            // The screen template can overrule the layout by doing
            // #set( $layout = "MyLayout.vm" )
            String layoutUrlToUse = (String) context.get(getLayoutKey());
            if (layoutUrlToUse != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Screen content template has requested layout [" + layoutUrlToUse + "]");
                }
            } else {
                // ���ָ��layout����ʹ��Ԥ�����layout
                layoutUrlToUse = getLayoutUrl();
            }

            try {
                getTemplate(getLayoutPrefix() + layoutUrlToUse).merge(context, writer);
            } catch (Exception ex) {
                // TODO;
                throw new Exception();
            }
        }

        private void renderScreenContent(Context velocityContext) throws Exception {
            if (logger.isDebugEnabled()) {
                logger.debug("Rendering screen content template [" + name + "]");
            }

            StringWriter sw = new StringWriter();
            Template screenContentTemplate = getTemplate();
            screenContentTemplate.merge(velocityContext, sw);

            velocityContext.put(getScreenContentKey(), sw.toString());
        }
    }

}

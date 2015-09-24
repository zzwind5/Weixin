/*
 * Hundsun Inc.
 * Copyright (c) 2006-2009 All Rights Reserved.
 *
 * Author     :zhengdd
 * Version    :1.0
 * Create Date:2010-6-30
 */
package cn.wltc.framework.mail.template;

import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �����ʼ�ģ����������࣬����һ���ʼ�ģ��ĳ���ʵ�֡�
 * 
 * <p>
 * �����ʼ�ģ���ǽ��ʼ�ģ����ת��Ϊĳ��ģ�弼��ʵ�ֵ�һ����̣����ʼ�ģ��һ��Ϊ����
 * �ϵ�ĳһ�ļ�����Ҫ����I/O�����������ṩ�˻����ʼ�ģ��Ĺ��ܣ����״ν����ɹ����ʼ�ģ �建�棬����֮���ٴε��ý�����I/O������
 * 
 * <p>
 * ���໹�ṩ������ȫ�����ԵĽӿڣ��������ͨ��ӿ�����ȫ�����ԣ���Щ����Ĭ�ϻᱻ�� ����Ⱦ�ʼ�ģ����������ģ���С�
 * 
 * <p>
 * �������һ��Ĭ�ϵ��ʼ�ģ�����ʵ�֡�
 * 
 * @author zhengdd
 * @version $Id: AbstractMailTemplateResolver.java,v 0.1 2010-6-30 ����09:33:28
 *          zhengdd Exp $
 */
public abstract class AbstractCachedMailTemplateResolver implements MailTemplateResolver {

    /** ��־ */
    protected final Log                     logger           = LogFactory.getLog(getClass());

    /** �ʼ�ģ�建��ӳ�� */
    private final Map<Object, MailTemplate> templateCache    = new HashMap<Object, MailTemplate>();

    /** �Ƿ񻺴��ʼ�ģ�� */
    private boolean                         cache            = true;

    /** ȫ������ */
    private Map<String, Object>             globalAttributes = new HashMap<String, Object>();

    /**
     * ����ȫ�����ԣ���Щ���Ի����ʼ�ģ����Ⱦ֮ǰ����ע�뵽��Ⱦ��������ģ���С�
     */
    public void setGlobalAttributes(Map<String, Object> globalAttributes) {
        addGlobalAttributes(globalAttributes);
    }

    public void addGlobalAttribute(String key, Object value) {
        if (this.globalAttributes.containsKey(key)) {
            logger.debug("a attribute will be overrided, the key: " + key);
        }
        this.globalAttributes.put(key, value);
    }

    public void addGlobalAttributes(Map<String, Object> globalAttributes) {
        for (Map.Entry<String, Object> attribute : globalAttributes.entrySet()) {
            if (this.globalAttributes.containsKey(attribute.getKey())) {
                logger.debug("a attribute will be overrided, the key: " + attribute.getKey());
            }
            this.globalAttributes.put(attribute.getKey(), attribute.getValue());
        }
    }

    /**
     * ����/�ر��ʼ�ģ�建�档
     * <p>
     * Ĭ��Ϊ "true"���������档
     * <p>
     * <b>ע�⣺�ر��ʼ�ģ�建��Ἣ���Ӱ�����ܣ�������ڿ������ߵ���ģʽ�¹رա�</b>
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /**
     * �����Ƿ����ʼ�ģ�建�档
     */
    public boolean isCache() {
        return this.cache;
    }

    /**
     * ��ݸ����ģ��������򷵻��ʼ�ģ�建��key��
     * <p>
     * Ĭ�Ϸ���Ϊ��ģ����������׺�ĵ��ַ�������Ը��Ǹ÷�����
     */
    protected Object getCacheKey(String templateName, Locale locale) {
        if (locale == null) {
            return templateName;
        } else {
            return templateName + "_" + locale;
        }
    }

    /**
     * �ṩ����ض��ʼ�ģ�建��Ĺ��ܡ�
     */
    public void removeFromCache(String templateName, Locale locale) {
        if (!this.cache) {
            logger.warn("View caching is SWITCHED OFF -- removal not necessary");
        } else {
            Object cacheKey = getCacheKey(templateName, locale);
            Object cachedView;
            synchronized (this.templateCache) {
                cachedView = this.templateCache.remove(cacheKey);
            }
            if (cachedView == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("No cached instance for view '" + cacheKey + "' was found");
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Cache for view " + cacheKey + " has been cleared");
                }
            }
        }
    }

    /**
     * ����ʼ�ģ�建�档
     */
    public void clearCache() {
        logger.debug("Clearing entire view cache");
        synchronized (this.templateCache) {
            this.templateCache.clear();
        }
    }

    public MailTemplate resolveTemplateName(String templateName, Locale locale) throws Exception {
        if (!isCache()) {
            return loadTemplate(templateName, locale);
        } else {
            Object cacheKey = getCacheKey(templateName, locale);
            synchronized (this.templateCache) {
                MailTemplate template = this.templateCache.get(cacheKey);
                if (template == null) {
                    template = loadTemplate(templateName, locale);
                    this.templateCache.put(cacheKey, template);
                }
                return template;
            }
        }
    }

    /**
     * �������ʵ�ָ÷����������ض���ģ�壬���ص�ģ�彫�ᱻ���档
     * <p>
     * ������Ժ��Դ���ı�����Ϣ������Ҫ��ǿ��ʵ�ֽ�������еı����������ԣ����� ���龡��ʵ�֡�
     * 
     * @param templateName
     *            �ʼ�ģ�����
     * @param locale
     *            ������Ϣ
     * @return the �ض���ģ��ʵ��
     * @throws Exception
     *             ��������в�����쳣
     */
    protected abstract MailTemplate loadTemplate(String templateName, Locale locale) throws Exception;

    /**
     * �ʼ�ģ������࣬�ṩ����ʵ���ض���ģ�弼����
     * <p>
     * {@link AbstractCachedMailTemplateResolver#globalAttributes}��������
     * {@link #render(Map, Writer)} �����ģ���С�
     */
    protected abstract class AbstractMailTemplate implements MailTemplate {

        protected String name;

        public void render(Map<String, ?> model, Writer writer) throws Exception {
            Map<String, Object> mergedModel = new HashMap<String, Object>(globalAttributes.size()
                                                                          + (model != null ? model.size() : 0));
            if (model != null) {
                mergedModel.putAll(model);
            }
            for (Entry<String, Object> attribute : globalAttributes.entrySet()) {
                if (mergedModel.containsKey(attribute.getKey())) {
                    logger.debug("a attribute will be overrided, key: " + attribute.getKey());
                }
                mergedModel.put(attribute.getKey(), attribute.getValue());
            }
            mergeTemplateModel(mergedModel, writer);
        }

        /**
         * �ϲ��ʼ�ģ�����ݶ��󣬽���Ⱦ���д���ַ�������У��������ʵ�ָ÷�����
         * 
         * @param model
         *            ��ݶ���
         * @param writer
         *            �ַ������
         * @throws Exception
         *             �ϲ�����д����ʱ������쳣
         */
        protected abstract void mergeTemplateModel(Map<String, Object> model, Writer writer) throws Exception;

    }

}

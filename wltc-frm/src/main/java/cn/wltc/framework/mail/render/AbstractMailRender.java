package cn.wltc.framework.mail.render;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wltc.framework.mail.context.MailContext;
import cn.wltc.framework.mail.template.MailTemplateResolver;

/**
 * �ʼ���Ⱦ������
 * 
 * @author zhengd
 * @version $Id: AbstractMailSender.java,v 0.1 2010-6-28 ����02:47:04 zhengdd Exp $
 */
public abstract class AbstractMailRender implements MailRender {
    
    /** ��־ */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private MailTemplateResolver templateResolver;

    public MailTemplateResolver getTemplateResolver() {
        return templateResolver;
    }

    public void setTemplateResolver(MailTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }

    public MailContext render(MailContext context) {
        return doRender(context);
    }
    
    protected abstract MailContext doRender(MailContext context);

}

package cn.wltc.framework.mail.render.impl;

import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;

import cn.wltc.framework.mail.context.MailContext;
import cn.wltc.framework.mail.render.AbstractMailRender;
import cn.wltc.framework.mail.template.MailTemplate;

/**
 * 
 * 
 * @author zhengdd
 * @version $Id: SimpleMailRender.java,v 0.1 2010-7-6 ����04:51:00 zhengdd Exp $
 */
public class SimpleMailRender extends AbstractMailRender {

    @Override
    protected MailContext doRender(MailContext mail) {
        if (StringUtils.isNotBlank(mail.getTemplate())) {
            if (getTemplateResolver() == null) {
                throw new IllegalArgumentException("mail template resolver can't be null");
            }
            StringWriter sw = new StringWriter();
            try {
                MailTemplate template = getTemplateResolver().resolveTemplateName(mail.getTemplate(), null);           
                template.render(mail.getModel(), sw);
            } catch (Exception e) {
                logger.error("",e);
            }
            mail.setText(sw.toString());
        }
        return mail;
    }

}

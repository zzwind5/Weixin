package cn.wltc.framework.util.digest;

/**
 * ��ϢժҪ�ӿ�
 * 
 * @author zhengdd
 * @version $Id: MessageDigest.java,v 0.1 2010-6-10 ����05:03:53 zhengdd Exp $
 */
public interface MessageDigest {

    /**
     * ��������Ϣ������ϢժҪ����,ʹ��UTF-8����
     * 
     * @param text ������Ϣ
     * @return String ��ϢժҪ
     */
    public String digest(String text);

    /**
     * ��������Ϣ������ϢժҪ����,ʹ��ָ���ַ����
     *  
     * @param text ������Ϣ
     * @param encoding �ַ����
     * @return ��ϢժҪ
     */
    public String digest(String text, String encoding);

}

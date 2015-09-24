package cn.wltc.framework.web.cookyjar;

/**
 * ���������ܰ��Լ��־ó�String�����Լ���String�������ֽ�������
 * 
 * @author fish
 * 
 */
public interface SelfDependence {
	public String lieDown();

	public SelfDependence riseUp(String value);
}

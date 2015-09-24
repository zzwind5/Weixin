package cn.wltc.framework.util.captcha;
import java.util.Random;

import jj.play.ns.nl.captcha.text.producer.TextProducer;

/**
 * 
 * @author dell
 *
 */
public class EasyCharTextProducer implements TextProducer {

	private static final char[] chars = { 'a', 'c', 'd', 'e', 'f', 'h', 'j',
			'k', 'm', 'n', 'p', 'r', 's', 't', 'w', 'x', 'y', '3',
			'4', '5', '7', '8'};

	private static final int lastChar = chars.length;

	private int length;

	public EasyCharTextProducer() {
		this(5);
	}

	public EasyCharTextProducer(int length) {
		super();
		this.length = length;
	}

	public String getText() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int r = random.nextInt(lastChar);
			sb.append(chars[r]);
		}
		return sb.toString();
	}
}

package cn.wltc.biz.weixin.biz;

import java.util.Random;

public class ScratchCard {
	
	
	public static int getRandomNumber(){
		Random rand = new Random();
		return rand.nextInt(10000);
	}
	
	/***
	 * 返回显示消息内容
	 * @return
	 */
	public String showMsgContent(){
		int num = getRandomNumber();
		if(num % 20 == 0){
			return "请到竞猜页面刮取奖品：\n <a href=\"http://www.zfsoft.com/wxpt/scratchCard/2.html\">刮奖区</a>";
		}else if (num % 21 == 0){
			return "请到竞猜页面刮取奖品：\n <a href=\"http://www.zfsoft.com/wxpt/scratchCard/1.html\">刮奖区</a>";
		}else{
			return "请到竞猜页面刮取奖品：\n <a href=\"http://www.zfsoft.com/wxpt/scratchCard/3.html\">刮奖区</a>";
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getRandomNumber());
		}
	}
}

package cn.wltc.biz.weixin.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wltc.biz.weixin.entity.menu.Button;
import cn.wltc.biz.weixin.entity.menu.ClickButton;
import cn.wltc.biz.weixin.entity.menu.ComplexButton;
import cn.wltc.biz.weixin.entity.menu.Menu;
import cn.wltc.biz.weixin.entity.menu.ViewButton;
import cn.wltc.biz.weixin.entity.token.Token;
import cn.wltc.biz.weixin.util.CommonUtil;
import cn.wltc.biz.weixin.util.MenuUtil;

public class MenuManager {
	private static final Log logger = LogFactory.getLog(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {		
		//------------------个人中心----------------------//
		ViewButton userCenterBt_my56 = new ViewButton();
		userCenterBt_my56.setName("一键上网");
		userCenterBt_my56.setUrl( "http://www.commsky.com.cn/?t=wechat_auth_redirect.php");
		
		
		ComplexButton userCenterBt = new ComplexButton();
		userCenterBt.setName("无线测试");
		userCenterBt.setSub_button(new Button[] { userCenterBt_my56});
		
	
		
		ViewButton wltcNewsBt = new ViewButton();
		wltcNewsBt.setName("关于我们");
		wltcNewsBt.setUrl( "http://commsky.com.cn/about.html");

		ComplexButton newCenterBt = new ComplexButton();
		newCenterBt.setName("康凯科技");
		newCenterBt.setSub_button(new Button[] { wltcNewsBt });
		
		Menu menu = new Menu();
		menu.setButton(new Button[] { userCenterBt, newCenterBt });

		return menu;
	}

	public static void main(String[] args) {
//      System.getProperties().setProperty( "http.proxyHost", "10.71.19.195" );
//
//      System.getProperties().setProperty( "http.proxyPort","3125" );
//      
//      System.getProperties().setProperty( "https.proxyHost", "10.71.19.195" );
//
//      System.getProperties().setProperty( "https.proxyPort","3125" );      
		// 调用接口获取凭证 
		Token token = CommonUtil.getToken(CommonUtil.APPID, CommonUtil.APPSECRET);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				logger.info("菜单创建成功！");
			else
				logger.info("菜单创建失败！");
		}
	}
}

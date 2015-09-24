package cn.wltc.weixin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.wltc.biz.weixin.entity.user.SNSUserInfo;
import cn.wltc.framework.web.editor.AllDateFormat;

@Controller
@RequestMapping("/snsuser")
public class SNSUserInfoController {
	private final String VIEW_BASE =  "/snsuser/";
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(AllDateFormat.instance, true));  
	}
	
	/** 列表 */
	@RequestMapping(value="/userinfo")
	public String list(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		return VIEW_BASE+"userinfo";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String oauth(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		
		SNSUserInfo snsUserInfo = new SNSUserInfo();
		snsUserInfo.setCity("杭州");
		snsUserInfo.setCountry("中国");
		snsUserInfo.setNickname("Chris");
		snsUserInfo.setOpenId("chrishehui");
		snsUserInfo.setProvince("浙江");
		snsUserInfo.setHeadImgUrl("http://wx.qlogo.cn/mmopen/ajNVdqHZLLDdNhpicPfPcefRcUWdevTvcSMCoKvHeolyOaOpOBzqANWzSSmqeichvPH62z4X6q2vVGvcr5gP9SicQ/0?r=2111646999");
		model.addAttribute("snsUserInfo", snsUserInfo);
		return VIEW_BASE+"userinfo"; 

//		// 用户同意授权后，能获取到code
//		String code = request.getParameter("code");
//
//		// 用户同意授权
//		if (!"authdeny".equals(code)) {
//			// 获取网页授权access_token
//			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(code);
//			// 网页授权接口访问凭证
//			String accessToken = weixinOauth2Token.getAccessToken();
//			// 用户标识
//			String openId = weixinOauth2Token.getOpenId();
//			// 获取用户信息
//			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
//
//			model.addAttribute("snsUserInfo", snsUserInfo);
//			return VIEW_BASE+"userinfo"; 
//		}
//		
//		return "";
	}
}

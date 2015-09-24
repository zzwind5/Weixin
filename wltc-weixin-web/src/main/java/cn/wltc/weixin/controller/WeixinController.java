package cn.wltc.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.wltc.biz.weixin.service.CoreService;
import cn.wltc.biz.weixin.util.SignUtil;

@RequestMapping(value="/weixin")
@Controller
public class WeixinController {

	@Autowired
	private CoreService coreService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void validate(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		PrintWriter writer  = response.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			writer.print( request.getParameter("echostr"));
		}
		writer.close();
		writer = null;
	}

	@RequestMapping(method = RequestMethod.POST,produces="application/xml" )
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		PrintWriter writer  = response.getWriter();
		// 请求校验
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// 调用核心服务类接收处理请求
			String respXml = coreService.processRequest(request);
			writer.print(respXml);
		}
		writer.close();
		writer = null;
	}
}

package cn.wltc.weixin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wltc.biz.weixin.biz.bo.WeixinNews;
import cn.wltc.biz.weixin.biz.service.WeixinNewsService;
import cn.wltc.framework.web.editor.AllDateFormat;

@RequestMapping(value = "/news")
@Controller
public class NewsController {

	@Autowired
	private WeixinNewsService weixinNewsService;

	private final String VIEW_BASE = "/news/";

	/** binder用于bean属性的设置 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				AllDateFormat.instance, true));
	}

	/** 新闻详细 */
	@RequestMapping(value = "/detail/{newsId}")
	public String list(ModelMap model, @PathVariable("newsId") Integer newsId,
			HttpServletRequest request, HttpServletResponse response) {

		WeixinNews weixinNews = weixinNewsService.getById(newsId);

		model.addAttribute("newsInfo", weixinNews);
		return VIEW_BASE + "detail";
	}
}

package cn.wltc.weixin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.biz.wxmgr.model.CollectedLine;
import cn.wltc.biz.wxmgr.model.Orgzanation;
import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.biz.wxmgr.service.CollectedLineService;
import cn.wltc.biz.wxmgr.service.OrgzanationService;
import cn.wltc.biz.wxmgr.service.RoadLineService;
import cn.wltc.framework.service.page.Page;
import cn.wltc.weixin.JsonResp;

@Controller
@RequestMapping("/roadline")
public class RoadLineController {

	@Autowired
	private CollectedLineService collectedLineService;

	@Autowired
	private RoadLineService roadLineService;

	@Autowired
	private OrgzanationService orgzanationService;

	private final String VIEW_BASE = "/roadline/";

	/** 列表 */
	@RequestMapping(value = "/main")
	public String main(ModelMap model, @ModelAttribute("query") RoadLine query,
			HttpServletRequest request, HttpServletResponse response) {
		return VIEW_BASE + "main";
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap model, RoadLine query,
			HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("query", query);
		return VIEW_BASE + "list";
	}

	@RequestMapping(value = "/page")
	public String page(ModelMap model, RoadLine query,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("RoadLineController.page...."  + query.getStart());
		String start = query.getStart();
		query.setStart(null);
		String end = query.getEnd();
		query.setEnd(null);
		CustomerInfo user = (CustomerInfo) request.getSession().getAttribute("user");
		query.setCustomerId(user.getCustomer_id());
		if (start != null) {
			String[] startALL = start.split("-");
			if (startALL != null && startALL.length > 0) {
				query.setStartProvince(startALL[0]);
			}
			if (startALL != null && startALL.length > 1) {
				// query.setStartCity(startALL[1]);
			}
			if (startALL != null && startALL.length > 2) {
				// query.setStart(startALL[2]);
			}
		}
		if (end != null) {
			String[] endALL = end.split("-");
			if (endALL != null && endALL.length > 0) {
				query.setEndProvince(endALL[0]);
			}
			if (endALL != null && endALL.length > 1) {
				// query.setEndCity(endALL[1]);
			}
			if (endALL != null && endALL.length > 2) {
				// query.setEnd(endALL[2]);
			}
		}
		Page<RoadLine> page = roadLineService.findPageLine(query);
		model.addAttribute("page", page);
		return VIEW_BASE + "page";
	}

	@RequestMapping(value = "/dofav")
	@ResponseBody
	public JsonResp dofav(ModelMap model, Integer lineId,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerInfo user = (CustomerInfo) request.getSession().getAttribute(
				"user");
		CollectedLine collectedLine = new CollectedLine();
		collectedLine.setCustomerId(user.getCustomer_id());
		collectedLine.setLineId(lineId);
		List<CollectedLine> list = collectedLineService.find(collectedLine);
		if (list.size() > 0) {
			return new JsonResp(false, "1", "", null);
		} else {
			collectedLineService.save(collectedLine);
			return JsonResp.Success;
		}
	}

	@RequestMapping(value = "/doRemovefav")
	@ResponseBody
	public JsonResp doRemovefav(ModelMap model, Integer lineId,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerInfo user = (CustomerInfo) request.getSession().getAttribute(
				"user");
		CollectedLine collectedLine = new CollectedLine();
		collectedLine.setCustomerId(user.getCustomer_id());
		collectedLine.setLineId(lineId);
		List<CollectedLine> list = collectedLineService.find(collectedLine);
		if (list.size() == 0) {
			return new JsonResp(false, "1", "", null);
		} else {
			collectedLineService.removeById(collectedLine);
			return JsonResp.Success;
		}
	}

	/** 详细 */
	@RequestMapping(value = "/detail")
	public String detail(ModelMap model, Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		RoadLine roadline = roadLineService.getById(id);
		Orgzanation orgzanation = orgzanationService.getById(roadline
				.getOrgId());
		CustomerInfo user = (CustomerInfo) request.getSession().getAttribute(
				"user");
		CollectedLine collectedLine = new CollectedLine();
		collectedLine.setCustomerId(user.getCustomer_id());
		collectedLine.setLineId(id);
		CollectedLine collectedLinedb = collectedLineService
				.getById(collectedLine);

		model.addAttribute("collectedLine", collectedLinedb);
		model.addAttribute("roadline", roadline);
		model.addAttribute("orgzanation", orgzanation);
		return VIEW_BASE + "detail";
	}
}

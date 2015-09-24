package cn.wltc.weixin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wltc.biz.adcd.model.BaseAdcd;
import cn.wltc.biz.adcd.service.BaseAdcdService;
import cn.wltc.framework.web.editor.AllDateFormat;
import cn.wltc.weixin.CityDataResp;

@RequestMapping(value = "/adcd")
@Controller
public class AdcdController {
    @Autowired
    private BaseAdcdService baseAdcdService;

    private final String    VIEW_BASE = "/adcd/";

    /** binder用于bean属性的设置 */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(AllDateFormat.instance, true));
    }

    @RequestMapping(value = "/loadCityData")
    @ResponseBody
    public CityDataResp getCoupon(ModelMap model, String param, HttpServletRequest request, HttpServletResponse response) {
        String[] params = param.split("_");
        String level = "1";
        BaseAdcd baseAdcd = new BaseAdcd();
        if (params[1].endsWith("0000")) {
            baseAdcd.setAdcd(params[1].substring(0, 2) + "%00");
            level = "2";
        } else if (params[1].endsWith("00")) {
            baseAdcd.setAdcd(params[1].substring(0, 4) + "%");
            level = "3";
        }
        List<BaseAdcd> list = baseAdcdService.find(baseAdcd);
        List<HashMap<String,String>> list2 = new ArrayList<HashMap<String,String>>();
        for (BaseAdcd one : list){
            if (one.getAdcd().equals(params[1])){
                continue;
            }
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("name", one.getAdnm());
            map.put("code", one.getAdcd());
            map.put("level", level);
            list2.add(map);
        }
        
        return new CityDataResp(true,null,"获取信息成功",list2);
    }
}

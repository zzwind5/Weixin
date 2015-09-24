package cn.wltc.biz.weixin.biz.bo;

import cn.wltc.framework.service.BaseQuery;


public class WeixinNewsGroup extends BaseQuery {

    private static final long serialVersionUID = 3674966416767634534L;
    private Integer group_id;
    private String section;
    private String news_ids;
    private String status;


    public Integer getGroup_id() {
        return group_id;
    }


    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }


    public String getSection() {
        return section;
    }


    public void setSection(String section) {
        this.section = section;
    }


    public String getNews_ids() {
        return news_ids;
    }


    public void setNews_ids(String news_ids) {
        this.news_ids = news_ids;
    }


    
    public String getStatus() {
        return status;
    }


    
    public void setStatus(String status) {
        this.status = status;
    }



}

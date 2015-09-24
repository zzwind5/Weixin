package cn.wltc.biz.weixin.biz.bo;

import java.util.Date;
import java.util.List;

import cn.wltc.framework.service.BaseQuery;

public class WeixinNews extends BaseQuery {

    private static final long serialVersionUID = -7489555233204101647L;

    private Integer           news_id;
    private String            news_url;
    private String            is_main_news;
    private String            section;
    private String            title;
    private String            description;
    private String            image_url;
    private String            content;
    private String            status;
    private Date              update_time;
    private Integer[]         news_ids;

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }

    public String getIs_main_news() {
        return is_main_news;
    }

    public void setIs_main_news(String is_main_news) {
        this.is_main_news = is_main_news;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    
    public Integer[] getNews_ids() {
        return news_ids;
    }

    
    public void setNews_ids(Integer[] news_ids) {
        this.news_ids = news_ids;
    }

}

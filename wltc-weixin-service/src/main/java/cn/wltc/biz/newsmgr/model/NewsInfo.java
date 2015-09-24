package cn.wltc.biz.newsmgr.model;

import java.util.Date;

import cn.wltc.framework.service.BaseQuery;

public class NewsInfo extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Integer news_id;
	public String news_url;
	public String is_main_news;
	public String section;
	public String title;
	public String description;
	public String image_url;
	public String content;
	public String status;
	public Date update_time;
	
	public Integer group_id;
	public String group_name;
	public String news_ids;
	public Integer sx;
	public Date add_time;
	public String flag;

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

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getNews_ids() {
		return news_ids;
	}

	public void setNews_ids(String news_ids) {
		this.news_ids = news_ids;
	}

	public Integer getSx() {
		return sx;
	}

	public void setSx(Integer sx) {
		this.sx = sx;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}

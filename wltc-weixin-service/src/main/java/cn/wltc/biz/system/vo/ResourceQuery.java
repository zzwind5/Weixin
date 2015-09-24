package cn.wltc.biz.system.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.wltc.framework.service.BaseQuery;


public class ResourceQuery extends BaseQuery {

    private static final long serialVersionUID = 7566081688781869159L;

    public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


package cn.wltc.biz.system.vo;

import cn.wltc.biz.system.model.SysLog;

public class LogQuery extends SysLog {

    /**
     * 
     */
    private static final long serialVersionUID = 1686199482026389288L;
    private String            czsjStart;
    private String            czsjEnd;

    public String getCzsjStart() {
        return czsjStart;
    }

    public void setCzsjStart(String czsjStart) {
        this.czsjStart = czsjStart;
    }

    public String getCzsjEnd() {
        return czsjEnd;
    }

    public void setCzsjEnd(String czsjEnd) {
        this.czsjEnd = czsjEnd;
    }

}

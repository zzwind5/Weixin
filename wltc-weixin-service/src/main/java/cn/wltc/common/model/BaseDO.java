package cn.wltc.common.model;

import java.io.Serializable;
import java.util.Date;

public class BaseDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1223663304791640839L;
    private Long              id;
    private Date              gmtCreate;
    private Date              gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}

package cn.wltc.common.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wltc.framework.service.BaseEntity;

public abstract class BaseTreeObject extends BaseEntity {

    private static final long    serialVersionUID = -1716615585541861801L;
    public static String         STATE_OPEN       = "open";
    public static String         STATE_CLOSED     = "closed";
    private Integer                 id;
    private Integer                 key;
    private String title;
    private Integer                 fid;
    private int                  order;
    private Boolean              leaf;
    private Boolean              isFolder;
    private boolean              expand;
    private List<BaseTreeObject> children;
    private Map<String, Object>  attributes       = new HashMap<String, Object>();

    private boolean              select;
    private String               state            = STATE_OPEN;
    private String               icon;
    private String               nodecode;
    

    public String getNodecode() {
        return nodecode;
    }

    public void setNodecode(String nodecode) {
        this.nodecode = nodecode;
    }

    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Boolean getLeaf() {
        return leaf;
    }

    
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }


    public Boolean getIsFolder() {
        return isFolder;
    }

    
    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getFid() {
        return fid;
    }
    
    public void setFid(Integer fid) {
        this.fid = fid;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    public boolean isExpand() {
        return expand;
    }


    public void setExpand(boolean expand) {
        this.expand = expand;
    }


    public void setCollapsed(boolean collapsed) {
        this.expand = !collapsed;
    }

    public boolean isCollapsed() {
        return !expand;
    }

    public void setChildren(List<BaseTreeObject> children) {
        this.children = children;
    }

    public List<BaseTreeObject> getChildren() {
        return children;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public final void putAttributes(String key, Object value) {
        attributes.put(key, value);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    public boolean isSelect() {
        return select;
    }

    
    public void setSelect(boolean select) {
        this.select = select;
    }


    
    public Integer getId() {
        return id;
    }


    
    public void setId(Integer id) {
        this.id = id;
    }

}

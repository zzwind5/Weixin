package cn.wltc.common.model;


public class BaseSeries {
    private String name;
    private Object pointStart;
    private String type = "line";
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Object getPointStart() {
        return pointStart;
    }
    public void setPointStart(Object pointStart) {
        this.pointStart = pointStart;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

}

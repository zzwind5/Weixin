package cn.wltc.common.web.util;

import java.util.List;

import cn.wltc.common.model.BaseTreeObject;


public class TreeModelSupport {

    public static <T extends BaseTreeObject> T getTreeObjectByCode(List<T> roots, String code) {
        if (code == null) {
            throw new IllegalArgumentException();
        }
        if (roots != null && roots.size() > 0) {
            for (T root : roots) {
                if (code.equals(root.getNodecode())) {
                    return root;
                }
                T t = (T) getTreeObjectByCode(root.getChildren(), code);
                if (t != null) {
                    return t;
                }
            }
        }
        return null;
    }
}

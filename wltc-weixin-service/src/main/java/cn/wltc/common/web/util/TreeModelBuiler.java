package cn.wltc.common.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.beans.support.SortDefinition;

import cn.wltc.common.model.BaseTreeObject;

public final class TreeModelBuiler {

    public static <T extends BaseTreeObject> List<T> builTree(List<T> data) {
        return builTree(data, null);
    }

    public static <T extends BaseTreeObject> List<T> builTree(List<T> data, List<Integer> defaultChecked) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        SortDefinition sortDefinition = new MutableSortDefinition("px", true, true);
        PropertyComparator.sort(data, sortDefinition);

        Map<Integer, T> nodeMap = new HashMap<Integer, T>();
        Iterator<T> iterator = data.iterator();
        while (iterator.hasNext()) {
            T node = iterator.next();
            node.setKey(node.getId());
            nodeMap.put(node.getId(), node);
        }

        List<T> roots = new ArrayList<T>();
        iterator = data.iterator();
        while (iterator.hasNext()) {
            T node = iterator.next();
            node.setSelect(contains(node.getId(), defaultChecked));

            T parent = null;
            if (node.getFid() != null) {
                parent = nodeMap.get(node.getFid());
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<BaseTreeObject>());
                    }
                    parent.getChildren().add(node);
                }
            }

            if (parent != null) {
                parent.setExpand(true);
            }
            if (parent == null) {
                roots.add(node);
            }
        }
        
        iterator = data.iterator();
        while (iterator.hasNext()) {
            T node = iterator.next();
            if (node.getChildren() != null && node.getChildren().size() > 0){
                node.setIsFolder(true);
            } else {
                node.setLeaf(true);
            }
        }
        return roots;
    }

    private static boolean contains(Integer id, List<Integer> defaultChecked) {
        return defaultChecked == null ? false : defaultChecked.contains(id);
    }
}

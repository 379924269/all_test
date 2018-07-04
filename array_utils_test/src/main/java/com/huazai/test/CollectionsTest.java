package com.huazai.test;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.*;

/**
 * @author 华仔
 * @date 2018/6/25 16:04
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("6");
        list1.add("7");
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("5");
        Collection c = CollectionUtils.retainAll(list1, list2);
        System.out.println(c);
        System.out.println(ListUtils.union(list1, list2));

        Map map = new HashMap();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", "你好");
        System.out.println(map);
        System.out.println(MapUtils.invertMap(map));
        System.out.println(MapUtils.getInteger(map, "6"));

    }


}

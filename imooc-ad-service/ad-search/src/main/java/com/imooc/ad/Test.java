package com.imooc.ad;

import com.imooc.ad.utils.CommonUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Test {
  public static void main(String[] args) {
    Map<Integer, String> unitDistrict = new HashMap<Integer, String>();
    unitDistrict.put(1, "宝马");
    unitDistrict.put(2, "奥迪");
    Set<String> brands = new HashSet<String>();
    brands.add("丰田");
    brands.add("本田");
    brands.add("奥迪");
    System.out.println(unitDistrict.toString());
  }

  public static String factory(String key) {
    return key + "这是新添加的key";
  }
}

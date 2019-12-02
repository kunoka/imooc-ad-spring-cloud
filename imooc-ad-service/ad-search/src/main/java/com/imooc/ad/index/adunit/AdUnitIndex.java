package com.imooc.ad.index.adunit;

import com.imooc.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class AdUnitIndex implements IndexAware <Long, AdUnitObject>{
  private static Map<Long, AdUnitObject> objectMap;
  static {
    objectMap = new ConcurrentHashMap<Long, AdUnitObject>();
  }
  @Override
  public AdUnitObject get(Long key) {
    return objectMap.get(key);
  }

  @Override
  public void add(Long key, AdUnitObject value) {
    log.info("add before: {}", objectMap);
    objectMap.put(key, value);
    log.info("add after:{}", objectMap);
  }

  @Override
  public void update(Long key, AdUnitObject value) {
    log.info("before update: {}", objectMap);
    AdUnitObject oldUnitObject = objectMap.get(key);
    if(oldUnitObject == null){
      objectMap.put(key, value);
    }else{
      oldUnitObject.update(value);
    }
    log.info("after update: {}", objectMap);
  }

  @Override
  public void delete(Long key, AdUnitObject value) {
    log.info("before delete:{}", objectMap);
    objectMap.remove(key);
    log.info("after delete:{}", objectMap);
  }
}

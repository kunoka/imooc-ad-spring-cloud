package com.imooc.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.imooc.ad.mysql.TemplateHolder;
import com.imooc.ad.mysql.dto.BinlogRowData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AggregationListener implements BinaryLogClient.EventListener {
  private String dbName;
  private String tableName;

  private Map<String, Ilistener> listenerMap = new HashMap<>();
  private final TemplateHolder templateHolder;

  public AggregationListener(TemplateHolder templateHolder) {
    this.templateHolder = templateHolder;
  }

  private String genKey(String dbName, String tableName) {
    return dbName + ":" + tableName;
  }

  public void register(String _dbName, String _tableName,
                       Ilistener ilistener) {
    log.info("register: {}-{}", _dbName, _tableName);
    this.listenerMap.put(genKey(_dbName, _tableName), ilistener)
  }

  @Override
  public void onEvent(Event event) {
    EventType type = event.getHeader().getEventType();
    log.debug("event type: {}", type);
    if (type == EventType.TABLE_MAP) {
      TableMapEventData data = event.getData();
      this.tableName = data.getTable();
      this.dbName = data.getDatabase();
      return;
    }
    if (type != EventType.EXT_UPDATE_ROWS
      && type != EventType.EXT_WRITE_ROWS
      && type != EventType.EXT_DELETE_ROWS) {
      return;
    }
    // 表名和库名是否已经完成填充
    if (StringUtils.isEmpty(dbName) || StringUtils.isEmpty(tableName)) {
      log.error("no meta data event");
      return;
    }
    // 找出对应表有兴趣的监听器
    String key = genKey(this.dbName, this.tableName);
    Ilistener listener = this.listenerMap.get(key);
    if (null == listener) {
      log.debug("skip {}", key);
      return;
    }
    log.info("trigger event: {}", type.name());
    try {
      BinlogRowData rowData = buildRowData(event.getData());
      if (rowData == null) {
        return;
      }
      rowData.setEventType(type)
      listener.onEvent(rowData);
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error(ex.getMessage());
    } finally {
      this.dbName = "";
      this.tableName = "";
    }
  }

  private BinlogRowData buildRowData(EventData eventData) {
    return null;
  }
}

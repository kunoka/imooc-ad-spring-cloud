package com.imooc.ad.runner;

import com.imooc.ad.mysql.BinlogClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//程序启动时自动运行的一段程序 放到runner文件夹里
@Slf4j
@Component
public class BinlogRunner implements CommandLineRunner {
  private final BinlogClient client;

  public BinlogRunner(BinlogClient client) {
    this.client = client;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Coming in BinlogRunner...");
    client.connect();
  }
}

package com.learning.controller;


import com.learning.listener.StockUpdateObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class StockController {

  //example for emitter
//  @GetMapping(value = "/startEmit",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//  public SseEmitter startEmit() {
//    //to set timeout we can use new SseEmitter(2000L);
//    SseEmitter emitter = new SseEmitter();
//    Runnable runnable = () -> {
//      for (int i = 1; i <= 10; i++) {
//        try {
//          emitter.send(i);
//          Thread.sleep(1000);
//        } catch (Exception e) {
//          throw new RuntimeException(e);
//        }
//      }
//      emitter.complete();
//    };
//    Thread thread = new Thread(runnable);
//    thread.start();
//    return emitter;
//  }

  @Autowired
  StockUpdateObserver stockUpdateObserver;

  @GetMapping(value = "/stockUpdates",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter getStockUpdates() {
    return stockUpdateObserver.createEmit();
  }

}

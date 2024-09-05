package com.learning.listener;

import com.learning.stock.PriceUpdate;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.emitter.Emitter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class StockUpdateObserver implements StreamObserver<PriceUpdate> {

  Set<SseEmitter> emitters = Collections.synchronizedSet(new HashSet<>());

  public SseEmitter createEmit(){
    SseEmitter emitter = new SseEmitter();
    emitters.add(emitter);
    emitter.onError(ex -> this.emitters.remove(emitter));
    return emitter;
  }

  @Override
  public void onNext(PriceUpdate priceUpdate) {
    com.learning.domain.PriceUpdate dto = new com.learning.domain.PriceUpdate(priceUpdate.getTicker().toString(), priceUpdate.getPrice());
    this.emitters.removeIf(e -> !this.send(e, dto));
  }

  @Override
  public void onError(Throwable throwable) {
    System.out.println("streaming error "+ throwable);
    this.emitters.forEach(e -> e.completeWithError(throwable));
    this.emitters.clear();
  }

  @Override
  public void onCompleted() {
    this.emitters.forEach(ResponseBodyEmitter::complete);
    this.emitters.clear();
  }

  private boolean send(SseEmitter emitter, Object o){
    try {
      emitter.send(o);
      return true;
    } catch (IOException e) {
      System.out.println("sse error "+ e.getMessage());
      return false;
    }
  }
}

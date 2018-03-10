package com.github.pwittchen.reactivebus.library;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class ReactiveBus implements Bus {

  private PublishSubject<Event> bus = PublishSubject.create();

  public static ReactiveBus create() {
    return new ReactiveBus();
  }

  @Override
  public void send(final Event object) {
    bus.onNext(object);
  }

  @Override
  public Flowable<Event> observe() {
    return bus.toFlowable(BackpressureStrategy.BUFFER);
  }
}

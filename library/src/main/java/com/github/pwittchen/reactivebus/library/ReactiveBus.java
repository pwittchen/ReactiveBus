package com.github.pwittchen.reactivebus.library;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ReactiveBus implements Bus {

  private Subject<Object> bus = PublishSubject.create().toSerialized();

  public static ReactiveBus create() {
    return new ReactiveBus();
  }

  @Override
  public void send(final Event object) {
    bus.onNext(object);
  }

  @Override
  public Flowable<Event> receive() {
    return bus
        .toFlowable(BackpressureStrategy.BUFFER)
        .filter(new Predicate<Object>() {
          @Override
          public boolean test(final Object o) {
            return o instanceof Event;
          }
        })
        .map(new Function<Object, Event>() {
          @Override
          public Event apply(final Object object) {
            return (Event) object;
          }
        });
  }
}

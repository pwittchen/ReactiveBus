package com.github.pwittchen.reactivebus.library;

import io.reactivex.Flowable;

public interface Bus {
  void send(Event event);

  Flowable<Event> receive();
}

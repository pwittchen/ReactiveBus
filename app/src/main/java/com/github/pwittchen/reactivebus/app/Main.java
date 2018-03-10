package com.github.pwittchen.reactivebus.app;

import com.github.pwittchen.reactivebus.library.Bus;
import com.github.pwittchen.reactivebus.library.Event;
import com.github.pwittchen.reactivebus.library.ReactiveBus;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Main {
  public static void main(String args[]) {

    System.out.println("app started");

    Bus bus = ReactiveBus.create();

    bus.send(new Event("This event won't be published!"));

    final Disposable events = bus.observe().subscribe(new Consumer<Event>() {
      @Override public void accept(Event event) {
        System.out.println(event.toString());
      }
    });

    bus.send(new Event("First event"));
    bus.send(new Event("Second event"));
    bus.send(new Event("Yet another event!"));

    events.dispose();

    bus.send(new Event("This event won't be published too!"));
  }
}

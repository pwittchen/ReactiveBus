package com.github.pwittchen.reactivebus.library;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ReactiveBusTest {
  @Test
  public void shouldCreateBus() {
    Bus bus = ReactiveBus.create();
    assertThat(bus).isNotNull();
  }

  @Test
  public void shouldSendAndReceiveEvent() {
    Bus bus = ReactiveBus.create();
    final Event sentEvent = new Event("test event");

    bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        assertThat(receivedEvent).isEqualTo(sentEvent);
      }
    });

    bus.send(sentEvent);
  }

  @Test
  public void shouldNotReceiveEventBeforeSubscription() {
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = new Event("test event one");
    final Event sentEventTwo = new Event("test event two");
    final int[] counter = {0};

    bus.send(sentEventOne);

    bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        counter[0]++;
      }
    });

    bus.send(sentEventTwo);

    assertThat(counter[0]).isEqualTo(1);
  }

  @Test
  public void shouldNotReceiveEventAfterDisposal() {
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = new Event("test event one");
    final Event sentEventTwo = new Event("test event two");
    final int[] counter = {0};

    Disposable subscription = bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        counter[0]++;
      }
    });

    bus.send(sentEventOne);
    subscription.dispose();
    bus.send(sentEventTwo);

    assertThat(counter[0]).isEqualTo(1);
  }

  @Test
  public void shouldBeAbleToReceiveManyEvents() {
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = new Event("test event one");
    final Event sentEventTwo = new Event("test event two");
    final Event sentEventThree = new Event("test event three");
    final int[] counter = {0};

    bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        counter[0]++;
      }
    });

    bus.send(sentEventOne);
    bus.send(sentEventTwo);
    bus.send(sentEventThree);

    assertThat(counter[0]).isEqualTo(3);
  }
}
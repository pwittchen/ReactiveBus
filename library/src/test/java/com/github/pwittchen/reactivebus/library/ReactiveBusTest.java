/*
 * Copyright (C) 2018 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    final Event sentEvent = Event.create("test event");

    Disposable subscription = bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        assertThat(receivedEvent).isEqualTo(sentEvent);
      }
    });

    bus.send(sentEvent);
    assertThat(subscription).isNotNull();
  }

  @Test
  public void shouldSendAndReceiveEventWithData() {
    Bus bus = ReactiveBus.create();
    final String testMessage = "test message";
    TestUtils.SerializableObject data = TestUtils.createSerializableObject(testMessage);
    final Event sentEvent = Event.create("test event", data);

    Disposable subscription = bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        assertThat(receivedEvent).isEqualTo(sentEvent);
        assertThat(receivedEvent.hasData()).isTrue();
        assertThat(receivedEvent.getData()).isInstanceOf(TestUtils.SerializableObject.class);
        String message = ((TestUtils.SerializableObject) receivedEvent.getData()).getMessage();
        assertThat(message).isEqualTo(testMessage);
      }
    });

    bus.send(sentEvent);
    assertThat(subscription).isNotNull();
  }

  @Test
  public void shouldSendAndReceiveEventOfProperType() {
    Bus bus = ReactiveBus.create();
    final Event sentEvent = Event.create("test event");
    final Event[] receivedEvent = new Event[1];

    Disposable subscription = bus
        .receive()
        .subscribe(new Consumer<Event>() {
          @Override public void accept(Event event) {
            // when assertion failed here in this test, whole test passed for some reason
            receivedEvent[0] = event;
          }
        });

    bus.send(sentEvent);

    assertThat(receivedEvent[0]).isEqualTo(sentEvent);
    assertThat(receivedEvent[0]).isInstanceOf(Event.class);
    assertThat(subscription).isNotNull();
  }

  @Test
  public void shouldNotReceiveEventBeforeSubscription() {
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = Event.create("test event one");
    final Event sentEventTwo = Event.create("test event two");
    final int[] counter = {0};

    bus.send(sentEventOne);

    Disposable subscription = bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        counter[0]++;
      }
    });

    bus.send(sentEventTwo);

    assertThat(counter[0]).isEqualTo(1);
    assertThat(subscription).isNotNull();
  }

  @Test
  public void shouldNotReceiveEventAfterDisposal() {
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = Event.create("test event one");
    final Event sentEventTwo = Event.create("test event two");
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
    assertThat(subscription).isNotNull();
    assertThat(subscription.isDisposed()).isTrue();
  }

  @Test
  public void shouldBeAbleToReceiveManyEvents() {
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = Event.create("test event one");
    final Event sentEventTwo = Event.create("test event two");
    final Event sentEventThree = Event.create("test event three");
    final int[] counter = {0};

    Disposable subscription = bus.receive().subscribe(new Consumer<Event>() {
      @Override public void accept(Event receivedEvent) {
        counter[0]++;
      }
    });

    bus.send(sentEventOne);
    bus.send(sentEventTwo);
    bus.send(sentEventThree);

    assertThat(counter[0]).isEqualTo(3);
    assertThat(subscription).isNotNull();
  }
}
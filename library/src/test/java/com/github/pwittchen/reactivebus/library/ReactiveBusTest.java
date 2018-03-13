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

import io.reactivex.subscribers.TestSubscriber;
import java.util.List;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ReactiveBusTest {

  @Test
  public void shouldCreateBus() {
    // when
    Bus bus = ReactiveBus.create();

    // then
    assertThat(bus).isNotNull();
  }

  @Test
  public void shouldSendAndReceiveEvent() {
    // given
    Bus bus = ReactiveBus.create();
    final Event sentEvent = Event.create("test event");

    // when
    TestSubscriber subscriber = new TestSubscriber();
    bus.receive().subscribe(subscriber);

    // then
    bus.send(sentEvent);
    subscriber.assertValue(sentEvent);
  }

  @Test
  public void shouldSendAndReceiveEventWithData() {
    // given
    Bus bus = ReactiveBus.create();
    final String testMessage = "test message";
    TestUtils.SerializableObject data = TestUtils.createSerializableObject(testMessage);
    final Event sentEvent = Event.create("test event", data);

    // when
    TestSubscriber subscriber = new TestSubscriber();
    bus.receive().subscribe(subscriber);
    bus.send(sentEvent);

    // then
    subscriber.assertValue(sentEvent);
    List<Event> values = subscriber.values();
    Event receivedEvent = values.get(0);
    assertThat(receivedEvent.getData()).isInstanceOf(TestUtils.SerializableObject.class);
    String message = ((TestUtils.SerializableObject) receivedEvent.getData()).getMessage();
    assertThat(message).isEqualTo(testMessage);
  }

  @Test
  public void shouldSendAndReceiveEventOfProperType() {
    // given
    Bus bus = ReactiveBus.create();
    final Event sentEvent = Event.create("test event");
    TestSubscriber subscriber = new TestSubscriber();

    // when
    bus.receive().subscribe(subscriber);
    bus.send(sentEvent);

    // then
    subscriber.assertValue(sentEvent);
    List<Event> values = subscriber.values();
    Event receivedEvent = values.get(0);
    assertThat(receivedEvent).isEqualTo(sentEvent);
    assertThat(receivedEvent).isInstanceOf(Event.class);
  }

  @Test
  public void shouldNotReceiveEventBeforeSubscription() {
    // given
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = Event.create("test event one");
    final Event sentEventTwo = Event.create("test event two");
    TestSubscriber subscriber = new TestSubscriber();

    // when
    bus.send(sentEventOne);
    bus.receive().subscribe(subscriber);
    bus.send(sentEventTwo);

    // then
    subscriber.assertValueCount(1);
  }

  @Test
  public void shouldNotReceiveEventAfterDisposal() {
    // given
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = Event.create("test event one");
    final Event sentEventTwo = Event.create("test event two");
    TestSubscriber subscriber = new TestSubscriber();

    // when
    bus.receive().subscribe(subscriber);
    bus.send(sentEventOne);
    subscriber.dispose();
    bus.send(sentEventTwo);

    // then
    subscriber.assertValueCount(1);
    assertThat(subscriber.isDisposed()).isTrue();
  }

  @Test
  public void shouldBeAbleToReceiveManyEvents() {
    // given
    Bus bus = ReactiveBus.create();
    final Event sentEventOne = Event.create("test event one");
    final Event sentEventTwo = Event.create("test event two");
    final Event sentEventThree = Event.create("test event three");
    TestSubscriber subscriber = new TestSubscriber();

    // when
    bus.receive().subscribe(subscriber);
    bus.send(sentEventOne);
    bus.send(sentEventTwo);
    bus.send(sentEventThree);

    // then
    subscriber.assertValueCount(3);
  }
}
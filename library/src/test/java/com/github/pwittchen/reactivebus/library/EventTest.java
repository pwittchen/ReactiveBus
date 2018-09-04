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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class EventTest {

  @Test
  public void shouldCreateEvent() {
    Event event = Event.create();
    assertThat(event).isNotNull();
  }

  @Test
  public void shouldCreateEventWithName() {
    String eventName = "test event";

    Event event = Event.name(eventName).build();

    assertThat(event).isNotNull();
    assertThat(event.name()).isEqualTo(eventName);
    assertThat(event.hasData()).isFalse();
  }

  @Test
  public void shouldCreateEventWithIdAndName() {
    String eventId = "test_id";
    String eventName = "test name";

    Event event = Event.id(eventId).name(eventName).build();

    assertThat(event).isNotNull();
    assertThat(event.id()).isEqualTo(eventId);
    assertThat(event.name()).isEqualTo(eventName);
    assertThat(event.hasData()).isFalse();
  }

  @Test
  public void shouldCreateEventWithIdNameAndData() {
    String eventId = "test_id";
    String eventName = "test name";
    TestUtils.SerializableObject data = TestUtils.createSerializableObject();

    Event event = Event.id(eventId).name(eventName).data(data).build();

    assertThat(event).isNotNull();
    assertThat(event.id()).isEqualTo(eventId);
    assertThat(event.name()).isEqualTo(eventName);
    assertThat(event.hasData()).isTrue();
    assertThat(event.data()).isEqualTo(data);
  }

  @Test
  public void shouldCreateEventWithData() {
    TestUtils.SerializableObject data = TestUtils.createSerializableObject();

    Event event = Event.data(data).build();

    assertThat(event).isNotNull();
    assertThat(event.hasData()).isTrue();
    assertThat(event.data()).isEqualTo(data);
  }

  @Test
  public void eventsShouldBeTheSame() {
    String eventId = "test_id";
    String eventName = "test name";

    Event eventOne = Event.id(eventId).name(eventName).build();
    Event eventTwo = Event.id(eventId).name(eventName).build();

    assertThat(eventOne.equals(eventTwo)).isTrue();
    assertThat(eventOne.hashCode()).isEqualTo(eventTwo.hashCode());
  }

  @Test
  public void eventsShouldBeDifferentWithDifferentNames() {
    String eventId = "test_id";

    Event eventOne = Event.id(eventId).name("name 1").build();
    Event eventTwo = Event.id(eventId).name("name 2").build();

    assertThat(eventOne.equals(eventTwo)).isFalse();
  }

  @Test
  public void eventsShouldBeDifferentWithDifferentIds() {
    String eventName = "test name";

    Event eventOne = Event.id("test_id_1").name(eventName).build();
    Event eventTwo = Event.id("test_id_2").name(eventName).build();

    assertThat(eventOne.equals(eventTwo)).isFalse();
  }

  @Test
  public void eventObjectsShouldBeTheSame() {
    Event eventOne = Event.create();

    assertThat(eventOne.equals(eventOne)).isTrue();
    assertThat(eventOne.hashCode()).isEqualTo(eventOne.hashCode());
  }

  @Test
  public void eventsShouldBeDifferent() {
    Event eventOne = Event.create();
    Event eventTwo = Event.create();

    assertThat(eventOne.equals(eventTwo)).isFalse();
  }

  @Test
  public void eventShouldBeDifferentThanNull() {
    Event eventOne = Event.create();

    assertThat(eventOne.equals(null)).isFalse();
  }

  @Test
  public void eventShouldBeDifferentThanGenericObject() {
    Event eventOne = Event.create();

    assertThat(eventOne.equals(new Object())).isFalse();
  }

  @Test
  public void toStringShouldDescribeEvent() {

    String eventId = "test_id";
    String eventName = "test name";

    Event eventOne = Event.id(eventId).name(eventName).build();

    assertThat(eventOne.toString()).isEqualTo("Event {id='test_id', name='test name'}");
  }

  @Test
  public void constructorShouldBeProtected() throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<Event> constructor = Event.class.getDeclaredConstructor();

    assertThat(Modifier.isProtected(constructor.getModifiers())).isTrue();

    constructor.setAccessible(true);
    constructor.newInstance();
  }

  @Test
  public void eventBuilderConstructorShouldBePrivate()
      throws NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {
    Constructor<Event.Builder> constructor = Event.Builder.class.getDeclaredConstructor();

    assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

    constructor.setAccessible(true);
    constructor.newInstance();
  }
}
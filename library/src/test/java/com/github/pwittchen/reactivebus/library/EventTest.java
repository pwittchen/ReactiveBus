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

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class EventTest {

  @Test
  public void shouldCreateEvent() {
    Event event = new Event();
    assertThat(event).isNotNull();
  }

  @Test
  public void shouldCreateEventWithName() {
    String eventName = "test event";

    Event event = new Event.Builder().setName(eventName).build();

    assertThat(event).isNotNull();
    assertThat(event.getName()).isEqualTo(eventName);
    assertThat(event.hasData()).isFalse();
  }

  @Test
  public void shouldCreateEventWithIdAndName() {
    String eventId = "test_id";
    String eventName = "test name";

    Event event = new Event.Builder().setId(eventId).setName(eventName).build();

    assertThat(event).isNotNull();
    assertThat(event.getId()).isEqualTo(eventId);
    assertThat(event.getName()).isEqualTo(eventName);
    assertThat(event.hasData()).isFalse();
  }

  @Test
  public void shouldCreateEventWithIdNameAndData() {
    String eventId = "test_id";
    String eventName = "test name";
    TestUtils.SerializableObject data = TestUtils.createSerializableObject();

    Event event = new Event.Builder().setId(eventId).setName(eventName).setData(data).build();

    assertThat(event).isNotNull();
    assertThat(event.getId()).isEqualTo(eventId);
    assertThat(event.getName()).isEqualTo(eventName);
    assertThat(event.hasData()).isTrue();
    assertThat(event.getData()).isEqualTo(data);
  }

  @Test
  public void eventsShouldBeTheSame() {
    String eventId = "test_id";
    String eventName = "test name";

    Event eventOne = new Event.Builder().setId(eventId).setName(eventName).build();
    Event eventTwo = new Event.Builder().setId(eventId).setName(eventName).build();

    assertThat(eventOne.equals(eventTwo)).isTrue();
    assertThat(eventOne.hashCode()).isEqualTo(eventTwo.hashCode());
  }

  @Test
  public void eventsShouldBeDifferentWithDifferentNames() {
    String eventId = "test_id";

    Event eventOne = new Event.Builder().setId(eventId).setName("name 1").build();
    Event eventTwo = new Event.Builder().setId(eventId).setName("name 2").build();

    assertThat(eventOne.equals(eventTwo)).isFalse();
  }

  @Test
  public void eventsShouldBeDifferentWithDifferentIds() {
    String eventName = "test name";

    Event eventOne = new Event.Builder().setId("test_id_1").setName(eventName).build();
    Event eventTwo = new Event.Builder().setId("test_id_2").setName(eventName).build();

    assertThat(eventOne.equals(eventTwo)).isFalse();
  }

  @Test
  public void eventObjectsShouldBeTheSame() {
    Event eventOne = new Event();

    assertThat(eventOne.equals(eventOne)).isTrue();
    assertThat(eventOne.hashCode()).isEqualTo(eventOne.hashCode());
  }

  @Test
  public void eventsShouldBeDifferent() {
    Event eventOne = new Event();
    Event eventTwo = new Event();

    assertThat(eventOne.equals(eventTwo)).isFalse();
  }

  @Test
  public void eventShouldBeDifferentThanNull() {
    Event eventOne = new Event();

    assertThat(eventOne.equals(null)).isFalse();
  }

  @Test
  public void eventShouldBeDifferentThanGenericObject() {
    Event eventOne = new Event();

    assertThat(eventOne.equals(new Object())).isFalse();
  }

  @Test
  public void toStringShouldDescribeEvent() {

    String eventId = "test_id";
    String eventName = "test name";

    Event eventOne = new Event.Builder().setId(eventId).setName(eventName).build();

    assertThat(eventOne.toString()).isEqualTo("Event {id='test_id', name='test name'}");
  }
}
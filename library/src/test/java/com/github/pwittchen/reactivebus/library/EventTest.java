package com.github.pwittchen.reactivebus.library;

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

    Event event = Event.create(eventName);

    assertThat(event).isNotNull();
    assertThat(event.getName()).isEqualTo(eventName);
  }

  @Test
  public void shouldCreateEventWithIdAndName() {
    String eventId = "test_id";
    String eventName = "test name";

    Event event = Event.create(eventId, eventName);

    assertThat(event).isNotNull();
    assertThat(event.getId()).isEqualTo(eventId);
    assertThat(event.getName()).isEqualTo(eventName);
  }

  @Test
  public void eventsShouldBeTheSame() {
    String eventId = "test_id";
    String eventName = "test name";

    Event eventOne = Event.create(eventId, eventName);
    Event eventTwo = Event.create(eventId, eventName);

    assertThat(eventOne.equals(eventTwo)).isTrue();
    assertThat(eventOne.hashCode()).isEqualTo(eventTwo.hashCode());
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

    Event eventOne = Event.create(eventId, eventName);

    assertThat(eventOne.toString()).isEqualTo("Event {id='test_id', name='test name'}");
  }
}
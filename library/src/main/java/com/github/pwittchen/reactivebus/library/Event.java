package com.github.pwittchen.reactivebus.library;

import java.util.Objects;
import java.util.UUID;

public class Event {
  private static final String EMPTY_STRING = "";

  private String id;
  private String message;

  public Event(String id, String message) {
    this.id = id;
    this.message = message;
  }

  public Event(String message) {
    this(UUID.randomUUID().toString(), message);
  }

  public Event() {
    this(EMPTY_STRING);
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override public String toString() {
    return "Event{" +
        "id='" + id + '\'' +
        ", message='" + message + '\'' +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return Objects.equals(id, event.id);
  }

  @Override public int hashCode() {
    return Objects.hash(id);
  }
}

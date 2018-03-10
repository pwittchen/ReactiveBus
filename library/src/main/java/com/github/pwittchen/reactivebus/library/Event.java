package com.github.pwittchen.reactivebus.library;

import java.util.Objects;
import java.util.UUID;

public class Event {
  private static final String EMPTY_STRING = "";

  private String id;
  private String name;

  public Event(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Event(String name) {
    this(UUID.randomUUID().toString(), name);
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return "Event{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
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

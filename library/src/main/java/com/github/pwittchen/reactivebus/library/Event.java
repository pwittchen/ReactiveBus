package com.github.pwittchen.reactivebus.library;

import java.util.Objects;
import java.util.UUID;

public class Event {
  private static final String EMPTY_STRING = "";

  private String id;
  private String name;

  public static Event create(String id, String name) {
    return new Event(id, name);
  }

  public static Event create(String name) {
    return new Event(name);
  }

  public static Event create() {
    return new Event();
  }

  private Event(String id, String name) {
    this.id = id;
    this.name = name;
  }

  private Event(String name) {
    this(UUID.randomUUID().toString(), name);
  }

  private Event() {
    this(EMPTY_STRING);
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override public String toString() {
    return "Event {" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
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

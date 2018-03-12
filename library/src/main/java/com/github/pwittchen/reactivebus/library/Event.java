package com.github.pwittchen.reactivebus.library;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Event {
  private static final String EMPTY_STRING = "";

  private String id;
  private String name;
  private Serializable data;

  private Event(String id, String name, Serializable data) {
    this.id = id;
    this.name = name;
    this.data = data;
  }

  private Event(String id, String name) {
    this(id, name, null);
  }

  private Event(String name) {
    this(UUID.randomUUID().toString(), name);
  }

  private Event() {
    this(EMPTY_STRING);
  }

  public static Event create(String name, Serializable data) {
    return new Event(UUID.randomUUID().toString(), name, data);
  }

  public static Event create(String id, String name, Serializable data) {
    return new Event(id, name, data);
  }

  public static Event create(String id, String name) {
    return new Event(id, name);
  }

  public static Event create(String name) {
    return new Event(name);
  }

  public static Event create() {
    return new Event();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Serializable getData() {
    return data;
  }

  public boolean hasData() {
    return data != null;
  }

  @Override
  public String toString() {
    return "Event {" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Event event = (Event) o;
    return Objects.equals(id, event.id) && Objects.equals(name, event.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}

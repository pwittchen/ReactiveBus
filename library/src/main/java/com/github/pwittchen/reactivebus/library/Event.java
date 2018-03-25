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

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class Event {

  private final String id;
  private final String name;
  private final Serializable data;

  public static Event create() {
    return Event.builder().build();
  }

  private Event(String id, String name, Serializable data) {
    this.id = id;
    this.name = name;
    this.data = data;
  }

  private Event(Builder builder) {
    this(builder.id, builder.name, builder.data);
  }

  private Event() {
    this(builder());
  }

  private static Builder builder() {
    return new Builder();
  }

  public static Builder id(final String id) {
    return builder().id(id);
  }

  public String id() {
    return id;
  }

  public static Builder name(final String name) {
    return builder().name(name);
  }

  public String name() {
    return name;
  }

  public static Builder data(final Serializable data) {
    return builder().data(data);
  }

  public Serializable data() {
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

  public final static class Builder {
    private String id = UUID.randomUUID().toString();
    private String name = "";
    private Serializable data;

    private Builder() {
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder data(Serializable data) {
      this.data = data;
      return this;
    }

    public Event build() {
      return new Event(this);
    }
  }
}

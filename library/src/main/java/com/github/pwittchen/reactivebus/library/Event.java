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

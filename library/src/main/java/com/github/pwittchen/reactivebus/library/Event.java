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

  private String id;
  private String name;
  private Serializable data;

  public Event(String id, String name, Serializable data) {
    this.id = id;
    this.name = name;
    this.data = data;
  }

  public Event(Builder builder) {
    this(builder.id, builder.name, builder.data);
  }

  public Event() {
    this(new Builder());
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

  public static class Builder {
    private String id = UUID.randomUUID().toString();
    private String name = "";
    private Serializable data;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setData(Serializable data) {
      this.data = data;
      return this;
    }

    public Event build() {
      return new Event(this);
    }
  }
}

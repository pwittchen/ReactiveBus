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

class TestUtils {

  private TestUtils() {
  }

  static SerializableObject createSerializableObject() {
    return new SerializableObject();
  }

  static SerializableObject createSerializableObject(String message) {
    return new SerializableObject(message);
  }

  static class SerializableObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;

    public SerializableObject() {
      this.message = "";
    }

    public SerializableObject(final String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    @Override public boolean equals(final Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      final SerializableObject that = (SerializableObject) o;
      return Objects.equals(message, that.message);
    }

    @Override public int hashCode() {
      return Objects.hash(message);
    }
  }
}

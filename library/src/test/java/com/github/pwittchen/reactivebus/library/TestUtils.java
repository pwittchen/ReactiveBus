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

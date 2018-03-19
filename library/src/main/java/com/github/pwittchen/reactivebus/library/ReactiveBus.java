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

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ReactiveBus implements Bus {

  private final Subject<Object> bus = PublishSubject.create().toSerialized();

  public static ReactiveBus create() {
    return new ReactiveBus();
  }

  @Override
  public void send(final Event object) {
    bus.onNext(object);
  }

  @Override
  @SuppressWarnings("unchecked")
  public Flowable<Event> receive() {
    return (Flowable<Event>)(Flowable<?>) bus
        .toFlowable(BackpressureStrategy.BUFFER)
        .filter(new Predicate<Object>() {
          @Override
          public boolean test(final Object o) {
            return o instanceof Event;
          }
        });
  }
}

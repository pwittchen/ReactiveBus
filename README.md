# ReactiveBus
🚍 Simple Reactive Event Bus for JVM & Android applications built with RxJava 2

This project is compatible with Java 1.7+.

Usage
-----

We can use this library as follows:

```java
Bus bus = ReactiveBus.create();

Disposable observer = bus.receive().subscribe(new Consumer<Event>() {
    @Override public void accept(Event event) {
      // handle event here
    }
  });

bus.send(Event.create("my event"));
observer.dispose(); // after disposal, observer will stop receiving new events
```
Download
--------

TBD.

Code style
----------

Code style used in the project is called Square from Java Code Styles repository by `Square` available at: https://github.com/square/java-code-styles.

Building, tests and static code analysis
--------------------

To build project, type:

```
./gradlew build
```

To run tests, type:

```
./gradlew test
```

To run static code analysis, type:

```
./gradlew check
```

References
----------
- **Articles**:
  - [Understanding RxJava Subject — Publish, Replay, Behavior and Async Subject](https://blog.mindorks.com/understanding-rxjava-subject-publish-replay-behavior-and-async-subject-224d663d452f)
  - [What's different in RxJava 2.0?](https://github.com/ReactiveX/RxJava/wiki/What%27s-different-in-2.0)
  - [Implementing an Event Bus with RxJava 1](https://blog.kaush.co/2014/12/24/implementing-an-event-bus-with-rxjava-rxbus/)
- **Other Event Bus implementations**:
  - [Otto](https://github.com/square/otto)
  - [Guava](https://github.com/google/guava)
  - [GreenRobot's Event Bus](https://github.com/greenrobot/EventBus)

License
-------

    Copyright 2015 Piotr Wittchen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
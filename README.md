# ReactiveBus [![Build Status](https://travis-ci.org/pwittchen/ReactiveBus.svg?branch=master)](https://travis-ci.org/pwittchen/ReactiveBus) [![codecov](https://codecov.io/gh/pwittchen/ReactiveBus/branch/master/graph/badge.svg)](https://codecov.io/gh/pwittchen/ReactiveBus)  ![Maven Central](https://img.shields.io/maven-central/v/com.github.pwittchen/reactivebus.svg?style=flat) 
🚍 Simple Reactive Event Bus for JVM (1.7+) and Android apps built with RxJava 2

Usage
-----

```java
Bus bus = ReactiveBus.create(); // creating thread safe instance of event bus

Disposable observer = bus.receive().subscribe(new Consumer<Event>() {
    @Override public void accept(Event event) {
      // handle event here
    }
  });

bus.send(Event.create("my event")); // send a message only
bus.send(Event.create("my another event", serializableObject)); // send some data

observer.dispose(); // after disposal, observer will stop receiving new events
```
Download
--------

You can depend on the library through Maven:

```xml
<dependency>
    <groupId>com.github.pwittchen</groupId>
    <artifactId>reactivebus</artifactId>
    <version>0.0.1</version>
</dependency>
<dependency>
    <groupId>io.reactivex.rxjava2</groupId>
    <artifactId>rxjava</artifactId>
    <version>2.1.10</version>
</dependency>
```

or through Gradle:

```groovy
implementation 'com.github.pwittchen:reactivebus:0.0.1'
implementation 'io.reactivex.rxjava2:rxjava:2.1.10'
```

Code style
----------

Code style used in the project is called Square from Java Code Styles repository by `Square` 

available at: https://github.com/square/java-code-styles.

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
  - [Event Bus in Guava](https://github.com/google/guava/wiki/EventBusExplained)
  - [GreenRobot's Event Bus](https://github.com/greenrobot/EventBus)

License
-------

    Copyright 2018 Piotr Wittchen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Lab Activity #10 - 20261120

v1.0.0-20261117
 
### Asynchronous Programming in JavaScript using node.js

- Asynchronous programming from CPS to async/await in JavaScript using [node.js](https://nodejs.org/). 
- Getting started 
  1) Open a terminal, change dir to `lab-activities/lab-activity-10/sisco-lab-10-javascript` available on the repo.
  2) Pull the node image, from docker hub (be sure to have docker running on your system):
      ```
      docker pull node
      ```
  3) Run JavaScript programs by running the node container, based on the `node` image and mouting the local directory as `/sisco-lab` directory inside the container:
     ```
     docker run -v .:/sisco-lab node node /sisco-lab/hello-world.js
- Running examples in [javascript-async-prog-steps.md](./sisco-lab-10-javascript/javascript-async-prog-steps.md)







### Asynchronous Programming in Java using Vert.x

- [Vert.x Framework](http://vertx.io/)
- Key points:
  - Reactor and Multi-Reactor, The Golden Rule - Don’t Block the Event Loop
  - "Verticle" abstraction => event loop
    - multiple verticles => multiple independent event loops 
  - Async programming style
    - based on (async) "future" + promise
    - future composition and coordination
  - How to run blocking code or long-term computation (`executeBlocking` mechanism)
  - Verticles asynchronous communication using the Event Bus 
  - Libraries. Among the others:
    - file systems, network (e.g. http) 
- Basic examples in `sisco.async`
    - Simple examples (through steps) showing some core API at work  

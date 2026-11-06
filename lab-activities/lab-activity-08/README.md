Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Lab Activity #08 - 20261106 and 20261109

v1.0.1-20261106

### How to Implement Monitors in Java

**[Lab Notes] Implementing Monitors in Java**
- Testing raw behavior of `wait`, `notify`
  - `sisco.TestRawWaitNotify` 
  - `sisco.TestIllegalMonitorStateEx`
  - `sisco.TestInterruptedException`
- Implementing a monitor - first approach, using raw support
  - `sisco.monitors.SynchCell`, 
- Implementing a monitor - second approach, using lib support
  - `sisco.monitors.SynchCell2`   
- More complex example: Bounded-Buffer implementation example in Producers-Consumers  
  - `sisco.monitors.BoundedBufferImplRaw`, `sisco.monitors.BoundedBufferImplWithLib`
- Work-in-Lab  
  - Implementing a barrier monitor - `sisco.monitors.ex_barrier`  
  - Implementing latch monitor - `sisco.monitors.ex_latch`  
- Testing signalling discipline semantics in Java
  - the S&C signalling discipline effects - different behaviours 
     - `sisco.monitors.TestSemantics1` (raw monitor impl)
         - Thread #1 is the first to enter the monitor
         - Thread #2 and Thread #3 arrive after Thread #1 is already inside
         - Thread #3 requests to enter after Thread #2
         - Thread #1 executes a wait inside the monitor before Thread #3 requests to enter
     - `sisco.monitors.TestSemantics2` (raw monitor impl, different timings)
         - Like previous case, but Thread #3 requests to enter before Thread #1 executes the wait, so it is already in the entry set when Thread #1 executes the wait   
     - `sisco.monitors.TestSemantics3`  
         - monitor impl. Based on Locks+Cond
 
### From Threads to Tasks

**[Lab Notels] Task-Oriented Programming in Java**
- `Executor` Framework - Examples
  - Matmul example – `sisco.executors.matmul`
  - Quadrature problem example, with different strategies to collect results
  	- collecting results with monitor + shutdown mechanism to synch – `sisco.executors.quad1_basic`
  	- collecting results only with monitor - `sisco.executors.quad2_withsynch`
  	- without monitors, using futures - `sisco.executors.quad2_withfuture`
- Beware of deadlocks using Executors – example: `sisco.executors.deadlock`
  - Why there is a deadlock?
-  Improving structure in task-oriented programming  
    -  `ForkJoin` executor   
        - map-reduce example: `sisco.executors.fj`   
    - Structured Concurrency (JEP 437)    

### Virtual Threads 

**[Lab Notes] Virtual Threads**
- Examples – `sisco.virtual_threads`
  - Test scalability: creating high number of virtual threads (`sisco.sisco.virtual_threads.TestScalability`)
  - Test scalability, using an Executor based on virtual threads (`sisco.sisco.virtual_threads.TestScalabilityWithExecutor`)
  - Test virtual threads with monitors (`sisco.sisco.virtual_threads.TestVTwithMonitors`)


### Multi-threaded programs with GUI

**[Lab Notes] GUI Frameworks and Concurrency**
- Chrono case study
  - bugged version: not responsive and with races: `sisco.mvc_chrono.not_reactive_plus_races`
  - correct version: reactive and with no races: `sisco.mvc_chrono.reactive_no_races`

- Applying producer/consumer architecture for handling asynchronous input in a concurrent MVC-based architecture - `sisco.mvc_with_async_cmd`example 
  - the controller is an active controller consuming commands asynchronously produced by the input component (the view frame) and acting on the model
      - inputs are: increase (key I), decrease (key D), reset (key R)
  - the view is an observer of the model, to be updated each time the model changes
      - the view model is a monitor, so as to avoid races between the active controller thread updating the model (and - indirectly - the view model)  and the EDT thread that needs to access the view model to update the GUI.
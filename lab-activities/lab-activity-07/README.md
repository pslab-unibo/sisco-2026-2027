Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Lab Activity #07 - 20261030

v1.0.0-20261029

### Semaphores in Java

- [Lab Notes] Thread Coordination in Java - Library Support
	- `Semaphore` class 
		- Implementing a critical section with semaphores: `sisco.cs_withsem`

- **Work-in-Lab** -  Thread synchronisation with semaphores
	- Preparation
		- Consider the `sisco.ex01_syncwithsem.TestPingPong` program
			- `Pinger` and `Ponger` agents, concurrently printing ping and pong
	- Exercise  
		- Make `Pinger` and `Ponger` synchronised using semaphores, so that they alternate in printing ping and pong
		- What correctness properties? How to specify and check them with JPF?

### Tool: VisualVM profiler
 
- [VisualVM](https://visualvm.github.io/)
	- useful also to track thread behaviour/state (blocked, not blocked)

### Thread Liveness & Deadlocks

- [Lab Notes] Thread Liveness
- The simplest deadlock in Java: `sisco.deadlock_simplest.TestDeadlockSimplest`
  - Analysis using VisualVM
- More complex example: AccountManager in `sisco.liveness.accounts` 
  - requirements
    - no race conditions in updating individual accounts
    - transfer between accounts should be atomic 
    - maximising concurrency
      - two transactions involving distinct couples of accounts should be served concurrentl
- Deadlocks when implementing MVC and Observer pattern
  - `sisco.deadlock_obs.TestObsPatternDeadlock`

### Using JPF

      
  - **Preparing the environment**:
    -  Start a container from the `jpf-core` directory (see [Lab Activity #06](../lab-activity-06/README.md)), mounting the  local directory `sisco-2026-2027/lab-activities/lab-activity-07/sisco-jpf` as `/home/sisco-jpf` inside the container:

       `docker-compose run --rm -v <your local directory>:/home/sisco-jpf jpf-dev` 

       For instance: mounting the `lab-activity-07/sisco-jpf` directory:

       `docker-compose run --rm -v <path to SISCO repo>/sisco-2026-2027/lab-activities/lab-activity-07/sisco-jpf:/home/sisco-jpf jpf-dev`
  	- Compile all Java sources:
    
    	`javac -d /home/sisco-jpf/target/classes -classpath /home/sisco-jpf/target/classes:/home/sisco-jpf/lib/jpf.jar /home/sisco-jpf/src/main/java/sisco/jpf/*.java`
 
  		(the classpath must include also `jpf.jar` including the Verify API classes)

  - **Example #1** - model-checking sequential programs...
    - sequential program - `sisco.jpf.TestSequential`
      - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestSequential.jpf`
    - sequential program with input (using **Verify API**) - `sisco.jpf.TestSequentialWihtInput`
      - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestSequentialWithInput.jpf`
    - sequential program with random (using **Verify API**) - `sisco.jpf.TestSequentialWihtRand`
      - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestSequentialWithRand.jpf`
  - **Example #2** - model-checking simple concurrent programs 
    - simple concurrent program - `sisco.jpf.TestScenarios`
      - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestScenarios.jpf`
      - look at the number of states and traces (scenarios)
    - defining atomic blocks (using **Verify API**)  
      - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestScenariosWithAtomicBlocks.jpf`
      - look at the number of states and traces
  - **Example #3a** - Finding lost updates races using precise race detector
    - `sisco.jpf.TestLostUpdate`
    - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestLostUpdate.jpf`
  - **Example #3b** - **TODO** (Work-in-Lab): Finding lost updates races - **using assert** 
    - `sisco.jpf.TestLostUpdateUsingAssert`
    - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestLostUpdateUsingAssert.jpf`
  - **Example #4** - Finding check-and-act races 
    - `sisco.jpf.TestCheckAct`
    - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestCheckAct.jpf`
  - **Example #5** - Detecting deadlocks
    - `sisco.jpf.TestDeadlock`
    - `java -jar build/RunJPF.jar /home/sisco-jpf/src/main/java/sisco/jpf/TestDeadlock.jpf`


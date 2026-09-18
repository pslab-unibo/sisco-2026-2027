Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Lab Activity #01 - 20260918

version: 1.0.0 - last update: 20260917

### Modelling concurrent systems - Part I

**Introducing the tool: LTSA - Labelled Transition System Analyser**
- A tool for specifying models in FSP, producing the corresponding LTSs and analysing them
- Java-based (quite old, but still working)
- Can be downloaded at https://www.doc.ic.ac.uk/ltsa/
- Executable jar available [here](./ltsatool/ltsa.jar)
  - To run: `java -jar ltsa.jar`
  - To run extending JVM initial heap memory (e.g. to 1 GB): `java -Xms1g -jar ltsa.jar`
  

Reference documentation:  
- "Concurrency: State Models & Java Programs" (Jeff Magee, Jeff Kramer) book
  - Appendix A - FSP quick reference
  - Appendix B - FSP language specification
  - Appendix C - FSP semantics
- Digital copy available on course web site (Materials folder)

**Working with the tool - modelling single processes**

- Action Prefix 
  - Modelling a simple clock, that ticks all time

- Choice operator
  - Extend the clock so that it can be started and stopped

  - A look to predefined processes: STOP, END, ERROR 

- Choice operator for modelling non-determinism
  - Modelling a clock that can break

- Indexed processes and actions
  - Examples: 
    - BUF process
    - SUM process

- Guarded actions
  - Example: 
    - COUNTDOWN process
  - Remarks
    - Using expressions to define indexes (see FSP lang spec)
    - Further construct : `if <expr> then P else Q`

**Exercise: modelling a full-fledged coffee machine**

The coffee machines provides two buttons, to choose either coffee or tea. By doing the selection, the machine starts the making process, that lasts N steps (conceptually visualised by a process bar). When the beverage is ready, the machine waits the user to grab it and then it goes back to the initial state. A failure can occur in any moment: in the case of failure, the machine stops in an error state.

*Extension #1: Sugar levels*

Before starting the process, a sugar level can be chosen (SL levels). 

*Extension #2: Bounded resources*

The coffee or tea can finish. If a beverage is not available, it cannot be selected. If no beverage is available, the machine enters in a maintenance mode, waiting to be restored. 



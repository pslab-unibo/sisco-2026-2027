Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Assignment #02

v0.9.0-20261105

The assignment is about the design and development of concurrent program implementing the N-Body simulation.

### Description 

In physics, the [*n-body problem*](https://en.wikipedia.org/wiki/N-body_problem) is the problem of predicting the individual motions of a group of celestial objects interacting with each other gravitationally. The classical physical problem can be stated as follows:

*Each particle in a system of a finite number of particles is subjected to a Newtonian gravitational attraction from all the other particles, and to no other forces. If the initial state of the system is given, how will the particles move?*

In the following we consider a simulation approach to solve the problem, based on the following algorithm (in pseudo-code):

```
vt = 0;     /* virtual time */         
dt  = 0.01; /* time increment at each iteration */

initialize a set of bodies b (position, mass, velocity)

loop {
    
    For each body b[i]:
      compute total force exerted by other bodies b[j]
      compute the instant acceleration, given the total force and mass
      update body velocity, given the acceleration and the virtual time elapsed dt   
    
    Update bodies positions, given the velocity and virtual time elapsed dt
    vt = vt + dt;   
    Display current stage;  /* if visualisation is required */
}

```

A simple sequential implementation is available on the repo `sisco.ass2.n_body_seq`. In this implementation, bodies lives in a 2D space and there are two versions: without visualisation (`sisco.ass2.n_body_seq.SimulationMain`) and with a simple GUI visualisation (`sisco.ass2.n_body_seq.SimulationMainWithGUI`). 

### The assignment

We want to develop a concurrent version of the simulation, in two steps described in the following. 

1) First step: we want to develop a concurrent version without visualisation and interaction with the user, focusing only on performances.  

    The concurrent program should be able to run a simulation with a certain number of bodies N (initialised randomly), for a number of simulation steps M. 

    In this case, two different implementations must be developed:
    - One based on Java **multithreaded programming**, using only default/platform threads;
    - One based on a **Task-based** approach, using Java **Executor Framework**.

    The concurrent programs should be designed according the principles studied during the course, promoting modularity, encapsulation as well as performance, reactivity. For enabling/managing interaction among active components/threads, high-level constructs such as monitors should be preferably used (vs. low-level mechanisms) when possible, providing your own implementation.

2) Second step, we want to develop a version with a GUI that allow to: 
    - visualize every step of the simulation, and
    - start, stop, suspend, execute step-by-step the simulation.

    For this second point, only one implementation is requested (no constraints about it).

The documentation can be provided as a report (either in *.md or *.pdf), including:
  - A brief analsysis of the problem, focusing in particular those aspects that are relevant from a concurrency point of view
  - A brief description of the adopted design, the architecture (structure) and the behaviour
    - for the behaviour, a model based on FSP should be included, identifying some correctness properties
  - Performance tests -- only for step 1 (no GUI), to show: 
    - if/how performances are improved compared with a sequential version
    - if/how performances scale with the number of available cores 
  - Verification of the program using model-checking -- only for step 1 (no GUI), multithreaded programming version (no Executors)
    - using JPF applied with a simplified version of the program.

### The deliverable

The deliverable must be a zipped folder `Assignment-02`, to be submitted on the course web site, including:  
- `src` directory with sources
- `doc` directory with the report





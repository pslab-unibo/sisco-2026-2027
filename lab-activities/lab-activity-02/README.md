Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Lab Activity #02 - 20260925

version: 1.0.0 - last update: 20260924

### Modelling concurrent systems - Part II


**Exercise solution** 

- The basic coffee machine

  *The coffee machine provides two buttons, to choose either coffee or tea. By doing the selection, the machine starts the making process, that lasts N steps (conceptually visualised by a process bar). When the beverage is ready, the machine waits the user to grab it and then it goes back to the initial state. A failure can occur in any moment: in the case of failure, the machine stops in an error state.*

```
const MAX_STEPS = 3
const COFFEE = 1
const TEA = 1
range RANGE_STEPS = 1..MAX_STEPS 
range BEVERAGES = 1..2

COFFE_MACHINE = IDLE,
IDLE = 
  (coffee_button_pressed -> MAKING[COFFEE][1]
  |tea_button_pressed -> MAKING[TEA][1]
  |failure -> ERROR),
MAKING[bev:BEVERAGES][i:RANGE_STEPS] = 
  (when i < MAX_STEPS step -> MAKING[bev][i+1]
  |when i == MAX_STEPS step -> RELEASE[bev]
  |failure -> ERROR),
RELEASE[bev:BEVERAGES] = 
  (output[bev] -> grab -> IDLE
  |failure -> ERROR).
```

- Extension #1: Sugar levels

  *Before starting the process, a sugar level can be chosen (SL levels).* 

```
const MAX_STEPS = 3
const NUM_BEV = 2
const MAX_SUGAR_LEVEL = 3
const COFFEE = 1
const TEA = 2

range RANGE_STEPS = 1..MAX_STEPS 
range BEVERAGES = 1..NUM_BEV
range SUGAR_LEVELS = 1..MAX_SUGAR_LEVEL

COFFEE_MACHINE = IDLE[1],
IDLE[sl:SUGAR_LEVELS] = 
  (when sl > 1 dec_sugar -> IDLE[sl-1]
  |when sl < MAX_SUGAR_LEVEL inc_sugar -> IDLE[sl+1]
  |coffee_button_pressed -> MAKING[COFFEE][1]
  |tea_button_pressed -> MAKING[TEA][1]
  |failure -> ERROR),
MAKING[bev:BEVERAGES][i:RANGE_STEPS] = 
  (when i < MAX_STEPS step -> MAKING[bev][i+1]
  |when i == MAX_STEPS step -> RELEASE[bev]
  |failure -> ERROR),
RELEASE[bev:BEVERAGES] = 
  (output[bev] -> grab -> IDLE[1]
  |failure -> ERROR).
```

- Extension #2: Bounded resources

  *The coffee or tea can finish. If a beverage is not available, it cannot be selected. If no beverage is available, the machine enters in a maintenance mode, waiting to be restored.* 

```
const MAX_STEPS = 3
const MAX_COFFEE = 2
const MAX_TEA = 2
const MAX_SUGAR = 3
const INITIAL_SUGAR_LEVEL = 2
const COFFEE = 1
const TEA = 2

range RANGE_STEPS = 1..MAX_STEPS 
range BEVERAGES = 1..2
range SUGAR_LEVELS = 0..MAX_SUGAR
range AVAIL_COFFEE = 0..MAX_COFFEE
range AVAIL_TEA = 0..MAX_TEA

COFFEE_MACHINE = START[INITIAL_SUGAR_LEVEL][MAX_COFFEE][MAX_TEA],
START[sl:SUGAR_LEVELS][c:AVAIL_COFFEE][t:AVAIL_TEA] =
  if (c == 0 && t == 0) then MAINTENANCE else IDLE[sl][c][t],
IDLE[sl:SUGAR_LEVELS][c:AVAIL_COFFEE][t:AVAIL_TEA] =
  (when sl > 0 dec_sugar -> IDLE[sl-1][c][t]
  |when sl < MAX_SUGAR inc_sugar -> IDLE[sl+1][c][t]
  |when c > 0 coffee_button_pressed -> MAKING[COFFEE][1][c-1][t]
  |when t > 0 tea_button_pressed -> MAKING[TEA][1][c][t-1]
  |failure -> ERROR),
MAKING[bev:BEVERAGES][i:RANGE_STEPS][c:AVAIL_COFFEE][t:AVAIL_TEA] = 
 (when i < MAX_STEPS step -> MAKING[bev][i+1][c][t]
 |when i == MAX_STEPS step -> RELEASE[bev][c][t]
 |failure -> ERROR),
RELEASE[bev:BEVERAGES][c:AVAIL_COFFEE][t:AVAIL_TEA] = 
 (output[bev] -> grab -> START[INITIAL_SUGAR_LEVEL][c][t]
 |failure -> ERROR),
MAINTENANCE = 
 (restored -> START[INITIAL_SUGAR_LEVEL][MAX_COFFEE][MAX_TEA]).
```

**Modelling the Coffee Machine using Petri Nets** 

- [TINA tool](https://projects.laas.fr/tina)

**Modelling concurrent systems** 

Implementing the examples on the slides/book using the LTSA tool.

- Parallel composition

```
CLOCK = (tick->CLOCK).
RADIO = (on->off->RADIO).
||CLOCK_RADIO = (CLOCK || RADIO).
```
- Interaction

```
MAKER = (make->ready->MAKER).
USER  = (ready->use->USER).
||MAKER_USER = (MAKER || USER).
```

- Multiple process instances - process labelling and set of prefix labels

```
RESOURCE = (acquire->release->RESOURCE).
USER = (acquire->use->release->USER).
||RESOURCE_SHARE =
  (a:USER || b:USER || {a,b}::RESOURCE).
```
- Hiding and silent actions

```
RESOURCE = (acquire->release->RESOURCE).
USER = (acquire->use->release->USER)\{use}.
||RESOURCE_SHARE =
  (a:USER || b:USER || {a,b}::RESOURCE).
```

**Exercise: Modelling the Coffe Machine and its Users**

- Modelling the Coffee Machine (basic version) and a single user that could decide to get either a coffee or a tea,
use the machine, drink the beverage, waiting a bit and then getting another one, continuosly.

- Modelling the Coffee Machine (basic version) and two users, one getting only coffee and one getting only tea, continuously.

- Modelling the Coffee Machine with sugar levels, and two users, one wanting only coffee with no sugar, and one wanting only tea with max sugar
  - the two users should not interfere
  
**A look at more complex examples**

- Modelling Space Invaders (from the book)
  - [Space Invaders Model](./space_invaders.lts)


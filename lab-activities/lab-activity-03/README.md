Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Lab Activity #03 - 20261002

version: 1.0.0 - last update: 20260930

### Modelling concurrent systems - Part III

#### Exercise solution 

- *Modelling the Coffee Machine (basic version) and a single user that could decide to get either a coffee or a tea, use the machine, drink the beverage, waiting a bit and then getting another one, continuosly.* 
  ```
  /* coffee machine basic + a user */

  const MAX_STEPS = 3
  range RANGE_STEPS = 1..MAX_STEPS 
  range BEVERAGES = 1..2

  COFFEE_MACHINE = IDLE,
  IDLE = 
    (coffee_button_pressed -> MAKING[1][1]
    |tea_button_pressed -> MAKING[2][1]
    |failure -> ERROR),
  MAKING[bev:BEVERAGES][i:RANGE_STEPS] = 
    (when i < MAX_STEPS step -> MAKING[bev][i+1]
    |when i == MAX_STEPS step -> RELEASE[bev]
    |failure -> ERROR),
  RELEASE[bev:BEVERAGES] = 
    (output[bev] -> grab -> IDLE
    |failure -> ERROR).

  USER  = 
    (want_coffee -> coffee_button_pressed -> grab -> drink -> USER
    |want_tea -> tea_button_pressed -> grab -> drink -> USER).

  ||COFFEE_MACHINE_AND_USER = (COFFEE_MACHINE || USER).
  ``` 
  [File](./coffee-machine-basic-with-a-user.lts)

  
- Modelling the Coffee Machine (basic version) and *two users, one getting only coffee and one getting only tea, continuously.*
  ```
  /* coffee machine basic + two users */

  const MAX_STEPS = 3
  range RANGE_STEPS = 1..MAX_STEPS 
  range BEVERAGES = 1..2

  COFFEE_MACHINE = IDLE,
  IDLE = 
    (coffee_button_pressed -> MAKING[1][1]
    |tea_button_pressed -> MAKING[2][1]
    |failure -> ERROR),
  MAKING[bev:BEVERAGES][i:RANGE_STEPS] = 
    (when i < MAX_STEPS step -> MAKING[bev][i+1]
    |when i == MAX_STEPS step -> RELEASE[bev]
    |failure -> ERROR),
  RELEASE[bev:BEVERAGES] = 
    (output[bev] -> grab -> IDLE
    |failure -> ERROR)\{step, output}.

  USER_COFFEE  = 
    (want_coffee -> coffee_button_pressed -> grab -> drink -> USER_COFFEE).

  USER_TEA  = 
    (want_tea -> tea_button_pressed -> grab -> drink -> USER_TEA).

  ||USERS = (a:USER_COFFEE || b:USER_TEA).

  ||COFFEE_MACHINE_AND_USERS = ( USERS || {a,b}::COFFEE_MACHINE).
  ``` 
  [File](./coffee-machine-basic-with-two-users.lts)

- Modelling the Coffee Machine with sugar levels, and *two users, one wanting only coffee with no sugar, and one wanting only tea with max sugar - the two users should not interfere* 
  ```
  /* coffee machine - extension #1 */

  const MAX_STEPS = 3
  const NUM_BEV = 2
  const MAX_SUGAR_LEVEL = 3
  const COFFEE = 1
  const TEA = 2

  range RANGE_STEPS = 1..MAX_STEPS 
  range BEVERAGES = 1..NUM_BEV
  range SUGAR_LEVELS = 0..MAX_SUGAR_LEVEL

  COFFEE_MACHINE = IDLE[1],
  IDLE[sl:SUGAR_LEVELS] = 
    (when sl > 0 dec_sugar -> IDLE[sl-1]
    |when sl < MAX_SUGAR_LEVEL inc_sugar[sl] -> IDLE[sl+1]
    |read_sugar_level[sl] -> IDLE[sl]
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

  USER_COFFEE  = 
    (want_coffee -> acquire -> READ_SUGAR_LEVEL),
  READ_SUGAR_LEVEL =
    (read_sugar_value[sl:SUGAR_LEVELS] -> DECREASE_COFFEE[sl]),
  DECREASE_COFFEE[sl:SUGAR_LEVELS] = 
    (when sl > 0 dec_sugar -> DECREASE_COFFEE[sl-1]
    |when sl == 0 coffee_button_pressed -> grab -> release -> drink -> USER_COFFEE).

  USER_TEA  = 
    (want_tea -> acquire -> READ_SUGAR_LEVEL),
  READ_SUGAR_LEVEL =
    (read_sugar_value[sl:SUGAR_LEVELS] -> INCREASE_SUGAR[sl]),
  INCREASE_SUGAR[sl:SUGAR_LEVELS] = 
    (when sl < MAX_SUGAR_LEVEL inc_sugar -> INCREASE_SUGAR[sl+1]
    |when sl == MAX_SUGAR_LEVEL tea_button_pressed -> grab -> release -> drink -> USER_TEA).

  LOCK = (acquire -> release -> LOCK).

  ||USERS = (a:USER_COFFEE || b:USER_TEA).

  ||COFFEE_MACHINE_AND_USERS = ( USERS || {a,b}::LOCK || {a,b}::COFFEE_MACHINE).
  ``` 
  [File](./coffee-machine-sugar-with-two-users.lts)
  

#### Analysis and Verification using FLTL

- Using the LTSA tool
  - In order to specify and check LTL property, the LTSA tool must be launched including also the `LTL2buchi.jar` library
    - `java -cp LTL2buchi.jar -jar  ltsa.jar`  
  - To check the LTL property using the LTSA tool: `Check -> LTL Property`

- **Critical Section** case study
  ```
  USER = 
    (ncs -> enterCS -> cs -> exitCS -> USER).
  ||USERS = (a:USER || b:USER).
  ```
  - Defining *safety* properties
    
    ```
    fluent USER_A_IN_CS = <a.enterCS, a.exitCS>
    fluent USER_B_IN_CS = <b.enterCS, b.exitCS>
    assert MUTEX = []!(USER_A_IN_CS && USER_B_IN_CS)
    ```

    The property violated - adding a lock to guarantee mutual exclusion.

  - Defining *liveness* properties
    ```
    USER = 
      (ncs -> acquire -> enterCS -> cs -> exitCS -> release -> USER).

    LOCK = 
      (acquire -> release -> LOCK).

    ||USERS = (a:USER || b:USER || {a,b}::LOCK).

    fluent USER_A_IN_CS = <a.enterCS, a.exitCS>
    fluent USER_B_IN_CS = <b.enterCS, b.exitCS>

    assert MUTEX = []!(USER_A_IN_CS && USER_B_IN_CS)
    assert NO_STARV = [](a.acquire -> <> a.enterCS)
    ```
    - **Remark**: Before doing the check with the LTSA Tool, disable the option `Options > Fair Choice for LTL Check`




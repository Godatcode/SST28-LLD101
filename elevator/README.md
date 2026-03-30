# Elevator System

## Class Diagram

```
+---------------------+       +---------------------+
|      <<enum>>       |       |      <<enum>>       |
|      Direction      |       |   ElevatorState     |
|---------------------|       |---------------------|
| UP                  |       | IDLE                |
| DOWN                |       | MOVING_UP           |
+---------------------+       | MOVING_DOWN         |
                               +---------------------+

+---------------------+
|       Request       |
|---------------------|
| -floor: int         |
| -direction          |
+---------------------+
          |
          | dispatched to
          v
+----------------------------------------------+
|                  Elevator                    |
|----------------------------------------------|
| -id: String                                  |
| -currentFloor: int                           |
| -state: ElevatorState                        |
| -destinations: TreeSet<Integer>              |
|----------------------------------------------|
| +addDestination(floor): void                 |
| +step(): void                                |
| +isIdle(): boolean                           |
+----------------------------------------------+
          ^
          | manages
+----------------------------------------------+
|             ElevatorController               |
|----------------------------------------------|
| -elevators: List<Elevator>                   |
|----------------------------------------------|
| +dispatch(request): void                     |
| +step(): void                                |
| +printStatus(): void                         |
+----------------------------------------------+
          ^
          | uses
+----------------------------------------------+
|                  Building                    |
|----------------------------------------------|
| -totalFloors: int                            |
| -controller: ElevatorController              |
|----------------------------------------------|
| +requestElevator(floor, direction): void     |
| +step(): void                                |
| +printStatus(): void                         |
+----------------------------------------------+
```

## Design Approach

### Elevator Movement
Each `Elevator` maintains a `TreeSet<Integer>` of destination floors. A TreeSet keeps them sorted automatically. When `step()` is called, the elevator moves one floor towards the next destination, prints an arrival message when it gets there, and removes the served floor. When the destination set is empty, the state becomes IDLE.

### Dispatch Algorithm
`ElevatorController.dispatch()` picks the best elevator for each request using a cost function:
- **IDLE elevator**: cost = distance to request floor
- **Elevator moving in same direction toward the floor**: cost = distance (it's on the way, efficient)
- **Elevator moving away or in opposite direction**: cost = 100 + distance (heavily penalized, last resort)

The elevator with the lowest cost gets the request.

### Building as Facade
`Building` is the entry point for users. It validates the floor number and delegates to the controller. This keeps the caller from needing to know about elevators or requests directly.

### Simulation
The system is step-driven: each call to `building.step()` advances all elevators one floor. This makes the behavior easy to observe and test.

## How to Build and Run

```bash
cd elevator
javac -d out src/com/example/elevator/*.java
java -cp out com.example.elevator.App
```

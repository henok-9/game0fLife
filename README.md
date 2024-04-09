<h1 align="center"> COMP2800: Game of Life</h1>

<p align="center">Java implementation of Conway's Game of Life, the classic cellular automaton. </p>

<p align="center">
  <img src="assets/header.gif "/>
</p>



## Features

**GUI implemented using Java Swing**
* Visual grid representation with customizable dimensions.
* Start, stop, pause, and step controls for simulation flow.
* Adjustable simulation speed using a slider.
* Ability to draw initial patterns on the grid.
* Reset button to revert to the initial grid state.

	
## Installation

**Prerequisites**

* Java Development Kit (JDK) version 11 or later
* `make` utility (optional)
* Git 

**Steps**

1. **Clone the Repository:**
```shell
git clone https://github.com/henok-9/game0fLife.git
```

2. **Navigate to the project directory:**
```shell
cd gameOfLife
```

3. **Build the Project:**
```shell
make compile
```

4. **Run the Simulation:**
```shell
make run
```


## Usage

1. Set the initial grid size.
2. Optionally, click cells to create a starting pattern.
3. Press "Start" to begin the simulation.
4. Use "Pause", "Step", and the speed slider to control execution.
5. Press "Reset" to clear the grid and start over

## Next Steps 

- [ ] Use an actual Java build tool ... (Gradle)
- [ ] implement a way to visualize the age of cells with color gradients. 
- [ ] Enable saving interesting patterns to files and reloading them. 
- [ ] port project to JavaFx 

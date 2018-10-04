SLogo API Design Exercise: CellSociety API Critique
===
Cell Society: Reviewing Team 10's API

_For simplicity, we will look at a couple classes within each part of the project and analyse all the methods whether they should be internal or external._

* Simulation
    * Internal
        * getFishBreedPeriod()
        * getSharkBreedPeriod()
    * External:
        * getStatistics() passes statistics to the * GUI
    * Not part of API
        * breed()
        * eat()
        * localUpdate(Cell<Integer> me, List<Cell<Integer>> neighbors) 

* Visualization:
    * Internal:
        * All the line charts are internal
        * All the model controls are internal
    * External:
        * GameOfLifeStatistics() 
        * SegregationStatistics() 
        * SpreadingFireStatistics() 
        * WaTorStatistics()
        * simulator() 
        * NeighborChooser(String shapeType) 

* Configuration: 
    * Internal: All the xml writers and parsers are internal because they are specific in performing their functionality and do not allowed themselves to be modified by people working on other parts of the project

* Other classes
    * Utility
        * ColorUtils
            * mix()
        * ShapeUtils
            * centerShape()
            * makeShape()
            * shapeCodes()
            * shapes()

API Design Description

For Cell, all the methods are internal, because Cell class is generalized stand-alone class. The Cell classes will be called by the grid, both of which belongs to the back-end.

Simulation interface has situational implementation after considering neighbors, next color, other parameters etc.

The GUI utilizes a hierarchical class organization structure in order to effectively communicate between elements. Objects which manipulate other objects subjugate said object as a private instance within themselves, creating a hierarchy based on functionality rather than visual representation.

The GUI also uses getStatistics() API from the Simulation in order to gather the information about the model and pass the parameters into the line chart.
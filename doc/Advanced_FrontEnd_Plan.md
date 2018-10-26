
Ben
* Display and run multiple turtles (ask how turtles move)
* click to toggle which turtles are currently active (active turtles should be graphically distinct from inactive ones)
    * What is active?
    * How to make it toggleable? Not ImageView -> What?
* provide a way to change the pen's current properties graphically (up/down, thickness, etc.)
* Palettes of images and colors with their associates numeric values that can be referred to within new SLogo commands (i.e., it should be clear which images/colors are associated with which numbers so the SLogo language can refer to them in code). At the start, there should be a default set of values assigned to numbers to choose from.

Vincent
* StateView (display for one turtle) - current state of a turtle (i.e., its ID, position, heading) and its pen (i.e., up/down, color, thickness) updated while the code is running. Feel free to include any other values you feel would be usefully associated with either. 
    * Store multiple turtle objects inside View
    * Maybe add id to coordinate
* click to execute commands from the history or user-defined commands (provide a means to enter needed parameters)
    * make history clickable
* click to edit the value of user-defined variables
* open or close any view (except the main turtle display) or to put them in any order they want.

Together
* Allow users to create multiple workspaces (perhaps as tabs or as separate windows) and to set, save, and load preferences for each workspace. You can define exactly what a workspace contains but, at the very least, it should have separate turtle display areas, palettes, command histories, active variables, user-defined commands, and default command language. Likewise you can choose the exact preferences you want to make available (like starting background, color and image palettes, starting number of turtles, file of code to load, etc.).
* UI Style and Design
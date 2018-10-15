SLogo Architecture Design: Team 07
===
1. What is the result of parsing and who receives it?
    * The result of parsing is a set of commands that are able       to be understood by our Turtle class. For example, if the
    
    
2. When does parsing need to take place and what does it need to start properly?
3. When are errors detected and how are they reported?
    * Errors that can occur are the command inputted is not valid. For example, the command is misspelled or the syntax is invalid. If that occurs, our interpreter class would catch it, and throw an exception that would prompt the GUI to create an error box. 
5. What do commands know, when do they know it, and how do they get it?
    * Command 
6. How is the GUI updated after a command has completed execution?
    * The commmand history window would get a string from the command input and display it
    * The turtle would move based on the command given
---
# CLASSES
Model View Controller

* Main
    * Instantiates the GUI

* GUI (displays info from Grid)
    * GUI class
        * LogoPanel
            * Display
        * frontend_external.HistoryView
            * Contains a ArrayList of commands, passed from Command Class
            * Update command once a new command is typed
        * InputPanel
            * Get the input and pass to Interpreter
    * Methods
        * step() to start simulation
* Controller
    * detect change in input
    * detect change in turtle position
* Turtle
    * Point getPosition()
    * void move(Point p)
    * void rotate(double theta)
    * void penUp()
    * void penDown()
* Interpreter (parser)
    * private void ParseCommand(String command)
        * Throws exception if invalid command
        * Creates command classes if the command is valid. For example if the command is "fd 50", we could create a forwardCommand class. 
* Command (Abstract Super Class containing common methods)
    * update Method implemented by subclasses
    * update(Turtle t)
        * Forward Subclass
        * Draw Circle
        * Rotate
        * penUp
        * penDown

* Exception Classes
    * InvalidCommandException
    * OutOfBoundsException
    * WrongLanguageException



Internal Back End : Jose

Internal Front End : Ben

External Back End : Harry

External Front end : Vincent
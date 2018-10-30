# Slogo Team 7

A development environment that helps users write SLogo programs.

* Team Members & Responsibilities
    * Vincent Liu
        * Nearly all the view components in frontend except for animation
        * Connecting backend with the frontend through external API
    * Harry Xie
        * ?
    * Ben Xu
        * Turtle Display window
        * Multiple Turtle manipulation/management framework
        * Turtle animation and processing queue
        * Front end internal API functionality integration
    * José San Martin
        * ?
* Dates you started, date you finished, and an estimate of the number of hours worked on the project
    * Start Date: 10.15.2018
    * End Date: 10.29.2018
    * Estimate Hours: 150+
* Files used to test the project and errors you expect your program to handle without crashing
    * data/examples
* Data or resource files required
    * FrontEnd/resources
    * BackEnd/resources
* Run the program
    * Note
        * Due to module's complexity, we decided to use two parallel modules FrontEnd and BackEnd.
    * Procedures
        1. Set FrontEnd/resources as the resource root
        2. In the module setting, import two modules: FrontEnd and BackEnd
        3. Go to FrontEnd module's dependency setting, add BackEnd module as its dependency
        4. Go to FrontEnd/src/main/Main.java, run it
* Reference
    * [Yanbo Fang's Repository](https://github.com/yanbofang/slogo/tree/master/src)
    * https://www.oodesign.com/command-pattern.html
    * https://www.oodesign.com/memento-pattern.html
* Assumptions:
    * Everything is a double, except for the remainder method
    * Lists (brackets) only work as arguments. Therefore, you can't say something like "[ fd 50 ]" because there is no top level commands here.
    * When assigning variables in Make/Set or in loop functions, the variable name must start with "
        * For example, to set 5 to a variable var, the command would be 'make "var 5'
    * In order to run a user defined function, the user needs to say "run" and then the name of their function
    * Unlimited parameters: We will create a parenthesis node, and it will act very similar to a
    List node, where the parenthesis node will distribute the command to all of the arguments.
* any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
* any known bugs, crashes, or problems with the project's functionality
* any extra features included in the project

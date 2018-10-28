# slogo
A development environment that helps users write SLogo programs.

* names of all people who worked on the project
* date you started, date you finished, and an estimate of the number of hours worked on the project
* each person's role in developing the project
* any books, papers, online, or human resources that you used in developing the project
* files used to start the project (the class(es) containing main)
* files used to test the project and errors you expect your program to handle without crashing
* any data or resource files required by the project (including format of non-standard files)
* any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
* any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
* any known bugs, crashes, or problems with the project's functionality
* any extra features included in the project


Assumptions:
- Everything is a double, except for the remainder method
- Lists (brackets) only work as argumnets. Therefore, you can't say something like "[ fd 50 ]" because there is no top level commands here. 
- When assigning variables in Make/Set or in loop functions, the variable name must start with " 
    - For example, to set 5 to a variable var, the command would be 'make "var 5'
- In order to run a user defined function, the user needs to say "run" and then the name of their function
- Unlimited parameters: We will create a parenthesis node, and it will act very similar to a 
List node, where the parenthesis node will distribute the command to all of the arguments.
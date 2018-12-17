## Estimation: before looking at the old code:

- how long do you think it will take you to complete this new feature?
  - About 1 hour
- how many files will you need to add or update? Why?
  - Add:
    - TurtleImageView -> to display all images of all turtle
  - Edit:
    - View -> I need to add the new view inside the GridPane and add internal methods to add images
    - DisplayView -> add the image of default and newly added turtle onto the TurtleImageView
    - TurtleManager -> this class needs to call View to add image to the TurtleImageView
   
## Review: after completing the feature:

- how long did it take you to complete this new feature?
  - 1.2 hour
- how many files did you need to add or update? Why?
  - TurtleImageView -> I created it in the front end
  - View and ViewInternal. I added 2 methods in the interface and in the concrete class to change/add images
  - DisplayView -> I added 1 method to pass the command to change only 1 turtle image
  - TurtleManager -> I added 1 method to alter 1 turtle image
  - style.css -> add border for the TurtleImageView
- did you get it completely right on the first try?   
  - No I did not. I encountered a problem in that I directly added the sprite to the TurtleImageView, where the sprite
  never showed up and I did not know why. Later on, I figured out that Sprite class has its own positions, but I only
  need the image from the sprite.
 
## Analysis: what do you feel this exercise reveals about your project's design and documentation?

- was it as good (or bad) as you remembered?
  - It was as good as I remembered. I could follow the documentations and find usages for certain methods to simulate
  the code in my head and figure out where to add the new code
- what could be improved?
  - The debugging process is not very smooth. When I knew the turtle did not show up, I should take a look at its 
  location, which I did. However, I forgot to differentiate ImageView and Sprite, a customized class that Ben wrote. 
- what would it have been like if you were not familiar with the code at all?
  - It will certainly take longer to understand the program structure. However, since the documentation is quite clear,
  it will still be doable.
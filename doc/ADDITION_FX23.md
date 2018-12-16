CompSci 308: SLogo Addition
===================

### Estimation

I think this task should take less than 10 minutes.

I need to add two command classes: Stamp and ClearStamp. Then add two booleans and the code to notify listeners in the turtle classes, including the interface, the leaf, and the group.

### Review

Exactly 10 minutes.

I updated five files.

Yes. I got it completed on the first try without much problem.

### Analysis

The project's design is good, at least in the backend. Since the front-end listens to the state of the turtle, I simply needed to add some boolean values and some toggle boolean methods to notify the front-end. The display people are doing the heavy lifting with their painting and clearing methods.

Listener patterns, although it is very simple for me, can be very complicated for the front-end display. I hope they can provide a public API for me to call instead of passively waiting to be listened.

It would have been more difficult. But the Command classes have good example code.
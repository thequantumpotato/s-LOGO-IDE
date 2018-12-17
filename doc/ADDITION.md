SLOGO Feature Addition
Jose San Martin
===

Before Addition
====

* How long do you think it will take you to complete this new feature?
	* I think it will take me around an hour to implement the new feature
* How many files will you need to add or update? Why?
	* I should only have to update a few things. However, with the way we created commands, I should be able to add a stamp command to the backend. This would then prompt the front-end to essentially implement this command, and have the turtle leave a stamp of itself on the ground. 


After Addition
====

* how long did it take you to complete this new feature?
	* It took me around an hour to complete the feature

* how many files did you need to add or update? Why?
	* I needed to update quite a bit of files. Even though this was a backend task, I had to update not only the backend, but also the front end. This was because the backend simply notified the front-end of what to do, and the front-end actually implemented the command. In this case, the backend would parse the command and tell the front-end that the turtle needed to eb stamped

* did you get it completely right on the first try?
	* I did not. I had a few bugs of a lot of turtles being created

* Analysis: what do you feel this exercise reveals about your project's design and documentation?

* was it as good (or bad) as you remembered?
	* It was just what I remembered. The Observer/Observable design pattern was a headache during the actual project, and it is a headache now. Compared to the publisher/subscribe design pattern that my team used in Vooga, this feels so archaeic. I would totally go back into the past and change this entire design if I could.

* what could be improved?
	* The way the front-end and back-end communicate. Right now it is just a mess, and the front-end relies on being notified of any changes that occur to the turtle. 
	* I would use the publisher/subscribe pattern now if I could

* what would it have been like if you were not familiar with the code at all?
	* A complete mess. Since I was familiar, I knew where to put the nofityObservers() method, the update() method, and the clear() method for the turtles. If I was new to this code, I know that it would have taken me a long time to figure that out, because it just isn't intuative. I also knew that the DisplayView class of the frontend is what did the updating, so I knew to look there when trying to change the frontend. 
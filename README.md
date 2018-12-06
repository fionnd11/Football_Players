# Football_Players
Project
Name: Fionn Daly
Student ID: G00349372

1.	Introduction

We are developing Football players Application using JavaFX framework. We are using Eclipse as an IDE for coding and deployment.
Major functionalities developed in the project are addition, deletion and search specific for player by his jersey number or name. 
We can save the entire DB in .ser file and we can reset the form whenever required.



2.	Application Functionalities

Main Screen of the application is shown in screencast: 
 

i.	Adding a player to database
	
	Player object has 5 properties viz. Jersey Number, First Name, Last Name, Date of Birth 	and Club  he is playing for.
	When we click on 'Add Player' button on Home Screen, respective screen is displayed.
 
	On entering all mandatory fields, we click on 'Add Player' button, to add a record to 	database.
 	Meanwhile, if we decide to cancel player addition, we can do so by clicking button 	'Cancel  Adding Player' .
	
ii.	Removing a player from database

	We need to enter player's Jersey Number in the textbox next to the button 'Remove 
	Player' and click the button. Specified player will be deleted. If the player is not available 	in  database, proper error/warning message will be displayed.
  
iii.	Searching for a player (by Jersey No. or by First Name)

	We can search player by his Jersey Number or First Name as part of this functionality.
	In the respective textbox we have to enter Jersey No / First Name and by clicking 	respective button we can search for mentioned player.
	Again, If the player is not available in the database, proper error/warning message will 	be  displayed.

iv.	Getting total number of players 
	
	To get the total number of players in database, we use this functionality. 
	It displays total number of players in database in the provided textbox which in not 	editable.

v.	Saving players to DB / Loading players from DB 

	We can save players we have added into .ser file in the project directory 
	This database will be later used whenever we want to load DB by clicking 'Load Player's 	DB' button at the top of Home screen.
 
vi.	Reset the Form 

	To clear all the textboxes we use this functionality. We can use whenever necessary.

vii.	Quit the application
	
	This button is used to quit from our application.
  
  

3.	Project Files & Extra Features

3.1 Files 

i.	Launcher.java

	This file contains main function. This is the file invoked when we run the application at the start.
	It also contains code to format screen components (like forms, buttons, textbox etc.) using 	JavaFX framework.

ii.	PlayerManager.java
	
	This file contains code (more precisely functions) for managing all operations on football players 	list like adding, 	removing , searching a player and calculating total number of players in 	database.

iii.	Player.java

	This file contains player class (which contains 5 variables) and getter and setter methods for 	those properties of player object. 
	These properties include Jersey Number, First Name, Last Name, Date of Birth and Club the 	player is playing for.
	This file also contains one important function written at the end to return complete player 	record.

	In these code files, we have used java collection to create a structure to store objects. We have 	also taken care of handing exceptions (eg: when we try to load a file which is not present, we 	handle it with proper error and so on).
	We have also used serialization of objects and used JavaFX as framework to build GUI objects 	like buttons, textboxes in the form of scenes and stage.
3.2 Extra Features

	We have also included brief screen-cast of working application  and we have prepared clickble 	jar for the application.
	Steps to create clickable jar are:
	File -> Export -> Runnable JAR file (under Java Section)  select launch configuration & destination 	to save jar ->  finish.

4.	Compilation/Deployment of a Project

	In eclipse, we chose option 'Build Automatically' under Project menu option. It build (compiles) 	all code files in our project folder in real time. If there are errors in any of the files, it highlights it 	with red cross mark.

5.	Running a Project

	We run the project by Ctrl + F11 or by clicking RUN symbol from Menu. It launches home page 	designed using JavaFX.
	Screen-Cast is made to demonstrate entire working of the project.



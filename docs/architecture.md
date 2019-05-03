# Architecture

## Packaging structure

The program is organized into four packages into a three level structure. The UI package contains the main GUI component (and any future additions), whilst Domain contains all classes responsible for the main program logic. The DAO package contains data access objects responsible for saving and loading data, whilst the Simulation package contains classes necessary for running simulations and extracting results from them. Below is a diagram illustrating the hirearchy between the four packages. 

![packages](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/pictures/packagestructure.jpg)

## GUI Structure 

The graphic user interface is implemented in JavaFX. As such, each scene constitutes it's own object, and all compononets of the current scene (buttons, list displays) are components added to these scenes. The stage object in JavaFX is a root class that the scene objects are appended to when different GUI elements are to be displayed. The program has three scenes that can be accessed from each other:

+ Load / Intro scene 
+ Simulation editing scene
+ Result viewing scene

The logic of the program is controlled mainly by the markovManager class, but some trivial logical operations are conducted in the UI if they relate to how the scene is to be displayed next etc. Incorporating this logic into the markovManager class would have necessitated unnecessary complications.  

The scenes are all initiliazed at once since they don't have many repeated components, i.e. they can't all be constructed in the same way with a single method. The UI class nevertheless contains a method for updating parts of the three scenes. 

## Application Logic and Classes
The classes and their tasks can be summarized as follows:
+ markovManager : links together other classes / main logic
+ SimDescriptor : contains data structures used for building simulations
+ Simulation : evolvable, readable transition matrix representation of a simulation 
+ SimHelper : Simulation editing class
+ SimDao : interface for loading and saving SimDescriptor objects  
+ SimFromFile : class implementing SimDao that reads and saves to .csv files
+ SimFromDb : class implementing SimDao that reads and saves to an SQL database
+ markovUI : Entry point into program + graphical user interface 

The classes are organized inside the packages as shown below. The user interacts with a UI layer, and the application logic is handled chiefly by the markovManager class. Simulation descriptions are contained in simDescriptor objects, which can be loaded from a data-access-object or created by the user in the application.

The simulation state that is displayed to the user and advanced is contained in the simulation class. The simulation class mainly advances the simulation and contains the necessary information in a light format. The SimHelper class was implemented to 

![Architecture](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/pictures/architecture.jpg)

## Data Storage

Simulations are read and saved in .csv files in a simple format. The element in the first row and column is the size of the simulation, or the number of nodes. The next row elements are strings containing the descriptions or names of the nodes. After this we have the connections between the nodes stored as integers, representing the start index and end index of the nodes encountered in the previous lines. The start and end indices are separated by a comma. An example .csv can be found [here](https://github.com/volatilequark/ot-harjoitustyo/tree/master/docs).

Simulations can also be read and stored into an SQL database. The nodes are stored inside a table with the following schema:
and the connections are stored in a table with the scema: . We can join the node table with itself on the connections stored in the connections table to find unconnected nodes. 

## Main Features
### Loading a simulation from a .csv file 
### Advancing the simulation state
When the user presses the 'next' button in the result display, the following sequence occurs. The final result is that the current simulation state has advanced by n steps, and the results of taking these steps are displayed to the user.

![Sequence](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/pictures/simsequence.png)


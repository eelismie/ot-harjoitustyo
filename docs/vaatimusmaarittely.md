# Purpose 

A markov process is a weighted network model of a stochastic system, where the nodes of the network are possible states of the system, and the weights of 
the edges correspond to the probability of the system transitioning from one state to the other. The idea of this project is to create an application that allows the
user to create and edit small markov chains and explore the statistics of a random walk on the graph. Markov processes have been used to define Google's pagerank algorithm, and have been used to model multiple 
physical phenomena. 

# Users 

Primarily there will be one user at a time who can create / load simulations. The application can work on a simulation to simulation basis, whereby the user simply
loads or saves simulations via the internet or from their local computer. This, in a trivial way, would allow simulations to be shared between users (either by sharing
a google docs link or file).  

# GUI interface

The application will initially consist of only three separate scenes. The initial scene will handle simulation loading and creation. The second screen would be a 
simulation editing scene, allowing the user to see the nodes and connections as a list and add more if necessary. If time allows, this could be expanded to show a graphical depiction of the 
network. The third scene would contain results of the simulation, with a ranking of the most likely states of the system. Similarly, this view could be expanded to 
include a graphical depiction of the network.

# Initial Functionality

+ The user can save and load existing simulations to and from csv files via the internet or from their computer
+ The user can add nodes into a simulation and attach descriptions to these nodes. For example, node 1 has the message "finishes project on time" whilst node 2 has the message "plays football".
+ The user can add connections/transitions between nodes. Initially the connections are limited to 2-way equally weighted connections.
+ The user can allow the random walk to jump into nodes that are not connected to it and define the probability of this occurring 
+ The user can view the results of the simulation they designed, and select the results 

# Expansion possibilities

+ The addition of nodes can be expanded to include one-way and arbitrary probability transitions. In order for the system to behave like a markov process the application should be able to change existing transitions so that the walk stays in the graph.
+   

  
  

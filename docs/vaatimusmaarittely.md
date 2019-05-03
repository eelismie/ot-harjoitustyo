# Purpose 

A markov process is a weighted network model of a stochastic system, where the nodes of the network are possible states of the system, and the weights of 
the edges correspond to the probability of the system transitioning from one state to the other. The idea of this project is to create an application that allows the
user to create and edit small markov chains and explore the statistics of a random walk on the graph. Markov processes have been used to define Google's pagerank algorithm, and have been used to model multiple 
physical phenomena. 

# Users 

The application supports one user at a time who can create / load simulations. The application works on a simulation to simulation basis, whereby the user simply loads or saves simulations via csv files. Each user also has the option to save progress on a simulation they are building on their own computer. The .csv save/load feature, in a trivial way, allows simulations to be shared between users.

# GUI 

The application consists of three separate scenes. The initial scene provides simple instructions and contains the option to load a simulation from a csv. The second screen is a simulation editing scene, allowing the user to see the nodes and connections as a list and add more if necessary. The user can also choose to save the simulation from this creen, or clear the currently loaded nodes and connections. The third scene contains results for the simulation. The user can use provided sliders to control the running of the simulation and the way the results are displayed.

![GUI](https://github.com/volatilequark/ot-harjoitustyo/blob/master/docs/GUI.png)

# Functionality

+ The user can save and load existing simulations to and from csv files from their computer
+ The user can add nodes into a simulation and attach descriptions to these nodes. For example, node 1 has the message "finishes project on time" whilst node 2 has the message "plays football".
+ The user can add connections/transitions between nodes. The connections are 1-way connections, which are configured in a way to ensure that the sums of the probabilities of traveling out from node a to any of it's connections adds up to one.  
+ The user can allow the random walk to jump into nodes that are not connected to it during the running phase of the simulation, and define the probability of this occurring at each step. 
+ The user can view the results of the simulation they designed, and see the results for each starting node.
+ The user's progress can be saved during editing using a database. 

# Expansion possibilities

The application has been structured to allow it to be expanded in the following ways:

+ The addition of nodes can be expanded to include one-way and arbitrary probability transitions. In order for the system to behave like a markov process the application should be able to change existing transitions so that the walk stays in the graph.
+ Graphical visualization of the progression of the simulation can be added to the results screen.
+ Expanded user roles, i.e. added login functionality.
+ Add simulation predictions: does a steady state exist, what is the steady state vector? This would require solving for eigenvalues of nxn transition matrix. 

  
  
